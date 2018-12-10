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
import com.tortoise.quake.model.UserDepartment;
import com.tortoise.quake.service.UserDepartmentService;

/**
 * 
* @Project: quake
* @Title: UserDepartmentController.java
* @Package com.tortoise.quake.controller
* @Description: 用户_机构管理
* @author Pp
* @date 2018年12月10日 下午7:57:06
* @Copyright: 2018 
* @version V1.0
 */
@RequestMapping("/userdepartment")
@Controller
public class UserDepartmentController {
	
	
	@Autowired
	private UserDepartmentService mUserDepartmentService;
	

	/**
	 * 根据机构获取用户ID列表
	 * @param request
	 * @param response 
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/getUserDepartment", produces="application/json;charset=UTF-8")
	public ApiResult getUserDepartment(HttpServletRequest request, HttpServletResponse response, UserDepartment query){
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(query.getUserId())){
			params.put("userId", query.getUserId());
		}
		if(!StringUtils.isEmpty(query.getDepartmentId())){
			params.put("departmentId", query.getDepartmentId());
		}
		List<UserDepartment> userDepartments = mUserDepartmentService.queryList(params);
		return new ApiResult(ApiResult.SUCCESS, "成功！", userDepartments);
	}
	

	/**
	 * 保存用户_机构
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveUserDepartment")
	public ApiResult saveUserDepartment(HttpServletRequest request, HttpServletResponse response, String userId, String departmentId) {
		try {
			Long userIdL = 0L;
			if((!StringUtils.isEmpty(userId))||(!StringUtils.isEmpty(departmentId))){
				userIdL = Long.parseLong(userId);
				mUserDepartmentService.deleteByUserId(userIdL);
				
				List<UserDepartment> list = new ArrayList<UserDepartment>();
				
				UserDepartment userDepartment = new UserDepartment();
				userDepartment.setUserId(userIdL);
				userDepartment.setDepartmentId(Long.parseLong(departmentId));
				list.add(userDepartment);
				mUserDepartmentService.batchInsert(list);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "保存失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "保存成功！", null);
	}
	
	
	
}
