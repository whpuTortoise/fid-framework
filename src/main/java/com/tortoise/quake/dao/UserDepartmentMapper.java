package com.tortoise.quake.dao;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.UserDepartmentEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDepartmentMapper extends BaseMapper<UserDepartmentEntity> {
    /**
     * 根据用户ID删除所属部门
     */
    int deleteByUserId(Long userId);
}