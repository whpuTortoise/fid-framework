package com.tortoise.quake.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Project: quake
 * @Title: MyInterceptor.java
 * @Package com.tortoise.quake
 * @Description: 拦截器
 * @author WangZhi
 * @date 2018年5月11日 下午2:56:42
 * @Copyright: 2018
 * @version V1.0
 */

public class MyInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		
		logger.info("------preHandle------");
		// 获取session
		HttpSession session = arg0.getSession(true);
		// 判断用户ID是否存在，不存在就跳转到登录界面
		if (session.getAttribute("currentUser") == null) {
			logger.info("------:跳转到login页面！");
			arg1.sendRedirect(arg0.getContextPath() + "/login");
			return false;
		} else {
			session.setAttribute("currentUser", session.getAttribute("currentUser"));
			return true;
		}
	}

}
