package com.tortoise.quake.controller;

import java.util.List;

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
import com.tortoise.quake.model.Menu;
import com.tortoise.quake.service.MenuService;
import com.tortoise.quake.vo.MenuVo;

/**
 * 
* @Project: quake
* @Title: MenuController.java
* @Package com.tortoise.quake.controller
* @Description: 菜单管理
* @author WangZhi
* @date 2018年4月14日 下午7:57:06
* @Copyright: 2018 
* @version V1.0
 */
@RequestMapping("/menu")
@Controller
public class MenuController {
	@Autowired
	private MenuService mMenuService;
	
	/**
	 * 菜单管理页面跳转
	 * @param model
	 * @return
	 */
	@GetMapping("/manager")
	public String manager(Model model) {
		return "system/menu/menuManager";
	}
	
	/**
	 * 获取完整的菜单树
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@PostMapping("/getMenuTree")
	public ApiResult getMenuTree(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<MenuVo> menus = mMenuService.getMenuTree();
			return new ApiResult(ApiResult.SUCCESS, "成功！", menus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResult(ApiResult.FAILURE, "失败！", null);
	}
	
	/**
	 * 保存菜单
	 * @param request
	 * @param response
	 * @param menu
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveMenu")
	public ApiResult saveMenu(HttpServletRequest request, HttpServletResponse response, Menu menu) {
		try {
			if(StringUtils.isEmpty(menu.getId())){
				menu.setLevel(menu.getLevel()+1);
				mMenuService.insert(menu);
			}else{
				mMenuService.update(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "保存失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "保存成功！", null);
	}
	
	/**
	 * 
	* @Title: deleteMenus 
	* @Description: 删除菜单及其子菜单
	* @param request
	* @param response
	* @param id
	* @throws
	 */
	@ResponseBody
	@PostMapping("/deleteMenus")
	public ApiResult deleteMenus(HttpServletRequest request, HttpServletResponse response, Long id) {
		try {
			mMenuService.deleteMenusById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "删除失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "删除成功！", null);
	}
	
}
