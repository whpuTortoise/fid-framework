package com.tortoise.quake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.UserRoleMapper;
import com.tortoise.quake.model.UserRole;

@Service
public class UserRoleService extends BaseService<UserRole, UserRoleMapper> {

	@Autowired
	public void setMapper(UserRoleMapper mapper) {
		this.mapper = mapper;
	}

	
	/**
	 * 根据用户ID删除用户_角色
	 */
	public int deleteByUserId(Long userId){
		return mapper.deleteByUserId(userId);
	}
	/**
	 * 根据角色ID删除用户_角色
	 */
	public int deleteByRoleId(Long roleId){
		return mapper.deleteByRoleId(roleId);
	}
}
