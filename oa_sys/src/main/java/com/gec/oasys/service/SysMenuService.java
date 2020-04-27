package com.gec.oasys.service;

import com.gec.oasys.pojo.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : 卓炎秋
 * @date : 2020-04-17 18:20
 */
public interface SysMenuService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> queryMenuByRoleId(Integer roleId);

    List<SysMenu> getMenuDataPidTreeByRoleId(Integer roleId,Integer id);

    List<SysMenu> getAllMenuListTreeByRole(SysMenu sysMenu);

    void addCheckedMenuTreeData(SysMenu[] sysMenus,Integer roleId);

    List<SysMenu> query(SysMenu sysMenu);

    List<SysMenu> getAllMenuListTree(SysMenu sysMenu);

    Long getCountBySysMenu(SysMenu sysMenu);

    List<SysMenu> queryByPage(SysMenu sysMenu);

    List<SysMenu> treeDataJson(Integer id);

    List<SysMenu> queryNavMenuTree(SysMenu sysMenu);

    List<SysMenu> queryMenuByPidAndRoleId(SysMenu sysMenu);
}
