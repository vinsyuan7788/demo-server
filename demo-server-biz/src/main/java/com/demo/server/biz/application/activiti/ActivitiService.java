package com.demo.server.biz.application.activiti;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author Vince Yuan
 * @date 03/02/2021
 */
public interface ActivitiService {

    /**
     *  Deploy workflows (defined by BPMN files for example)
     *
     * @return
     */
    Deployment deployWorkflows();

    /**
     *  Get all deployment information
     *
     * @return
     */
    List<Deployment> getAllDeployments();

    /**
     *  Get the latest deployment information
     *
     * @return
     */
    Deployment getLatestDeployment();

    /**
     *  Get all deployed workflows
     *
     * @return
     */
    List<ProcessDefinition> getAllDeployedWorkflows();

    /**
     *  Get the latest deployed workflows
     *
     * @return
     */
    List<ProcessDefinition> getLatestDeployedWorkflows();

    /**
     *  Get all deployed workflows with specified definition key
     *
     * @return
     */
    List<ProcessDefinition> getAllDeployedWorkflowsWithDefinitionKey();

    /**
     *  Get the latest deployed workflows with specified definition key
     *
     * @return
     */
    List<ProcessDefinition> getLatestDeployedWorkflowsWithDefinitionKey();

    /**
     *  Start the latest deployed workflow
     *
     * @return
     */
    ProcessInstance startLatestDeployedWorkflowWithDefinitionKey();

    /**
     *  Get all running workflows
     *
     * @return
     */
    List<ProcessInstance> getAllRunningWorkflowsWithDefinitionKey();

    /**
     *  Get one running workflow
     *
     * @return
     */
    ProcessInstance getOneRunningWorkflowWithDefinitionKey();

    /**
     *  Get current running workflow
     *
     * @return
     */
    ProcessInstance getCurrentRunningWorkflowWithDefinitionKey();

    /**
     *  Terminate all running workflows
     */
    void terminateAllRunningWorkflowsWithDefinitionKey();

    /**
     *  Get all tasks from the workflow that is currently running
     *
     * @return
     */
    List<Task> getAllTasksFromCurrentRunningWorkflowWithDefinitionKey();

    /**
     *  Get all active tasks from the workflow that is currently running
     *
     * @return
     */
    List<Task> getAllActiveTasksFromCurrentRunningWorkflowWithDefinitionKey();

    /**
     *  Get all suspended tasks from the workflow that is currently running
     *
     * @return
     */
    List<Task> getAllSuspendedTasksFromCurrentRunningWorkflowWithDefinitionKey();

    /**
     *  Get root tasks from the workflow that is currently running
     *
     * @return
     */
    List<Task> getAllRootTasksFromCurrentRunningWorkflowWithDefinitionKey();

    /**
     *  Get one active task from the workflow that is currently running
     *
     * @return
     */
    Task getOneActiveTaskFromCurrentRunningWorkflowWithDefinitionKey();

//    /**
//     *  Get one root task from the workflow that is currently running
//     *
//     * @return
//     */
//    Task getOneRootTaskFromCurrentRunningWorkflowWithDefinitionKey();

    /**
     *  Get current task from the workflow that is currently running
     *
     * @return
     */
    Task getCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey();

    /**
     *  Complete current task from the workflow that is currently running
     */
    void completeCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey();

    /**
     *  For exploration
     *
     * @return
     */
    void forExploration();
}
