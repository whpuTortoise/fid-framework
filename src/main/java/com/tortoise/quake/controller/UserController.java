package com.tortoise.quake.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tortoise.quake.model.UserDepartmentEntity;
import com.tortoise.quake.model.UserRoleEntity;
import com.tortoise.quake.service.DepartmentService;
import com.tortoise.quake.service.UserDepartmentService;
import com.tortoise.quake.service.UserRoleService;
import com.tortoise.quake.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tortoise.framework.dto.ApiResult;
import com.tortoise.framework.util.JsonUtil;
import com.tortoise.quake.model.User;
import com.tortoise.quake.service.UserService;
import com.tortoise.quake.vo.page.PageRespVo;
import com.tortoise.quake.vo.page.UserPageReqVo;

/**
 * 
* @Project: quake
* @Title: UserController.java
* @Package com.tortoise.quake.controller
* @Description: 用户管理
* @author WangZhi
* @date 2018年4月14日 下午7:57:06
* @Copyright: 2018 
* @version V1.0
 */
@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserService mUserService;
	@Autowired
	private UserDepartmentService mUserDepartmentService;
	@Autowired
	private UserRoleService mUserRoleService;


	
	/**
	 * 用户管理页面跳转
	 * @param model
	 * @return
	 */
	@GetMapping("/manager")
	public String manager(Model model) {
		return "system/user/userManager";
	}
	
	/**
	 * 
	* @Title: getUserList 
	* @Description: 获取用户列表
	* @param request
	* @param response
	* @param pageReqVo
	* @return String     
	* @throws
	 */
	@ResponseBody
	@PostMapping(value = "/getUserList", produces="application/json;charset=UTF-8")
	public String getUserList(HttpServletRequest request, HttpServletResponse response, UserPageReqVo pageReqVo) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(pageReqVo.getSearchUserName())){
			queryMap.put("username", pageReqVo.getSearchUserName());
		}
		if(!StringUtils.isEmpty(pageReqVo.getSearchTel())){
			queryMap.put("tel", pageReqVo.getSearchTel());
		}
		List<User> users = mUserService.queryList(queryMap, pageReqVo.getOffset(), pageReqVo.getLimit());
		int count = mUserService.count(queryMap);

		PageRespVo<User> pageRespVo = new PageRespVo<User>();
		pageRespVo.setTotal(count);
		pageRespVo.setRows(users);
		return JsonUtil.toJson(pageRespVo);
	}
	
	/**
	 * 保存用户
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveUser")
	public ApiResult saveUser(HttpServletRequest request, HttpServletResponse response, UserVo user) {
		try {
			Long userId ;
			if(StringUtils.isEmpty(user.getId())){
				mUserService.insert(user);
				List<User> users = mUserService.queryList(user);
				userId = users.get(0).getId();
			}else{
				mUserService.update(user);
				userId = user.getId();
			}
			//插入所属部门
			UserDepartmentEntity userDepartmentEntity = new UserDepartmentEntity();
			userDepartmentEntity.setUserId(userId);
			userDepartmentEntity.setDepartmentId(Long.parseLong(user.getDepartmentId()));
			mUserDepartmentService.deleteByUserId(userId);
			mUserDepartmentService.insert(userDepartmentEntity);
			//插入所属角色
			mUserRoleService.deleteByUserId(userId);
			if(!StringUtils.isEmpty(user.getRoleIds())) {
				String[] roleIds = user.getRoleIds().split(",");
				for (int i = 0; i < roleIds.length; i++) {
					UserRoleEntity userRoleEntity = new UserRoleEntity();
					userRoleEntity.setUserId(userId);
					userRoleEntity.setRoleId(Long.parseLong(roleIds[i]));
					mUserRoleService.insert(userRoleEntity);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "保存失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "保存成功！", null);
	}
	
	/**
	 * 
	* @Title: deleteUsers 
	* @Description: 删除用户
	* @param request
	* @param response
	* @param ids
	* @return String     
	* @throws
	 */
	@ResponseBody
	@PostMapping("/deleteUsers")
	public ApiResult deleteUsers(HttpServletRequest request, HttpServletResponse response, String ids) {
		try {
			mUserService.batchDelete(ids.split(","), String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "删除失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "删除成功！", null);
	}
	
}
