package com.tortoise.quake.model;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 菜单名称
     **/
    private String menuName;

    /**
     * 菜单编码
     **/
    private String menuCode;

    /**
     * 菜单图标
     **/
    private String menuIcon;

    /**
     * 跳转url
     **/
    private String targetUrl;

    /**
     * 菜单层级
     **/
    private Integer level;

    /**
     * 父级菜单ID
     **/
    private Long pid;

    /**
     * 创建人
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
    
    public Menu() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuCode() {
        return this.menuCode;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuIcon() {
        return this.menuIcon;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return this.pid;
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