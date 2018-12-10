package com.tortoise.quake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.UserDepartmentMapper;
import com.tortoise.quake.model.UserDepartment;

@Service
public class UserDepartmentService extends BaseService<UserDepartment, UserDepartmentMapper> {

	@Autowired
	public void setMapper(UserDepartmentMapper mapper) {
		this.mapper = mapper;
	}
	/**
	 * 根据用户ID删除用户机构关联
	 */
	public int deleteByUserId(Long userId){
		return mapper.deleteByUserId(userId);
	}
	

}
