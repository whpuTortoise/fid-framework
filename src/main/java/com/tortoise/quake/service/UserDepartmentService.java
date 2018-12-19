package com.tortoise.quake.service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.UserDepartmentMapper;
import com.tortoise.quake.model.UserDepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDepartmentService extends BaseService<UserDepartmentEntity, UserDepartmentMapper> {

	@Autowired
	public void setMapper(UserDepartmentMapper mapper) {
		this.mapper = mapper;
	}

}
