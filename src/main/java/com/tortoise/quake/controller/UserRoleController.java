package com.tortoise.quake.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tortoise.framework.dto.ApiResult;
import com.tortoise.quake.model.UserRole;
import com.tortoise.quake.service.UserRoleService;

/**
 * 
* @Project: quake
* @Title: UserRoleController.java
* @Package com.tortoise.quake.controller
* @Description: 用户_角色管理
* @author Pp
* @date 2018年12月10日 下午7:57:06
* @Copyright: 2018 
* @version V1.0
 */
@RequestMapping("/userrole")
@Controller
public class UserRoleController {
	
	
	@Autowired
	private UserRoleService mUserRoleService;
	

	/**
	 * 根据机构获取用户ID列表
	 * @param request
	 * @param response 
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/getUserRole", produces="application/json;charset=UTF-8")
	public ApiResult getUserRole(HttpServletRequest request, HttpServletResponse response, UserRole query){
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(query.getUserId())){
			params.put("userId", query.getUserId());
		}
		if(!StringUtils.isEmpty(query.getRoleId())){
			params.put("roleId", query.getRoleId());
		}
		List<UserRole> userRoles = mUserRoleService.queryList(params);
		return new ApiResult(ApiResult.SUCCESS, "成功！", userRoles);
	}
	

	/**
	 * 根据用户ID保存用户_角色
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveUserRoleByUserId")
	public ApiResult saveUserRoleByUserId(HttpServletRequest request, HttpServletResponse response, String userId, String roleIds) {
		try {
			Long userIdL = 0L;
			if(!StringUtils.isEmpty(userId)){
				userIdL = Long.parseLong(userId);
				mUserRoleService.deleteByUserId(userIdL);
			}
			
			if(!StringUtils.isEmpty(roleIds)){
				String[] roles = roleIds.split(",");
				List<UserRole> list = new ArrayList<UserRole>();
				for(int i = 0; i < roles.length; i++){
					UserRole userRole = new UserRole();
					userRole.setUserId(userIdL);
					userRole.setRoleId(Long.parseLong(roles[i]));
					list.add(userRole);
				}
				mUserRoleService.batchInsert(list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "保存失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "保存成功！", null);
	}
	
	/**
	 * 根据用户ID保存用户_角色
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveUserRoleByRoleId")
	public ApiResult saveUserRoleByRoleId(HttpServletRequest request, HttpServletResponse response, String userIds, String roleId) {
		try {
			Long roleIdL = 0L;
			if(!StringUtils.isEmpty(roleId)){
				roleIdL = Long.parseLong(roleId);
				mUserRoleService.deleteByRoleId(roleIdL);
			}
			
			if(!StringUtils.isEmpty(userIds)){
				String[] users = userIds.split(",");
				List<UserRole> list = new ArrayList<UserRole>();
				for(int i = 0; i < users.length; i++){
					UserRole userRole = new UserRole();
					userRole.setUserId(Long.parseLong(users[i]));
					userRole.setRoleId(roleIdL);
					list.add(userRole);
				}
				mUserRoleService.batchInsert(list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "保存失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "保存成功！", null);
	}
	
	
	
}
