package com.gec.oasys.controller;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : 卓炎秋
 * @date : 2020-04-24 15:23
 */
@Controller
public class RepositoryActController {

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("/deloy")
    @ResponseBody
    public String deloyBpmnFile(){
        return repositoryService.createDeployment().addClasspathResource("leaveflow.bpmn").deploy().getId();
    }

}
