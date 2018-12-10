package com.tortoise.quake.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.Authority;

@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
	
	/**
	 * 根据角色ID删除权限
	 */
	int deleteByRoleId(Long roleId);
}