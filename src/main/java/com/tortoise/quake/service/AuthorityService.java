package com.tortoise.quake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tortoise.framework.service.BaseService;
import com.tortoise.quake.dao.AuthorityMapper;
import com.tortoise.quake.model.Authority;

@Service
public class AuthorityService extends BaseService<Authority, AuthorityMapper> {

	@Autowired
	public void setMapper(AuthorityMapper mapper) {
		this.mapper = mapper;
	}

	
	/**
	 * 根据角色ID删除权限
	 */
	public int deleteByRoleId(Long roleId){
		return mapper.deleteByRoleId(roleId);
	}
}
