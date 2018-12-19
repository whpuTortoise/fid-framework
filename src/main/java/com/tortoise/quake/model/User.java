package com.tortoise.quake.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     **/
    private String username;

    /**
     * 密码
     **/
    private String password;

    /**
     * 姓名
     **/
    private String realName;

    /**
     * 出生日期
     **/
    private String birthday;

    /**
     * 电话
     **/
    private String tel;

    /**
     * 更新人ID
     **/
    private String updateId;

    /**
     * 更新时间
     **/
    private Date updateTime;

    /**
     * 创建人ID
     **/
    private String createId;

    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 是否逻辑删除
     **/
    private Integer isDeleted;

    
    public User() {
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return this.realName;
    }



    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return this.tel;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return this.isDeleted;
    }

}