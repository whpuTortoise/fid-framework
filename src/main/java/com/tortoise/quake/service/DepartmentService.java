package com.tortoise.quake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.DepartmentMapper;
import com.tortoise.quake.model.Department;

@Service
public class DepartmentService extends BaseService<Department, DepartmentMapper> {

	@Autowired
	public void setMapper(DepartmentMapper mapper) {
		this.mapper = mapper;
	}

}
