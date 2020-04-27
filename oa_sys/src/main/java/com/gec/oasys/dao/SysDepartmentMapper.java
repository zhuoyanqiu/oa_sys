package com.gec.oasys.dao;

import com.gec.oasys.pojo.SysDepartment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    SysDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);

    List<SysDepartment> query(SysDepartment record);

    List<SysDepartment> querySysDepartmentByPid(Integer pid);

}