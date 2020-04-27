package com.gec.oasys.service;

import com.gec.oasys.pojo.Leave;

import java.util.List;

/**
 * 工作流程服务接口
 * @author : 卓炎秋
 * @date : 2020-04-24 21:41
 */

public interface WorkFlowService {
    /**
     * 启动流程实例
     * @param processDefinitionKey
     * @param leave
     * @return
     */
    String startFlowInstance(String processDefinitionKey, Leave leave);

    /**
     * 获取代办任务列表
     * @param leave
     * @return List<Leave>
     */
    List<Leave> getTaskList(Leave leave);

    /**
     * 办理任务
     * @param leave
     */
    void completeTask(Leave leave);

    public Leave getVarsByTaskId(String taskId);


}
