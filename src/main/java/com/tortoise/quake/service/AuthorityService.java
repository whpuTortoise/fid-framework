package com.tortoise.quake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.AuthorityMapper;
import com.tortoise.quake.model.Authority;
import com.tortoise.quake.model.Menu;

@Service
public class AuthorityService extends BaseService<Authority, AuthorityMapper> {

	@Autowired
	public void setMapper(AuthorityMapper mapper) {
		this.mapper = mapper;
	}

	
	/**
	 * 根据角色ID删除权限
	 */
	public int deleteByRoleId(Long roleId){
		return mapper.deleteByRoleId(roleId);
	}

	/**
	 * 根据菜单ID删除权限
	 */
	public int deleteByMenuId(Long menuId){
		return mapper.deleteByMenuId(menuId);
	}


	/**
	 * 根据角色ID获取菜单权限列表
	 * @param roleIds
	 * @return
	 */
	public List<Menu> getMenusByRoles(List<Long> roleIds){
		return mapper.getMenusByRoles(roleIds);
	}
	
	
}
