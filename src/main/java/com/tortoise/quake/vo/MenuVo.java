package com.tortoise.quake.vo;

import java.util.List;

import com.tortoise.quake.model.Menu;

public class MenuVo extends Menu{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 子节点
	 */
	private List<MenuVo> children;
	
	
	public List<MenuVo> getChildren() {
		return children;
	}

	public void setChildren(List<MenuVo> children) {
		this.children = children;
	}


	
	
	

}
