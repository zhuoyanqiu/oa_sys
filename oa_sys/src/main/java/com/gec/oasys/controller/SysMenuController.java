package com.gec.oasys.controller;

import com.gec.oasys.pojo.SysMenu;
import com.gec.oasys.pojo.SysUser;
import com.gec.oasys.service.SysMenuService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 卓炎秋
 * @date : 2020-04-20 17:42
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 根据角色id获取菜单树形组件所需的json数据
     * @param sysMenu
     * @return
     */
    @RequestMapping("/getMenuDataTreeByRoleIdJson")
    @ResponseBody
    public List<SysMenu> getMenuDataTreeByRoleIdJson(SysMenu sysMenu){
        sysMenu.setId(75);
        return sysMenuService.getAllMenuListTreeByRole(sysMenu);
    }

    /**
     * 获取所有的菜单数据（根具体角色无关），以树形结构的json数据返回
     * 遍历全部的menu数据也是以树型结构呈现
     * @param sysMenu
     * @return
     */
    @RequestMapping("/getMenuDataTreeJson")
    @ResponseBody
    public List<SysMenu> getMenuDataTreeJson(SysMenu sysMenu){
        //默认菜单的顶级id
        sysMenu.setId(0);
        return sysMenuService.getAllMenuListTree(sysMenu);
    }

    /**
     * 访问菜单列表页面
     * @return
     */
    @RequestMapping("/toMenuList")
    public String toMenuList(){
        return "menu/menuList";
    }

    /**
     * 获取菜单列表数据
     * @param sysMenu
     * @return
     */
    @RequestMapping("/getMenuData")
    @ResponseBody
    @Deprecated
    public Map<String,Object> getMenuData(SysMenu sysMenu){
        if (sysMenu.getId()!=null && sysMenu.getId()==75){
            sysMenu.setId(null);
            sysMenu.setTitle(null);
        }
        System.out.println("title="+sysMenu.getTitle());
        Map<String,Object> map = new HashMap<>();
        List<SysMenu> sysMenuList = sysMenuService.query(sysMenu);
        map.put("msg","");
        map.put("code",0);
        map.put("data",sysMenuList);
        return map;
    }

    /**
     * 获取分页后的菜单列表数据渲染前端表格
     * @param sysMenu
     * @return
     */
    @RequestMapping("/getMenuDataByPage")
    @ResponseBody
    public Map<String,Object> getMenuDataByPage(SysMenu sysMenu){
        System.out.println(sysMenu.getLimit());
        if (sysMenu.getId()!=null && sysMenu.getId()==75){
            sysMenu.setId(null);
            sysMenu.setTitle(null);
        }
        System.out.println("title="+sysMenu.getTitle());
        Map<String,Object> map = new HashMap<>();
        List<SysMenu> sysMenuList = sysMenuService.queryByPage(sysMenu);
        Long count = sysMenuService.getCountBySysMenu(sysMenu);
        System.out.println(count);
        map.put("msg","");
        map.put("code",0);
        map.put("count",count);
        map.put("data",sysMenuList);
        return map;
    }

    /**
     * 跳转菜单添加页面
     * @return
     */
    @RequestMapping("/toMenuForm")
    public String toMenuForm(){
        return "menu/menuForm";
    }

    /**
     * 获取添加菜单页面的父级菜单树形下拉框的数据（未完成：数据显示undefined）
     * @param sysMenu
     * @return
     */
    @RequestMapping("/treeDataJson")
    @ResponseBody
    public List<SysMenu> treeDataJson(SysMenu sysMenu){
        return sysMenuService.treeDataJson(sysMenu.getId());
    }


    /**
     * 添加提交菜单数据到数据库
     * @param sysMenu
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(SysMenu sysMenu){
        String result = "1";
        try {
            int flag = sysMenuService.insertSelective(sysMenu);
            if (flag>0){
                result="0";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据具体权限获取顶部一级菜单
     * @return
     */
    @RequestMapping("/getMenuTop")
    @ResponseBody
    public List<SysMenu> getMenuTop(){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        SysMenu sysMenu = new SysMenu();
        sysMenu.setIsTop("1");
        sysMenu.setRoleId(sysUser.getRoleId());
        return sysMenuService.query(sysMenu);
    }

    /**
     * 显示左侧树形菜单
     * @param sysMenu
     * @return
     */
    @RequestMapping("/getMenuByPidRec")
    @ResponseBody
    public List<SysMenu> getMenuByPidRec(SysMenu sysMenu){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysMenu.setRoleId(sysUser.getRoleId());
        return sysMenuService.queryNavMenuTree(sysMenu);

    }

    /**
     * 实现默认首页导航菜单栏显示
     * @return
     */
    @RequestMapping("/getTopMenuFirst")
    @ResponseBody
    public String getTopMenuFirst(){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        SysMenu sysMenu = new SysMenu();
        sysMenu.setIsTop("1");
        sysMenu.setRoleId(sysUser.getRoleId());
        List<SysMenu> sysMenuList = sysMenuService.query(sysMenu);
        if (sysMenuList!=null && sysMenuList.size()>0){
            return String.valueOf(sysMenuList.get(0).getId());
        }
        return null;
    }



}




















