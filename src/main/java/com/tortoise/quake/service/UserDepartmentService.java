package com.tortoise.quake.service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.UserDepartmentMapper;
import com.tortoise.quake.model.UserDepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDepartmentService extends BaseService<UserDepartmentEntity, UserDepartmentMapper> {

	@Autowired
	public void setMapper(UserDepartmentMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 根据用户ID删除归属机构
	 */
	public int deleteByUserId(Long userId){

		return mapper.deleteByUserId(userId);
	}

	/**
	 * 根据机构ID删除归属机构
	 */
	public int deleteByDepartmentId(Long departmentId){

		return mapper.deleteByDepartmentId(departmentId);
	}

	/**
	 * 根据用户IdList批量删除归属关系
	 */
	public int batchDeleteByUserIdList(List userIdList){

		if (userIdList != null && userIdList.size() > 0) {
			return mapper.batchDeleteByUserIdList(userIdList);
		} else {
			return 0;
		}

	}
}
