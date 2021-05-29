package com.demo.server.biz.application.activiti.impl;

import com.demo.base.common.utils.LogUtils;
import com.demo.base.common.utils.utils.ParametersToLog;
import com.demo.server.biz.application.activiti.ActivitiService;
import com.demo.server.common.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *  When Activiti starts: <br/>
 *  -- The tables with record inserted: <br/>
 *  &emsp; -- General: act_ge_property <br/>
 *  When workflow deployed: <br/>
 *  -- The tables with record inserted: <br/>
 *  &emsp; -- General: act_ge_bytearray <br/>
 *  &emsp; -- Repository: act_re_deployment; act_re_procdef <br/>
 *  When workflow starts: <br/>
 *  -- The tables with record inserted: <br/>
 *  &emsp; -- Runtime: act_ru_execution; act_ru_task; act_ru_variable <br/>
 *  &emsp; -- History: act_hi_actinst; act_hi_procinst; act_hi_taskinst; act_hi_varinst; act_hi_detail <br/>
 *  When task completes: <br/>
 *  -- The tables with record inserted: <br/>
 *  &emsp; -- Runtime: act_ru_variable <br/>
 *  &emsp; -- History: act_hi_varinst; act_hi_detail <br/>
 *  -- The tables with record updated: <br/>
 *  &emsp; -- History: act_hi_actinst; act_hi_taskinst; <br/>
 *  -- The tables with record removed: <br/>
 *  &emsp; -- Runtime: act_ru_task <br/>
 *  When workflow ends (by completing a task or manually terminated): <br/>
 *  -- The tables with record updated: <br/>
 *  &emsp; -- History: act_hi_procinst <br/>
 *  -- The tables with record removed: <br/>
 *  &emsp; -- Runtime: act_ru_execution; act_ru_variable <br/>
 *
 * @author Vince Yuan
 * @date 03/02/2021
 */
@Slf4j
@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private FormService formService;

    private final String deploymentName = "Demo Deployment of Workflows";
    private final String deploymentKey = "Deploy Demo Workflows";
    private final String[] deploymentCategories = { "template", "strategy", "queue" };
    /**
     *  The names of the workflow definitions (e.g., the names of the BMPN files) <br/>
     *  -- Which is only used to specify the BPMN files to be deployed
     */
    private final String[] workflowDefinitionNames = { "demo-workflow-1", "demo-workflow-2", "demo-workflow-3" };
    /**
     *  The keys of the workflow definitions (e.g., the ID of the BMPN files) <br/>
     *  -- Which corresponds to the "KEY_" field in "act_re_procdef" table
     */
    private final String[] workflowDefinitionKeys = { "demo_workflow_1", "demo_workflow_2", "demo_workflow_3" };
    /**
     *  The business-concerned value <br/>
     *  -- Which corresponds to the "BUSINESS_KEY_" field in "act_ru_execution" table
     */
    private final String workflowInstanceBusinessKey = "businessValue";
    /**
     *  The business-concerned variables that correspond to a workflow when the workflow is started <br/>
     *  -- Which is stored in "act_ru_variable" table
     */
    private final Map<String, Object> workflowInstanceVariables = new HashMap<>();
    /**
     *  The reason to terminate the workflow <br/>
     *  -- Which corresponds to the "DELETE_REASON_" field in "act_hi_actinst" table
     */
    private final String workflowTerminationReason = "Termination of the Workflow";
    /**
     *  The variables to pass to satisfy the condition of gateway
     *  -- Which will be stored in "act_ru_variable" and "act_hi_varinst" table
     */
    private final Map<String, Object> taskVariables = new HashMap<>();
    /**
     *  The variables that only exist during the transition between tasks and are not stored in the database
     */
    private final Map<String, Object> taskTransientVariables = new HashMap<>();


    /***   Deployment & Management of Workflow Definition   ***/
    @Override
    public Deployment deployWorkflows() {
        Deployment deployment = repositoryService.createDeployment()
                // The name of the deployment
                .name(deploymentName)
                // The key to distinguish the definition of workflows (i.e., one workflow definition corresponds to one key generally)
                .key(deploymentKey)
                // The category of the deployment of the workflows
                .category(deploymentCategories[0])
                // The tenant which the workflows are deployed to (which is used for multiple tenant mode)
                .tenantId(CommonConstant.ZERO.toString())
                // The workflow definitions to deploy (i.e., one deployment can contain multiple workflow definitions)
                .addClasspathResource("activiti/" + workflowDefinitionNames[0] + ".bpmn")
                .addClasspathResource("activiti/" + workflowDefinitionNames[1] + ".bpmn")
                .addClasspathResource("activiti/" + workflowDefinitionNames[2] + ".bpmn")
                // Deploy the workflows
                .deploy();
        return deployment;
    }

    @Override
    public List<Deployment> getAllDeployments() {
        // To query "act_re_deployment" table
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        // Get all deployments
        List<Deployment> deployments = deploymentQuery
                .deploymentKey(deploymentKey)
                .list();
        return deployments;
    }

    @Override
    public Deployment getLatestDeployment() {
        // To query "act_re_deployment" table
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        // Get latest deployment
        Deployment latestDeployment = deploymentQuery
                .deploymentKey(deploymentKey)
                .latest()
                .singleResult();
        log.info(LogUtils.getLogMessage("getLatestDeployment", new ParametersToLog()
                .addParameter("latest deployment from database", latestDeployment)));
        return latestDeployment;
    }

    @Override
    public List<ProcessDefinition> getAllDeployedWorkflows() {
        // To query "act_re_procdef" table
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // Get all deployed workflows
        List<ProcessDefinition> processDefinitions = processDefinitionQuery
                // One deployment can contain multiple workflow definitions (when adding multiple class-path resources in the deployment)
                .list();
        return processDefinitions;
    }

    @Override
    public List<ProcessDefinition> getLatestDeployedWorkflows() {
        // To query "act_re_procdef" table
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // Get all deployed workflows
        List<ProcessDefinition> processDefinitions = processDefinitionQuery
                .latestVersion()
                // One deployment can contain multiple workflow definitions (when adding multiple class-path resources in the deployment)
                .list();
        return processDefinitions;
    }

    @Override
    public List<ProcessDefinition> getAllDeployedWorkflowsWithDefinitionKey() {
        // To query "act_re_procdef" table
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // Get all deployed workflows
        List<ProcessDefinition> processDefinitions = processDefinitionQuery
                .processDefinitionKey(workflowDefinitionKeys[0])
                // One deployment can contain multiple workflow definitions (when adding multiple class-path resources in the deployment)
                .list();
        return processDefinitions;
    }

    @Override
    public List<ProcessDefinition> getLatestDeployedWorkflowsWithDefinitionKey() {
        // To query "act_re_procdef" table
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // Get all deployed workflows
        List<ProcessDefinition> processDefinitions = processDefinitionQuery
                .processDefinitionKey(workflowDefinitionKeys[0])
                .latestVersion()
                // One deployment can contain multiple workflow definitions (when adding multiple class-path resources in the deployment)
                .list();
        return processDefinitions;
    }

    /***   Running of Workflow   ***/
    @Override
    public ProcessInstance startLatestDeployedWorkflowWithDefinitionKey() {
        List<ProcessDefinition> latestDeployedWorkflows = getLatestDeployedWorkflowsWithDefinitionKey();
        if (CollectionUtils.isEmpty(latestDeployedWorkflows)) {
            log.info(LogUtils.getLogMessage("startLatestDeployedWorkflowWithDefinitionKey", "Do not find any latest deployed workflows with definition key"));
            return null;
        }
        ProcessDefinition processDefinition = latestDeployedWorkflows.get(0);
        workflowInstanceVariables.put("workflowInstanceVar1", "demo");
        workflowInstanceVariables.put("workflowInstanceVar2", true);
        workflowInstanceVariables.put("workflowInstanceVar3", new Date());
        workflowInstanceVariables.put("workflowInstanceVar4", new Random().nextInt());
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), workflowInstanceBusinessKey, workflowInstanceVariables);
        log.info(LogUtils.getLogMessage("startLatestDeployedWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("current running workflow", processInstance)));
        return processInstance;
    }

    @Override
    public List<ProcessInstance> getAllRunningWorkflowsWithDefinitionKey() {
        // To query "act_ru_execution" table
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        List<ProcessInstance> processInstances = processInstanceQuery
                .processDefinitionKey(workflowDefinitionKeys[0])
                // One deployed workflow definition can correspond to multiple workflow instances (when the deployed workflow definition is started multiple-times (for different tenants for example))
                .list();
        return processInstances;
    }

    @Override
    public ProcessInstance getOneRunningWorkflowWithDefinitionKey() {
        List<ProcessInstance> allRunningWorkflows = getAllRunningWorkflowsWithDefinitionKey();
        if (CollectionUtils.isEmpty(allRunningWorkflows)) {
            log.info(LogUtils.getLogMessage("getOneRunningWorkflowWithDefinitionKey", "Do not find any running workflows with definition key"));
            return null;
        }
        ProcessInstance processInstance = allRunningWorkflows.get(0);
        log.info(LogUtils.getLogMessage("getOneRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("the running workflow from all running workflows with definition key", processInstance)));
        return processInstance;
    }

    @Override
    public ProcessInstance getCurrentRunningWorkflowWithDefinitionKey() {
        return getOneRunningWorkflowWithDefinitionKey();
    }

    @Override
    public void terminateAllRunningWorkflowsWithDefinitionKey() {
        List<ProcessInstance> allRunningWorkflows = getAllRunningWorkflowsWithDefinitionKey();
        if (CollectionUtils.isEmpty(allRunningWorkflows)) {
            log.info(LogUtils.getLogMessage("terminateAllRunningWorkflowsWithDefinitionKey", "Do not find any running workflows"));
            return;
        }
        for (ProcessInstance allRunningWorkflow : allRunningWorkflows) {
            runtimeService.deleteProcessInstance(allRunningWorkflow.getProcessInstanceId(), workflowTerminationReason);
        }
    }

    @Override
    public List<Task> getAllTasksFromCurrentRunningWorkflowWithDefinitionKey() {
        ProcessInstance processInstance = getCurrentRunningWorkflowWithDefinitionKey();
        if (processInstance == null) {
            log.info(LogUtils.getLogMessage("getAllTasksFromCurrentRunningWorkflowWithDefinitionKey", "Do not find current running workflow with definition key"));
            return new ArrayList<>();
        }
        // To query "act_ru_task" table
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> tasks = taskQuery
                .processInstanceIdIn(Arrays.asList(processInstance.getProcessInstanceId()))
                // One workflow can contain multiple tasks
                .list();
        return tasks;
    }

    @Override
    public List<Task> getAllActiveTasksFromCurrentRunningWorkflowWithDefinitionKey() {
        ProcessInstance processInstance = getCurrentRunningWorkflowWithDefinitionKey();
        if (processInstance == null) {
            log.info(LogUtils.getLogMessage("getAllActiveTasksFromCurrentRunningWorkflowWithDefinitionKey", "Do not find current running workflow with definition key"));
            return new ArrayList<>();
        }
        // To query "act_ru_task" table
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> tasks = taskQuery
                .processInstanceIdIn(Arrays.asList(processInstance.getProcessInstanceId()))
                .active()
                // One workflow can contain multiple active tasks
                .list();
        return tasks;
    }

    @Override
    public List<Task> getAllSuspendedTasksFromCurrentRunningWorkflowWithDefinitionKey() {
        ProcessInstance processInstance = getCurrentRunningWorkflowWithDefinitionKey();
        if (processInstance == null) {
            log.info(LogUtils.getLogMessage("getAllSuspendedTasksFromCurrentRunningWorkflowWithDefinitionKey", "Do not find current running workflow with definition key"));
            return new ArrayList<>();
        }
        // To query "act_ru_task" table
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> tasks = taskQuery
                .processInstanceIdIn(Arrays.asList(processInstance.getProcessInstanceId()))
                .suspended()
                // One workflow can contain multiple suspended tasks
                .list();
        return tasks;
    }

    @Override
    public List<Task> getAllRootTasksFromCurrentRunningWorkflowWithDefinitionKey() {
        ProcessInstance processInstance = getCurrentRunningWorkflowWithDefinitionKey();
        if (processInstance == null) {
            log.info(LogUtils.getLogMessage("getAllRootTasksFromCurrentRunningWorkflowWithDefinitionKey", "Do not find current running workflow with definition key"));
            return new ArrayList<>();
        }
        // To query "act_ru_task" table
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> tasks = taskQuery
                .processInstanceIdIn(Arrays.asList(processInstance.getProcessInstanceId()))
                .excludeSubtasks()
                // One workflow can contain multiple root tasks
                .list();
        return tasks;
    }

    @Override
    public Task getOneActiveTaskFromCurrentRunningWorkflowWithDefinitionKey() {
        List<Task> allTasks = getAllActiveTasksFromCurrentRunningWorkflowWithDefinitionKey();
        if (CollectionUtils.isEmpty(allTasks)) {
            log.info(LogUtils.getLogMessage("getOneActiveTaskFromCurrentRunningWorkflowWithDefinitionKey", "Do not find any active task from current running workflow with definition key"));
            return null;
        }
        Task task = allTasks.get(0);
        log.info(LogUtils.getLogMessage("getOneActiveTaskFromCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("the active task from current running workflow with definition key", task)));
        return task;
    }

//    @Override
//    public Task getOneRootTaskFromCurrentRunningWorkflowWithDefinitionKey() {
//        List<Task> allTasks = getAllRootTasksFromCurrentRunningWorkflowWithDefinitionKey();
//        if (CollectionUtils.isEmpty(allTasks)) {
//            log.info(LogUtils.getLogMessage("getOneRootTaskFromCurrentRunningWorkflowWithDefinitionKey", "Do not find any root task from current running workflow with definition key"));
//            return null;
//        }
//        Task task = allTasks.get(0);
//        log.info(LogUtils.getLogMessage("getOneRootTaskFromCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
//                .addParameter("current task", this.currentTask)
//                .addParameter("the root task from current running workflow with definition key", task), true));
//        this.currentTask = task;
//        return task;
//    }

    @Override
    public Task getCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey() {
        return getOneActiveTaskFromCurrentRunningWorkflowWithDefinitionKey();
    }

    @Override
    public void completeCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey() {
        Task currentTask = getCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey();
        if (currentTask == null) {
            log.info(LogUtils.getLogMessage("completeCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey", "Do not find current task from current running workflow with definition key"));
            return;
        }
        String taskId = currentTask.getId();
        // The variables below will be fetched to condition of branch point
        taskVariables.put("result", "rejected");
        taskVariables.put("demo", false);
        taskVariables.put("demoId", 2);
        // The variables below are served as redundant variables
        taskVariables.put("userId", 1L);
        taskVariables.put("overdueMoney", 3.0d);
        // Record whom the task is assigned to: which is stored in "ASSIGNEE" in "act_hi_taskinst"
        taskService.setAssignee(taskId, "vinsyuan7788");
        taskService.setVariables(taskId, taskVariables);
        taskService.complete(taskId);
//        taskService.complete(taskId, taskVariables, taskTransientVariables);
    }

    @Override
    public void forExploration() {
        // Query current task
        // taskService.addCandidateGroup(taskId, "vinsyuan7788");
        List<Task> taskList = taskService.createTaskQuery().taskCandidateGroup("vinsyuan7788").list();
        log.info(LogUtils.getLogMessage("forExploration", new ParametersToLog()
                .addParameter("taskList.size()", taskList.size())
                .addParameter("taskList", taskList)));
        // Query historic task
        List<HistoricTaskInstance> historicTaskList = historyService.createHistoricTaskInstanceQuery().taskAssignee("vinsyuan7788").list();
        log.info(LogUtils.getLogMessage("forExploration", new ParametersToLog()
                .addParameter("historicTaskList.size()", historicTaskList.size())
                .addParameter("historicTaskList", historicTaskList)));
    }
}
