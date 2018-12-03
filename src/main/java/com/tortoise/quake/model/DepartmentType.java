package com.tortoise.quake.model;

import java.io.Serializable;

public class DepartmentType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     **/
    private String typeCode;

    /**
     * 密码
     **/
    private String typeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

  

}