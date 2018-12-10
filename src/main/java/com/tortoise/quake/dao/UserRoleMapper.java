package com.tortoise.quake.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.UserRole;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
	/**
	 * 根据用户ID删除
	 * @param userId
	 * @return
	 */
	int deleteByUserId(Long userId);
	/**
	 * 根据角色ID删除
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Long roleId);
	
}