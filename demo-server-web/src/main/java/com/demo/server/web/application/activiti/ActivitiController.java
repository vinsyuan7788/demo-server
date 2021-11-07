package com.demo.server.web.application.activiti;

import com.demo.base.common.response.bean.CommonResponse;
import com.demo.base.common.response.enums.ResponseEnum;
import com.demo.base.common.utils.LogUtils;
import com.demo.base.common.utils.bean.ParametersToLog;
import com.demo.server.biz.application.activiti.ActivitiService;
import com.demo.server.common.utils.SystemUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author Vince Yuan
 * @date 02/08/2021
 */
@Api(value = "应用-Activiti", tags = "应用-Activiti")
@Slf4j
@RestController
@RequestMapping("/activiti")
public class ActivitiController {

    @Autowired
    private ActivitiService activitiService;

    private final String logInfo = "Please see the log for more info";

    @ApiOperation(value = "部署工作流", notes = "部署定义的工作流")
    @PostMapping("/deployWorkflows")
    public CommonResponse deployWorkflows() {
        Deployment deployment = activitiService.deployWorkflows();
        log.info(LogUtils.getLogMessage("deployWorkflows", new ParametersToLog()
                .addParameter("deployment", deployment)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取所有部署信息", notes = "获取所有部署信息")
    @PostMapping("/getAllDeployments")
    public CommonResponse getAllDeployments() {
        List<Deployment> allDeployments = activitiService.getAllDeployments();
        log.info(LogUtils.getLogMessage("Get all deployments", new ParametersToLog()
                .addParameter("size", allDeployments.size())
                .addParameter("content", allDeployments)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取最新部署信息", notes = "获取最新部署信息")
    @PostMapping("/getLatestDeployment")
    public CommonResponse getLatestDeployment() {
        Deployment latestDeployment = activitiService.getLatestDeployment();
        log.info(LogUtils.getLogMessage("getLatestDeployment", new ParametersToLog()
                .addParameter("content", latestDeployment)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取所有部署的工作流", notes = "获取所有部署的工作流")
    @PostMapping("/getAllDeployedWorkflows")
    public CommonResponse getAllDeployedWorkflows() {
        List<ProcessDefinition> allProcessDefinitions = activitiService.getAllDeployedWorkflows();
        log.info(LogUtils.getLogMessage("getAllDeployedWorkflows", new ParametersToLog()
                .addParameter("size", allProcessDefinitions.size())
                .addParameter("content", allProcessDefinitions)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取最新部署的工作流", notes = "获取最新部署的工作流")
    @PostMapping("/getLatestDeployedWorkflows")
    public CommonResponse getLatestDeployedWorkflows() {
        List<ProcessDefinition> latestDeployedWorkflows = activitiService.getLatestDeployedWorkflows();
        log.info(LogUtils.getLogMessage("getLatestDeployedWorkflows", new ParametersToLog()
                .addParameter("size", latestDeployedWorkflows.size())
                .addParameter("content", latestDeployedWorkflows)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取所有部署的指定的工作流", notes = "获取所有部署的指定的工作流")
    @PostMapping("/getAllDeployedWorkflowsWithDefinitionKey")
    public CommonResponse getAllDeployedWorkflowsWithDefinitionKey() {
        List<ProcessDefinition> allProcessDefinitions = activitiService.getAllDeployedWorkflowsWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getAllDeployedWorkflowsWithDefinitionKey", new ParametersToLog()
                .addParameter("size", allProcessDefinitions.size())
                .addParameter("content", allProcessDefinitions)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取最新部署的指定的工作流", notes = "获取最新部署的指定的工作流")
    @PostMapping("/getLatestDeployedWorkflowsWithDefinitionKey")
    public CommonResponse getLatestDeployedWorkflowsWithDefinitionKey() {
        List<ProcessDefinition> latestDeployedWorkflows = activitiService.getLatestDeployedWorkflowsWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getLatestDeployedWorkflowsWithDefinitionKey", new ParametersToLog()
                .addParameter("size", latestDeployedWorkflows.size())
                .addParameter("content", latestDeployedWorkflows)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "启动最新部署的指定的工作流", notes = "启动最新部署的指定的工作流")
    @PostMapping("/startLatestDeployedWorkflowWithDefinitionKey")
    public CommonResponse startLatestDeployedWorkflowWithDefinitionKey() {
        ProcessInstance runningWorkflow = activitiService.startLatestDeployedWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("startLatestDeployedWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("running workflow", runningWorkflow)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取所有运行的指定的工作流", notes = "获取所有运行的指定的工作流")
    @PostMapping("/getAllRunningWorkflowsWithDefinitionKey")
    public CommonResponse getAllRunningWorkflowsWithDefinitionKey() {
        List<ProcessInstance> allRunningWorkflows = activitiService.getAllRunningWorkflowsWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getAllRunningWorkflowsWithDefinitionKey", new ParametersToLog()
                .addParameter("size", allRunningWorkflows.size())
                .addParameter("content", allRunningWorkflows)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取一个运行的指定的工作流", notes = "获取一个运行的指定的工作流")
    @PostMapping("/getOneRunningWorkflowWithDefinitionKey")
    public CommonResponse getOneRunningWorkflowWithDefinitionKey() {
        ProcessInstance runningWorkflow = activitiService.getOneRunningWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getOneRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("running workflow", runningWorkflow)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取当前运行的指定的工作流", notes = "获取当前运行的指定的工作流")
    @PostMapping("/getCurrentRunningWorkflowWithDefinitionKey")
    public CommonResponse getCurrentRunningWorkflow() {
        ProcessInstance runningWorkflow = activitiService.getCurrentRunningWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("running workflow", runningWorkflow)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "关闭所有运行的指定的工作流", notes = "关闭所有运行的指定的工作流")
    @PostMapping("/terminateAllRunningWorkflowsWithDefinitionKey")
    public CommonResponse terminateAllRunningWorkflowsWithDefinitionKey() {
        activitiService.terminateAllRunningWorkflowsWithDefinitionKey();
        log.info(LogUtils.getLogMessage("terminateAllRunningWorkflowsWithDefinitionKey", "Terminate all running workflows with definition key"));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取当前运行的指定的工作流的所有任务", notes = "获取当前运行的指定的工作流的所有任务")
    @PostMapping("/getAllTasksFromCurrentRunningWorkflowWithDefinitionKey")
    public CommonResponse getAllTasksFromCurrentRunningWorkflowWithDefinitionKey() {
        List<Task> allTasks = activitiService.getAllTasksFromCurrentRunningWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getAllTasksFromCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("all tasks", allTasks)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取当前运行的指定的工作流的所有活跃的任务", notes = "获取当前运行的指定的工作流的所有活跃的任务")
    @PostMapping("/getAllActiveTasksFromCurrentRunningWorkflowWithDefinitionKey")
    public CommonResponse getAllActiveTasksFromCurrentRunningWorkflowWithDefinitionKey() {
        List<Task> allTasks = activitiService.getAllActiveTasksFromCurrentRunningWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getAllActiveTasksFromCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("all active tasks", allTasks)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取当前运行的指定的工作流的所有挂起的任务", notes = "获取当前运行的指定的工作流的所有挂起的任务")
    @PostMapping("/getAllSuspendedTasksFromCurrentRunningWorkflowWithDefinitionKey")
    public CommonResponse getAllSuspendedTasksFromCurrentRunningWorkflowWithDefinitionKey() {
        List<Task> allTasks = activitiService.getAllSuspendedTasksFromCurrentRunningWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getAllSuspendedTasksFromCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("all suspended tasks", allTasks)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取当前运行的指定的工作流的所有根任务", notes = "获取当前运行的指定的工作流的所有根任务")
    @PostMapping("/getAllRootTasksFromCurrentRunningWorkflowWithDefinitionKey")
    public CommonResponse getAllRootTasksFromCurrentRunningWorkflowWithDefinitionKey() {
        List<Task> allTasks = activitiService.getAllRootTasksFromCurrentRunningWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getAllRootTasksFromCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("all root tasks", allTasks)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "获取当前运行的指定的工作流的一个活跃任务", notes = "获取当前运行的指定的工作流的一个活跃任务")
    @PostMapping("/getOneActiveTaskFromCurrentRunningWorkflowWithDefinitionKey")
    public CommonResponse getOneActiveTaskFromCurrentRunningWorkflowWithDefinitionKey() {
        Task task = activitiService.getOneActiveTaskFromCurrentRunningWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getOneActiveTaskFromCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("one active task", task)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

//    @ApiOperation(value = "获取当前运行的指定的工作流的一个根任务", notes = "获取当前运行的指定的工作流的一个根任务")
//    @PostMapping("/getOneRootTaskFromCurrentRunningWorkflowWithDefinitionKey")
//    public CommonResponse getOneRootTaskFromCurrentRunningWorkflowWithDefinitionKey() {
//        Task task = activitiService.getOneRootTaskFromCurrentRunningWorkflowWithDefinitionKey();
//        log.info(LogUtils.getLogMessage("getOneRootTaskFromCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
//                .addParameter("one root task", task)));
//        return CommonResponse.builder()
//                .code(ResponseEnum.SUCCESS.getCode())
//                .message(ResponseEnum.SUCCESS.getMessage())
//                .data(Collections.singletonList(logInfo))
//                .systemTime(SystemUtils.currentDateTimeString())
//                .build();
//    }

    @ApiOperation(value = "获取当前运行的指定的工作流的当前任务", notes = "获取当前运行的指定的工作流的当前任务")
    @PostMapping("/getCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey")
    public CommonResponse getCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey() {
        Task task = activitiService.getCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("getCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey", new ParametersToLog()
                .addParameter("current task", task)));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "完成当前运行的指定的工作流的当前任务", notes = "完成当前运行的指定的工作流的当前任务")
    @PostMapping("/completeCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey")
    public CommonResponse completeCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey() {
        activitiService.completeCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey();
        log.info(LogUtils.getLogMessage("completeCurrentTaskFromCurrentRunningWorkflowWithDefinitionKey", "Complete current task from current running workflow with definition key"));
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }

    @ApiOperation(value = "探索用", notes = "探索用")
    @PostMapping("/forExploration")
    public CommonResponse forExploration() {
        activitiService.forExploration();
        return CommonResponse.builder()
                .code(ResponseEnum.SUCCESS.getCode())
                .message(ResponseEnum.SUCCESS.getMessage())
                .data(Collections.singletonList(logInfo))
                .systemTime(SystemUtils.currentDateTimeString())
                .build();
    }
}
