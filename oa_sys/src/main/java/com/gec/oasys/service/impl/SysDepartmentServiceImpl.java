package com.gec.oasys.service.impl;

import com.gec.oasys.dao.SysDepartmentMapper;
import com.gec.oasys.pojo.SysDepartment;
import com.gec.oasys.service.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : 卓炎秋
 * @date : 2020-04-10 18:16
 */
@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysDepartmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysDepartment record) {
        return sysDepartmentMapper.insert(record);
    }

    @Override
    public int insertSelective(SysDepartment record) {
        return sysDepartmentMapper.insertSelective(record);
    }

    @Override
    public SysDepartment selectByPrimaryKey(Integer id) {
        return sysDepartmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysDepartment record) {
        return sysDepartmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysDepartment record) {
        return sysDepartmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysDepartment> query(SysDepartment record) {
        return sysDepartmentMapper.query(record);
    }

    @Override
    public List<SysDepartment> querySysDepartmentByPid(Integer pid) {
        return sysDepartmentMapper.querySysDepartmentByPid(pid);
    }

    @Override
    public List<SysDepartment> treeDataJson(int pid) {
        List<SysDepartment> sysDepartmentList = querySysDepartmentByPid(pid);
        diguiTree(sysDepartmentList);
        return sysDepartmentList;
    }

    private void diguiTree(List<SysDepartment> sysDepartmentList) {
        for (SysDepartment sysDepartment : sysDepartmentList) {
            Integer pid = sysDepartment.getId();
            List<SysDepartment> sysChildDepartMentList = querySysDepartmentByPid(pid);
            if (sysChildDepartMentList!=null && sysChildDepartMentList.size()>0){
                sysDepartment.setChildren(sysChildDepartMentList);
                diguiTree(sysChildDepartMentList);
            }
        }
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        int flag =1;
        try {
            if (ids!=null && ids.length>0){
                for (Integer id : ids) {
                    sysDepartmentMapper.deleteByPrimaryKey(id);
                }
                flag=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

}
