package com.tortoise.quake.vo;

import java.util.List;

import com.tortoise.quake.model.Department;

public class DepartmentVo extends Department{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 子节点
	 */
	private List<DepartmentVo> children;
	
	
	public List<DepartmentVo> getChildren() {
		return children;
	}

	public void setChildren(List<DepartmentVo> children) {
		this.children = children;
	}


	
	
	

}
