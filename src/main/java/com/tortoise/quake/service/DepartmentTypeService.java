package com.tortoise.quake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.DepartmentTypeMapper;
import com.tortoise.quake.model.DepartmentType;


@Service
public class DepartmentTypeService extends BaseService<DepartmentType, DepartmentTypeMapper> {

	@Autowired
	public void setMapper(DepartmentTypeMapper mapper) {
		this.mapper = mapper;
	}

}
