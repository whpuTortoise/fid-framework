package com.tortoise.quake.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 角色名称
     **/
    private String roleName;

    /**
     * 角色编码
     **/
    private String roleCode;

    /**
     * 角色描述
     **/
    private String description;

    /**
     * 创建者
     **/
    private String createId;

    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 更新人
     **/
    private String updateId;

    /**
     * 更新时间
     **/
    private Date updateTime;

    /**
     * 是否删除
     **/
    private Integer isDeleted;

    
    public Role() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateId() {
        return this.createId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateId() {
        return this.updateId;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return this.isDeleted;
    }

}