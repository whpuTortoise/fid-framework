package com.tortoise.quake.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tortoise.framework.dto.ApiResult;
import com.tortoise.quake.model.User;
import com.tortoise.quake.service.MenuService;
import com.tortoise.quake.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;

	/**
	 * 登录跳转
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String toLogin(Model model) {
		return "system/login";
	}
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String toIndex(Model model) {
		model.addAttribute("menus", menuService.getMenuTree());
		/**
		 * 1.添加用户和角色关联；
		 * 2.根据角色获取菜单
		 */
		return "system/index";
	}
	
	@GetMapping("/welcome")
	public String toWelcome(Model model) {
		return "system/welcome";
	}


	/**
	 * 登录
	 * 
	 * @param admin
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@ResponseBody
	@PostMapping("/toLogin")
	public ApiResult loginPost(HttpSession httpSession, User user) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username", user.getUsername());
		param.put("password", user.getPassword());
		User currentUser = userService.selectOne(param);
		if (currentUser != null) {
			httpSession.setAttribute("currentUser", currentUser);
			return new ApiResult(ApiResult.SUCCESS, "登录成功！", null);
		} else {
			return new ApiResult(ApiResult.FAILURE, "用户名或密码错误，请重新登录！", null);
		}
	}
	
	

	
	
}
