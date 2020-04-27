package com.gec.oasys.service.impl;

import com.gec.oasys.dao.SysUserMapper;
import com.gec.oasys.pojo.SysUser;
import com.gec.oasys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : 卓炎秋
 * @date : 2020-04-14 21:42
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public int insertSelective(SysUser record) {
        return sysUserMapper.insertSelective(record);
    }

    @Override
    public SysUser selectByPrimaryKey(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SysUser> query(SysUser record) {
        return sysUserMapper.query(record);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        int flag = 1;
        try {
            if (ids!=null && ids.length>0){
                for (Integer id : ids) {
                    sysUserMapper.deleteByPrimaryKey(id);
                }
                flag = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Long getCountByUser(SysUser record) {
        return sysUserMapper.getCountByUser(record);
    }

    @Override
    public List<SysUser> queryByPage(SysUser record) {
        return sysUserMapper.queryByPage(record);
    }

    @Override
    public SysUser querySysUserByLoginNameAndPwd(SysUser record) {
        return sysUserMapper.querySysUserByLoginNameAndPwd(record);
    }
}
