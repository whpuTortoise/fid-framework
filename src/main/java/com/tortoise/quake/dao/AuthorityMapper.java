package com.tortoise.quake.dao;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.Authority;

public interface AuthorityMapper extends BaseMapper<Authority> {
	
	/**
	 * 根据角色ID删除权限
	 */
	int deleteByRoleId(Long roleId);
}