package com.tortoise.quake.model;

import java.io.Serializable;

public class UserDepartmentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户ID
     **/
    private Long userId;

    /**
     * 部门ID
     **/
    private Long departmentId;


    public UserDepartmentEntity() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}