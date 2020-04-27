package com.gec.oasys.dao;

import com.gec.oasys.pojo.SysDepartment;
import com.gec.oasys.pojo.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> query(SysUser record);

    Long getCountByUser(SysUser record);

    List<SysUser> queryByPage(SysUser record);

    SysUser querySysUserByLoginNameAndPwd(SysUser record);

}