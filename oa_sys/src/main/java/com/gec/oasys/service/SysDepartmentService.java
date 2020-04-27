package com.gec.oasys.service;

import com.gec.oasys.pojo.SysDepartment;

import java.util.List;

/**
 * @author : 卓炎秋
 * @date : 2020-04-10 18:05
 */
public interface SysDepartmentService {

    int deleteByPrimaryKey(Integer id);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    SysDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);

    List<SysDepartment> query(SysDepartment record);

    List<SysDepartment> querySysDepartmentByPid(Integer pid);

    List<SysDepartment> treeDataJson(int pid);

    int deleteBatch(Integer[] ids);

}
