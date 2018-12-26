package com.tortoise.quake.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.UserRoleEntity;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {
    /**
     * 根据用户ID删除角色
     */
    int deleteByUserId(Long userId);


    /**
     * 批量用户IdList批量删除
     */
    int batchDeleteByUserIdList(List userIds);

    /**
     * 批量角色IdList批量删除
     */
    int batchDeleteByRoleIdList(List roleIds);

    /**
     * 根据map删除
     */
    int delete(UserRoleEntity entity);
}