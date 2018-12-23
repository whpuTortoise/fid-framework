package com.tortoise.quake.dao;

import com.tortoise.framework.dao.BaseMapper;
import com.tortoise.quake.model.UserDepartmentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserDepartmentMapper extends BaseMapper<UserDepartmentEntity> {
    /**
     * 根据用户ID删除所属机构
     */
    int deleteByUserId(Long userId);

    /**
     * 根据机构ID删除所属机构
     */
    int deleteByDepartmentId(Long departmentId);

    /**
     * 批量用户IdList批量删除
     */
    int batchDeleteByUserIdList(List userIds);
}