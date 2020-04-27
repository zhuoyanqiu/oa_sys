package com.gec.oasys.service;

import com.gec.oasys.pojo.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 卓炎秋
 * @date : 2020-04-15 21:04
 */
public interface SysRoleService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> query(SysRole record);
}
