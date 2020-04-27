package com.gec.oasys.controller;

import com.alibaba.fastjson.JSONArray;
import com.gec.oasys.pojo.SysMenu;
import com.gec.oasys.pojo.SysRole;
import com.gec.oasys.service.SysMenuService;
import com.gec.oasys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 卓炎秋
 * @date : 2020-04-20 12:00
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 访问角色列表页面
     * @return
     */
    @RequestMapping("/toSysRoleList")
    public String toSysRoleList(){
        return "role/roleList";
    }

    /**
     * 获取角色列表数据渲染前端表格
     * @param sysRole
     * @return
     */
    @RequestMapping("getSysRoleData")
    @ResponseBody
    public Map<String,Object> getSysRoleData(SysRole sysRole){
        List<SysRole> sysRoleList = sysRoleService.query(sysRole);
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("data",sysRoleList);
        return map;
    }

    /**
     * 保存用户权限
     * @param roleId
     * @param checkedMenuTreeDataJson
     * @return
     */
    @RequestMapping("/savePermission")
    @ResponseBody
    public String savePermission(Integer roleId, @RequestBody String checkedMenuTreeDataJson){
        System.out.println("roleId="+roleId);
        System.out.println(checkedMenuTreeDataJson);
        String result = "1";
        JSONArray jsonArray = JSONArray.parseArray(checkedMenuTreeDataJson);
        SysMenu[] sysMenuArrays = new SysMenu[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            SysMenu sysMenu = jsonArray.getObject(i, SysMenu.class);
            sysMenuArrays[i]=sysMenu;
        }
        try {
            sysMenuService.addCheckedMenuTreeData(sysMenuArrays,roleId);
            result="0";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 访问添加角色表单页面
     * @return
     */
    @RequestMapping("/toRoleForm")
    public String toRoleForm(){
        return "role/roleForm";
    }

    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(SysRole sysRole){
        String result = "1";
        try {
            int flag = sysRoleService.insertSelective(sysRole);
            if (flag>0){
                result="0";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }



}
