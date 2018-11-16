package com.tortoise.quake.controller;

import java.util.ArrayList;
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
import com.tortoise.quake.model.Authority;
import com.tortoise.quake.service.AuthorityService;
import com.tortoise.quake.service.MenuService;
import com.tortoise.quake.vo.MenuVo;

/**
 * 
* @Project: quake
* @Title: AuthorityController.java
* @Package com.tortoise.quake.controller
* @Description: 权限管理
* @author WangZhi
* @date 2018年4月14日 下午7:57:06
* @Copyright: 2018 
* @version V1.0
 */
@RequestMapping("/authority")
@Controller
public class AuthorityController {
	@Autowired
	private MenuService mMenuService;
	
	@Autowired
	private AuthorityService mAuthorityService;
	
	/**
	 * 权限管理页面跳转
	 * @param model
	 * @return
	 */
	@GetMapping("/manager")
	public String manager(Model model) {
		return "system/authority/authorityManager";
	}
	
	/**
	 * 根据角色获取权限列表
	 * @param request
	 * @param response 
	 * @return
	 */
	@ResponseBody
	@PostMapping("/getAuthority")
	public ApiResult getAuthority(HttpServletRequest request, HttpServletResponse response, String roleId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		List<Authority> authoritys = mAuthorityService.queryList(params);
		return new ApiResult(ApiResult.SUCCESS, "成功！", authoritys);
	}
	
	/**
	 * 获取完整的权限树
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@PostMapping("/getAuthorityTree")
	public ApiResult getAuthorityTree(HttpServletRequest request, HttpServletResponse response, Long roleId) {
		try {
			List<MenuVo> menus = mMenuService.getMenuTree();
			return new ApiResult(ApiResult.SUCCESS, "成功！", menus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(ApiResult.FAILURE, "失败！", null);
	}
	
	/**
	 * 保存权限
	 * @param request
	 * @param response
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveAuthority")
	public ApiResult saveAuthority(HttpServletRequest request, HttpServletResponse response, String menuIds, String roleId) {
		try {
			Long roleIdL = 0L;
			if(!StringUtils.isEmpty(roleId)){
				roleIdL = Long.parseLong(roleId);
				mAuthorityService.deleteByRoleId(roleIdL);
			}
			
			if(!StringUtils.isEmpty(menuIds)){
				String[] menus = menuIds.split(",");
				List<Authority> list = new ArrayList<Authority>();
				for(int i = 0; i < menus.length; i++){
					Authority authority = new Authority();
					authority.setRoleId(roleIdL);
					authority.setMenuId(Long.parseLong(menus[i]));
					list.add(authority);
				}
				mAuthorityService.batchInsert(list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "保存失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "保存成功！", null);
	}
	
	
	
}
