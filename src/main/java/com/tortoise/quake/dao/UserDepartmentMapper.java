package com.tortoise.quake.dao;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.UserDepartmentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserDepartmentMapper extends BaseMapper<UserDepartmentEntity> {
    /**
     * 根据用户ID删除所属部门
     */
    int deleteByUserId(Long userId);

    /**
     * 批量用户IdList批量删除
     */
    int batchDeleteByUserIdList(List userIds);
}