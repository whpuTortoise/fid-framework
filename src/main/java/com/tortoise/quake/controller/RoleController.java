package com.tortoise.quake.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.tortoise.quake.model.Role;
import com.tortoise.quake.service.RoleService;
import com.tortoise.quake.vo.page.PageRespVo;
import com.tortoise.quake.vo.page.RolePageReqVo;

@RequestMapping("/role")
@Controller
public class RoleController {
	@Autowired
	private RoleService mRoleService;
	
	/**
	 * 角色管理页面跳转
	 * @param model
	 * @return
	 */
	@GetMapping("/manager")
	public String manager(Model model) {
		return "system/role/roleManager";
	}
	
	/**
	 * 获取所有角色
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/getAllRole",produces="application/json;charset=UTF-8")
	public ApiResult getAllRole(HttpServletRequest request, HttpServletResponse response){
		List<Role> roles = mRoleService.queryAll();
		return new ApiResult(ApiResult.SUCCESS, "", roles);
	}
	
	/**
	 * 
	* @Title: getRoleList 
	* @Description: 获取角色列表
	* @param request
	* @param response
	* @param pageReqVo
	* @return String     
	* @throws
	 */
	@ResponseBody
	@PostMapping(value = "/getRoleList", produces="application/json;charset=UTF-8")
	public String getRoleList(HttpServletRequest request, HttpServletResponse response, RolePageReqVo pageReqVo) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(pageReqVo.getSearchRoleName())){
			queryMap.put("roleName", pageReqVo.getSearchRoleName());
		}
		
		List<Role> roles = mRoleService.queryList(queryMap, pageReqVo.getOffset(), pageReqVo.getLimit());
		int count = mRoleService.count(queryMap);

		PageRespVo<Role> pageRespVo = new PageRespVo<Role>();
		pageRespVo.setTotal(count);
		pageRespVo.setRows(roles);
		return JsonUtil.toJson(pageRespVo);
	}
	
	/**
	 * 保存角色
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveRole")
	public ApiResult saveRole(HttpServletRequest request, HttpServletResponse response, Role role) {
		try {
			if(StringUtils.isEmpty(role.getId())){
				mRoleService.insert(role);
			}else{
				mRoleService.update(role);
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
	* @Description: 删除角色
	* @param request
	* @param response
	* @param ids
	* @return String     
	* @throws
	 */
	@ResponseBody
	@PostMapping("/deleteRoles")
	public ApiResult deleteRoles(HttpServletRequest request, HttpServletResponse response, String ids) {
		try {
			mRoleService.batchDelete(ids.split(","), String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "删除失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "删除成功！", null);
	}
	
	
}
