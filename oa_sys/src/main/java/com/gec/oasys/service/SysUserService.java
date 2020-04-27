package com.gec.oasys.service;

import com.gec.oasys.pojo.SysUser;

import java.util.List;

/**
 * @author : 卓炎秋
 * @date : 2020-04-14 21:41
 */
public interface SysUserService {

    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> query(SysUser record);

    int deleteBatch(Integer[] ids);

    Long getCountByUser(SysUser record);

    List<SysUser> queryByPage(SysUser record);

    SysUser querySysUserByLoginNameAndPwd(SysUser record);

}
