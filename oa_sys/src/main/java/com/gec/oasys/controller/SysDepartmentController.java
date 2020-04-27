package com.gec.oasys.controller;

import com.gec.oasys.pojo.SysDepartment;
import com.gec.oasys.service.SysDepartmentService;
import org.aspectj.weaver.loadtime.Aj;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 卓炎秋
 * @date : 2020-04-10 18:19
 */
@Controller
@RequestMapping("/sysDepartment")
public class SysDepartmentController {
    @Autowired
    private SysDepartmentService sysDepartmentService;

    /**
     * 转发到组织部门列表
     * @return String path
     */
    @RequestMapping("/toSysDepartmentList")
    public String toSysDepartmentList() {
        return "sys/sysDepartmentList";
    }

    /**
     * 实现表格获取数据源接口方法
     * @return Map<String, Object> map
     */
    @RequestMapping("/getSysDepartmentList")
    @ResponseBody
    public Map<String, Object> getDemoList(SysDepartment sysDepartment) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("部门名="+sysDepartment.getName());
        List<SysDepartment> sysDepartMentList = sysDepartmentService.query(sysDepartment);

        map.put("code", 0);
        map.put("msg", "");
        map.put("data", sysDepartMentList);
        return map;
    }

    /**
     * 跳转到添加部门界面
     * @return
     */
    @RequestMapping("/toAddSysDepartment")
    public String toAddSysDepartMent()
    {
        return "sys/sysDepartmentAdd";
    }

    @RequestMapping("/treeDataJson")
    @ResponseBody
    public List<SysDepartment> treeDataJson(SysDepartment sysDepartment){
        return sysDepartmentService.treeDataJson(sysDepartment.getId());
    }

    /**
     * 添加组织机构
     * @param sysDepartment
     * @return
     */
    @RequestMapping("/saveSysDepartment")
    @ResponseBody
    public String saveSysDepartment(SysDepartment sysDepartment){

        String ajaxReturnData = "1";
        try {
            sysDepartmentService.insertSelective(sysDepartment);
            ajaxReturnData="0";
        }catch (Exception e){
            e.printStackTrace();
        }
        return ajaxReturnData;
    }

    /**
     * 批量删除机构
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public String deleteBatch(Integer[] ids){
        String ajaxReturnData="1";
        System.out.println("deleteBatch ids="+ Arrays.toString(ids));
        int flag = sysDepartmentService.deleteBatch(ids);
        if (flag==0){
            ajaxReturnData="0";
        }
        return ajaxReturnData;

    }

    /**
     * 删除机构
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String deleteOne(int id){
        String ajaxReturnData ="1";
        int flag = sysDepartmentService.deleteByPrimaryKey(id);
        if (flag>0){
            ajaxReturnData="0";
        }
        return ajaxReturnData;
    }

    /**
     * 访问编辑组织机构页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toEditSysDepartment")
    public String toEditSysDepartment(Integer id, Model model){
        SysDepartment sysDepartment = sysDepartmentService.selectByPrimaryKey(id);
        model.addAttribute("sysDepartment",sysDepartment);
        return "sys/sysDepartmentEdit";
    }

    /**
     * 保存编辑组织机构页面的数据
     * @param sysDepartment
     * @return
     */
    @RequestMapping("/updateSaveSysDepartment")
    @ResponseBody
    public String updateSaveSysDepartment(SysDepartment sysDepartment){
        String ajaxReturnData = "1";
        try {
            sysDepartmentService.updateByPrimaryKeySelective(sysDepartment);
            ajaxReturnData="0";
        }catch (Exception e){
            e.printStackTrace();
        }
        return ajaxReturnData;
    }



}
