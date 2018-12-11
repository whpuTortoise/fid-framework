package com.tortoise.quake.model;

import java.io.Serializable;

public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色ID
     **/
    private Long roleId;

    /**
     * 菜单ID
     **/
    private Long menuId;

    
    public Authority() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuId() {
        return this.menuId;
    }

}