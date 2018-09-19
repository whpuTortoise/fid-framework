package com.tortoise.quake.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.Department;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}