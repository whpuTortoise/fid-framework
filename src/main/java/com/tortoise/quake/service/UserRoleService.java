package com.tortoise.quake.service;

import com.tortoise.framework.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.UserRoleMapper;
import com.tortoise.quake.model.UserRoleEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserRoleService extends BaseService<UserRoleEntity, UserRoleMapper> {

	@Autowired
	public void setMapper(UserRoleMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 根据用户ID删除角色
	 */
	public int deleteByUserId(Long userId){

		return mapper.deleteByUserId(userId);
	}

	/**
	 * 根据用户IdList批量删除角色
	 */
	public int batchDeleteByUserIdList(List userIdList){

		if (userIdList != null && userIdList.size() > 0) {
			return mapper.batchDeleteByUserIdList(userIdList);
		} else {
			return 0;
		}

	}

}
