package com.gec.oasys.controller;

import com.gec.oasys.pojo.AjaxResultJson;
import com.gec.oasys.pojo.SysUser;
import com.gec.oasys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import
        org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping("/toLoginForm")
    public String toLoginForm() {
        return "user/loginForm";
    }

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResultJson login(SysUser sysUser) {
        AjaxResultJson ajaxResultJson = new AjaxResultJson();

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(sysUser.getLoginName(), sysUser.getPwd());
        try {
            subject.login(usernamePasswordToken);
            ajaxResultJson.setCode("0");
            ajaxResultJson.setMsg("登录成功！");

        } catch (AuthenticationException e) {
            ajaxResultJson.setCode("1");
            ajaxResultJson.setMsg(e.getMessage());
        }
        return ajaxResultJson;
    }

    @RequestMapping("/index")
    public String index(){
        return "sys/index";
    }

    @RequestMapping("/logout")
    public void logout() {
        SecurityUtils.getSubject().logout();
    }

    @RequestMapping("/main")
    public String main(){
        return "sys/main";
    }


}