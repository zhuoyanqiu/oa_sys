package com.gec.oasys.service.impl;

import com.gec.oasys.dao.SysMenuMapper;
import com.gec.oasys.pojo.SysMenu;
import com.gec.oasys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysMenu record) {
        return sysMenuMapper.insert(record);
    }

    @Override
    public int insertSelective(SysMenu record) {
        return sysMenuMapper.insertSelective(record);
    }

    @Override
    public SysMenu selectByPrimaryKey(Integer id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysMenu record) {
        return sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysMenu record) {
        return sysMenuMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<SysMenu> queryMenuByRoleId(Integer roleId) {
        return sysMenuMapper.queryMenuByRoleId(roleId);
    }

    @Override
    public List<SysMenu> getMenuDataPidTreeByRoleId(Integer roleId, Integer id) {
        return sysMenuMapper.getMenuDataPidTreeByRoleId(roleId,id);
    }


    @Override
    public List<SysMenu> getAllMenuListTreeByRole(SysMenu sysMenu) {
        List<SysMenu> sysMenuList = getMenuDataPidTreeByRoleId(sysMenu.getRoleId(),sysMenu.getId());
        diguiMenuThreeByRoleId(sysMenuList,sysMenu.getRoleId());
        return sysMenuList;
    }

    @Override
    public void addCheckedMenuTreeData(SysMenu[] sysMenus, Integer roleId) {
        sysMenuMapper.deleteSysRoleMenuByRoleId(roleId);
        addDiguiTreeMenu(Arrays.asList(sysMenus),roleId);
    }

    @Override
    public List<SysMenu> query(SysMenu sysMenu) {
        return sysMenuMapper.query(sysMenu);
    }

    @Override
    public List<SysMenu> getAllMenuListTree(SysMenu sysMenu) {
        List<SysMenu> sysMenuList=getMenuDataTreeByPid(sysMenu.getId());
        System.out.println(sysMenuList.size());
        diguiMenuThree(sysMenuList);
        return sysMenuList;
    }

    @Override
    public Long getCountBySysMenu(SysMenu sysMenu) {
        return sysMenuMapper.getCountBySysMenu(sysMenu);
    }

    @Override
    public List<SysMenu> queryByPage(SysMenu sysMenu) {
        return sysMenuMapper.queryByPage(sysMenu);
    }

    @Override
    public List<SysMenu> treeDataJson(Integer pid) {
        List<SysMenu> sysMenuList = getMenuDataTreeByPid(pid);
        diguiMenuThree(sysMenuList);
        return sysMenuList;
    }

    @Override
    public List<SysMenu> queryNavMenuTree(SysMenu sysMenu) {
        List<SysMenu> rootSysMenu = queryMenuByPidAndRoleId(sysMenu);
        diguiNavMenuTree(rootSysMenu,sysMenu.getRoleId());
        return rootSysMenu;
    }

    @Override
    public List<SysMenu> queryMenuByPidAndRoleId(SysMenu sysMenu) {
        return sysMenuMapper.queryMenuByPidAndRoleId(sysMenu);
    }


    private void diguiNavMenuTree(List<SysMenu> rootSysMenu, Integer roleId) {
        for (SysMenu sysMenu : rootSysMenu) {
            sysMenu.setRoleId(roleId);
            List<SysMenu> subSysMenuList = queryMenuByPidAndRoleId(sysMenu);
            if (subSysMenuList!=null && subSysMenuList.size()>0){
                sysMenu.setChildren(subSysMenuList);
                diguiNavMenuTree(subSysMenuList,roleId);
            }
        }
    }


    public void  diguiMenuThree(List<SysMenu> list)
    {
        for (SysMenu sysMenu : list) {
            sysMenu.setName(sysMenu.getTitle());
            List<SysMenu> subSysMenuList=getMenuDataTreeByPid(sysMenu.getId());
            //当前菜单有子菜单
            if(subSysMenuList!=null && subSysMenuList.size()>0)
            {
                sysMenu.setChildren(subSysMenuList);
                diguiMenuThree(subSysMenuList);
            }


        }
    }

    private List<SysMenu> getMenuDataTreeByPid(Integer id) {
        return sysMenuMapper.getMenuDataTreeByPid(id);
    }


    private void addDiguiTreeMenu(List<SysMenu> asList, Integer roleId) {
        for (SysMenu sysMenu : asList) {
            sysMenuMapper.insertSysRoleMenu(roleId,sysMenu.getId());
            List<SysMenu> childSysMenuList = sysMenu.getChildren();
            if (childSysMenuList!=null && childSysMenuList.size()>0){
                addDiguiTreeMenu(childSysMenuList,roleId);
            }
        }
    }

    private void diguiMenuThreeByRoleId(List<SysMenu> sysMenuList, Integer roleId) {
        for (SysMenu sysMenu : sysMenuList) {
            List<SysMenu> subSysMenuList = getMenuDataPidTreeByRoleId(roleId, sysMenu.getId());
            if (subSysMenuList!=null && subSysMenuList.size()>0){
                sysMenu.setChecked(false);
                sysMenu.setChildren(subSysMenuList);
                diguiMenuThreeByRoleId(subSysMenuList,roleId);
            }
        }
    }


}
