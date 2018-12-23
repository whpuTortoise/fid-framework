package com.tortoise.quake.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.Authority;
import com.tortoise.quake.model.Menu;

@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
	
	/**
	 * 根据角色ID删除权限
	 */
	int deleteByRoleId(Long roleId);

	/**
	 * 根据菜单ID删除权限
	 */
	int deleteByMenuId(Long menuId);

	/**
	 * 根据角色ID获取菜单权限列表
	 * @param roleIds
	 * @return
	 */
	List<Menu> getMenusByRoles(@Param("roleIds") List roleIds);
	
}