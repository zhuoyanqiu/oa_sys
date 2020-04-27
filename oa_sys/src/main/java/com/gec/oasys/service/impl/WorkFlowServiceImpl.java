package com.gec.oasys.service.impl;

import com.gec.oasys.pojo.Leave;
import com.gec.oasys.service.WorkFlowService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 卓炎秋
 * @date : 2020-04-24 21:51
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @Override
    public String startFlowInstance(String processDefinitionKey, Leave leave) {
        identityService.setAuthenticatedUserId(leave.getSysUser().getLoginName());
        Map<String,Object> variables = new HashMap<>();
        variables.put("title",leave.getTitle());
        variables.put("reason",leave.getReason());
        variables.put("days",leave.getDays());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        return processInstance.getId();
    }

    @Override
    public List<Leave> getTaskList(Leave leave) {
        List<Leave> leaveList=new ArrayList<>();

        String candidateGroup="";

        Integer roleId=leave.getSysUser().getRoleId();

        //根据流程图已经固定角色字符串
        //dept_master等于部门主管
        //dept_master等于部门主管
        //dept_leader等于部门领导
        //company_manager等于总经理
        //hr等于人事经理
        if(roleId!=null && roleId ==9)
        {
            candidateGroup="dept_master";
        }else if(roleId!=null && roleId ==10)
        {
            candidateGroup="dept_leader";
        }else if(roleId!=null && roleId ==11)
        {
            candidateGroup="company_manager";
        }else if(roleId!=null && roleId ==12)
        {
            candidateGroup="hr";
        }else if(roleId!=null && roleId==8){
            candidateGroup="emp";
        }

        List<Task> taskList=new ArrayList<>();
        if ("emp".equals(candidateGroup)){
            taskList = taskService.createTaskQuery().taskAssignee(leave.getSysUser().getLoginName()).active().orderByTaskCreateTime().desc().list();
        }else{
            taskList = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).active().orderByTaskCreateTime().desc().list();
        }

        for (Task task : taskList) {
            Leave inLeave = new Leave();
            inLeave.setTitle((String) taskService.getVariable(task.getId(), "title"));
            inLeave.setReason((String) taskService.getVariable(task.getId(),"reason"));
            inLeave.setDays((Integer) taskService.getVariable(task.getId(),"days"));
            inLeave.setTaskId(task.getId());
            inLeave.setTaskName(task.getName());
            leaveList.add(inLeave);
        }
        return leaveList;
    }

    @Override
    public void completeTask(Leave leave) {
        String gatewayName="";

        Integer roleId=leave.getSysUser().getRoleId();
        if(roleId!=null && roleId ==9)
        {
            gatewayName="deptmasterpass";
        }else if(roleId!=null && roleId ==10)
        {
            gatewayName="deptleaderpass";
        }else if(roleId!=null && roleId ==11)
        {
            gatewayName="managerpass";
        }else if(roleId!=null && roleId ==12)
        {
            gatewayName="hrpass";
        }else if(roleId!=null && roleId==8){
            gatewayName="emppass";
        }
        Map<String,Object> vars=new HashMap<>();
        vars.put(gatewayName,leave.getPass());

        if(roleId!=null && roleId==8) {

            Task task=taskService.createTaskQuery().taskId(leave.getTaskId()).singleResult();

            if(task.getName().equals("调整申请"))
            {
                System.out.println("task name="+task.getName());
                vars.put("title", leave.getTitle());
                vars.put("reason", leave.getReason());
                vars.put("days", leave.getDays());
            }

        }

        taskService.complete(leave.getTaskId(),vars);
    }

    @Override
    public Leave getVarsByTaskId(String taskId) {

        String title= (String) taskService.getVariable(taskId,"title");
        String reason= (String) taskService.getVariable(taskId,"reason");
        Integer days= (Integer) taskService.getVariable(taskId,"days");

        Leave outLeave=new Leave();
        outLeave.setTaskId(taskId);
        outLeave.setTitle(title);
        outLeave.setReason(reason);
        outLeave.setDays(days);

        return outLeave;
    }
}
