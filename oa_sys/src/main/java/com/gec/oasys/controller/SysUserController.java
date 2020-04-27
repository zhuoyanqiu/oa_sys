package com.gec.oasys.controller;

import com.gec.oasys.dao.SysDepartmentMapper;
import com.gec.oasys.pojo.SysRole;
import com.gec.oasys.pojo.SysUser;
import com.gec.oasys.service.SysRoleService;
import com.gec.oasys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 卓炎秋
 * @date : 2020-04-14 21:46
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysDepartmentMapper sysDepartmentMapper;

    @RequestMapping("/toSysUserList")
    public String toSysUserList(){
       /* List<SysUser> sysUserList = sysUserService.query(null);
        List<String> statusList = new ArrayList<>();
        for (SysUser sysUser : sysUserList) {
            statusList.add(sysUser.getStatus());
        }
        model.addAttribute("statusList",sysUserList);*/
        return "user/sysUserList";
    }

    /**
     * 获取用户列表
     * @param sysUser
     * @param model
     * @return
     */
    @RequestMapping("/getSysUserList")
    @ResponseBody
    public Map<String,Object> getSysUserList(SysUser sysUser,Model model){
        HashMap<String, Object> map = new HashMap<>();
        List<SysUser> sysUserList = sysUserService.query(sysUser);
        map.put("code",0);
        map.put("msg","");
        map.put("data",sysUserList);
        model.addAttribute("sysUser",sysUser);
        return map;
    }

    /**
     * 获取分页后的用户列表
     * @return
     */
    @RequestMapping("/getSysUserListByPage")
    @ResponseBody
    public Map<String,Object> getSysUserListByPage(SysUser sysUser){
        HashMap<String, Object> map = new HashMap<>();
        List<SysUser> sysUserList = sysUserService.queryByPage(sysUser);
        Long count = sysUserService.getCountByUser(sysUser);
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",sysUserList);
        return map;
    }


    /**
     * 访问添加用户页面
     * @return
     */
    @RequestMapping("/toAddSysUser")
    public String toAddSysUser(Model model){
        List<SysRole> sysRoleList = sysRoleService.query(new SysRole());
        model.addAttribute("sysRoleList",sysRoleList);
        return "user/sysUserAdd";
    }

    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    @RequestMapping("/saveSysUser")
    @ResponseBody
    public String saveSysUser(SysUser sysUser){
        String ajaxReturnData ="1";
        System.out.println("img="+sysUser.getImg());
        try {
            sysUserService.insertSelective(sysUser);
            ajaxReturnData = "0";
        }catch (Exception e){
            e.printStackTrace();
        }
        return ajaxReturnData;
    }

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public String deleteBatch(Integer[] ids){
        String ajaxReturnData = "1";
        int flag = sysUserService.deleteBatch(ids);
        if (flag==0){
            ajaxReturnData = "0";
        }
        return ajaxReturnData;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        String ajaxReturnData = "1";
        int flag = sysUserService.deleteByPrimaryKey(id);
        if (flag>0){
            ajaxReturnData = "0";
        }
        return ajaxReturnData;
    }

    /**
     * 转发到编辑用户页面，并携带用户信息和角色列表数据
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toEditSysUser")
    public String toEditSysUser(Integer id, Model model){
        SysUser sysUser = sysUserService.selectByPrimaryKey(id);
        List<SysRole> sysRoleList = sysRoleService.query(new SysRole());
        model.addAttribute("sysRoleList",sysRoleList);
        model.addAttribute("sysUser",sysUser);
        return "user/sysUserEdit";
    }

    /**
     * 编辑后保存用户信息
     * @param sysUser
     * @return
     */
    @RequestMapping("/updateSaveSysUser")
    @ResponseBody
    public String updateSaveSysUser(SysUser sysUser){
        String ajaxReturnData ="1";
        try {
            sysUserService.updateByPrimaryKeySelective(sysUser);
            ajaxReturnData = "0";
        }catch (Exception e){
            e.printStackTrace();
        }
        return ajaxReturnData;
    }

    /**
     * 显示个人信息
     * @param model
     * @return
     */
    @RequestMapping("/personInfo")
    public String getUserById(Model model){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("sysUser",sysUser);
        return "user/personInfo";
    }

    /**
     * 修改用户信息
     * @param sysUser
     * @return
     */
    @RequestMapping("/updateSysUser")
    @ResponseBody
    public String updateSysUser(SysUser sysUser){
        String ajaxReturnData ="1";
        try {
            int flag = sysUserService.updateByPrimaryKeySelective(sysUser);
            if (flag>0){
            ajaxReturnData = "0";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ajaxReturnData;
    }


}
