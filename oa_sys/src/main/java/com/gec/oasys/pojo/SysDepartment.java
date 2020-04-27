package com.gec.oasys.pojo;

import java.util.List;

public class SysDepartment {
    private Integer id;

    private Integer parentdepartmentid;

    private String name;

    private Integer sort;

    private String description;

    private String type;

    private String area;

    private List<SysDepartment> children;

    public SysDepartment(Integer id, Integer parentdepartmentid, String name, Integer sort, String description, String type, String area, List<SysDepartment> children) {
        this.id = id;
        this.parentdepartmentid = parentdepartmentid;
        this.name = name;
        this.sort = sort;
        this.description = description;
        this.type = type;
        this.area = area;
        this.children = children;
    }

    public SysDepartment(Integer id, Integer parentdepartmentid, String name, Integer sort, String description, String type, String area) {
        this.id = id;
        this.parentdepartmentid = parentdepartmentid;
        this.name = name;
        this.sort = sort;
        this.description = description;
        this.type = type;
        this.area = area;
    }

    public SysDepartment() {
        super();
    }

    public List<SysDepartment> getChildren() {
        return children;
    }

    public void setChildren(List<SysDepartment> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentdepartmentid() {
        return parentdepartmentid;
    }

    public void setParentdepartmentid(Integer parentdepartmentid) {
        this.parentdepartmentid = parentdepartmentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    @Override
    public String toString() {
        return "SysDepartment{" +
                "id=" + id +
                ", parentdepartmentid=" + parentdepartmentid +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", area='" + area + '\'' +
                ", children=" + children +
                '}';
    }
}