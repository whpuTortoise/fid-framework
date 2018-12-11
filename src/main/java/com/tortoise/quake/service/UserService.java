package com.tortoise.quake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.UserMapper;
import com.tortoise.quake.model.User;


@Service
public class UserService extends BaseService<User, UserMapper> {

	@Autowired
	public void setMapper(UserMapper mapper) {
		this.mapper = mapper;
	}

}
