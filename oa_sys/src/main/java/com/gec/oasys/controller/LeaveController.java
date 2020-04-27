package com.gec.oasys.controller;

import com.gec.oasys.pojo.Leave;
import com.gec.oasys.pojo.SysUser;
import com.gec.oasys.service.WorkFlowService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 卓炎秋
 * @date : 2020-04-25 13:00
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private WorkFlowService workFlowService;


    /**
     * 跳转到填写请假表单
     * @return path
     */
    @RequestMapping("toLeaveForm")
    public String toLeaveForm(){
        return "leave/leaveForm";
    }

    /**
     * 保存请假表单的数据
     * @param leave 表单提交数据
     * @return ajaxReturnData
     */
    @RequestMapping("/saveLeave")
    @ResponseBody
    public String saveLeave(Leave leave)
    {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        leave.setSysUser(sysUser);
        String ajaxReturnData="1";
        try {
            workFlowService.startFlowInstance("emp_leave", leave);
            ajaxReturnData="0";
        }catch (Exception e){
            e.printStackTrace();
        }
        return ajaxReturnData;
    }

    /**
     * 实现获取待办列表
     * @return path
     */
    @RequestMapping("/toTaskList")
    public String toTaskList()
    {
        return "leave/leaveTaskList";
    }

    @RequestMapping("/getLeaveTaskList")
    @ResponseBody
    public Map<String, Object> getLeaveTaskList(Leave leave){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        leave.setSysUser(sysUser);
        List<Leave> leaveList = workFlowService.getTaskList(leave);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","");
        map.put("code",0);
        map.put("data",leaveList);
        return map;
    }

    @RequestMapping("/getLeaveTaskCount")
    public String getLeaveTaskCount(Model model){
        Leave leave = new Leave();
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        leave.setSysUser(sysUser);
        List<Leave> leaveList = workFlowService.getTaskList(leave);
        leave.setTaskCount(leaveList.size());
        model.addAttribute("leave",leave);
        return "sys/main";
    }


    @RequestMapping("/completeTask")
    @ResponseBody
    public String completeTask(Leave leave)
    {
        System.out.println("taskId="+leave.getTaskId()+"  pass="+leave.getPass());
        String ajaxReturnData="1";
        //shiro身份认证对象获取user信息
        SysUser sysUser= (SysUser) SecurityUtils.getSubject().getPrincipal();
        leave.setSysUser(sysUser);

        try{
            workFlowService.completeTask(leave);
            ajaxReturnData="0";
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return ajaxReturnData;

    }

    @RequestMapping("/leaveEditSave")
    @ResponseBody
    public String leaveEditSave(Leave leave)
    {
        System.out.println("taskId="+leave.getTaskId()+"  pass="+leave.getPass());
        String ajaxReturnData="1";
        //shiro身份认证对象获取user信息
        SysUser sysUser= (SysUser) SecurityUtils.getSubject().getPrincipal();
        leave.setSysUser(sysUser);

        try{
            workFlowService.completeTask(leave);
            ajaxReturnData="0";
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return ajaxReturnData;

    }

    //根据taskId，获取流程变量的数据
    @RequestMapping("/getVarableByTaskId")
    public String getTaskVarable(Leave leave, Model model)
    {

        Leave outLeave=workFlowService.getVarsByTaskId(leave.getTaskId());

        model.addAttribute("leave",outLeave);

        return "leave/editLeaveForm";

    }


}
