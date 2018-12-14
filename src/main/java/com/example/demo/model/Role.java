package com.example.demo.model;

import java.util.List;

public class Role {
    private Integer id;

    private String rolename;

    private List<RolePermission> rolePermissionList;

    public List<RolePermission> getRolePermissionList() {
        return rolePermissionList;
    }

    public void setRolePermissionList(List<RolePermission> rolePermissionList) {
        this.rolePermissionList = rolePermissionList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }
}