package com.tortoise.quake.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.UserDepartment;

@Mapper
public interface UserDepartmentMapper extends BaseMapper<UserDepartment> {
	/**
	 * 根据用户ID删除
	 * @param userId
	 * @return
	 */
	int deleteByUserId(Long userId);
	
}