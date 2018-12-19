package com.tortoise.quake.vo;

import com.tortoise.quake.model.User;

/**
 * Created by Pp on 2018-12-19
 */
public class UserVo extends User {
    private String departmentId;
    private String roleIds;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
