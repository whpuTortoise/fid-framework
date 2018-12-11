package com.tortoise.quake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.RoleMapper;
import com.tortoise.quake.model.Role;

@Service
public class RoleService extends BaseService<Role, RoleMapper> {

	@Autowired
	public void setMapper(RoleMapper mapper) {
		this.mapper = mapper;
	}

}
