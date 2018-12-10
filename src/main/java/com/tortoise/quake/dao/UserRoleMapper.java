package com.tortoise.quake.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.UserRoleEntity;


@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

}