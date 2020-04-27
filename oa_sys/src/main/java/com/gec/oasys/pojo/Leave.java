package com.gec.oasys.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Leave implements Serializable {

    private String taskId;
    private String taskName;
    private Integer taskCount;
    private String title;
    private String reason;
    private Integer days;

    private SysUser sysUser;
    private SysRole sysRole;

    private Boolean pass;

    Map<String,Object> vars=new HashMap<>();

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public Map<String, Object> getVars() {
        return vars;
    }

    public void setVars(Map<String, Object> vars) {
        this.vars = vars;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", title='" + title + '\'' +
                ", reason='" + reason + '\'' +
                ", days=" + days +
                ", sysUser=" + sysUser +
                ", sysRole=" + sysRole +
                ", pass=" + pass +
                ", vars=" + vars +
                '}';
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

}
