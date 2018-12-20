package com.tortoise.quake.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tortoise.framework.util.BeanUtil;
import com.tortoise.quake.model.Department;
import com.tortoise.quake.model.UserDepartmentEntity;
import com.tortoise.quake.model.UserRoleEntity;
import com.tortoise.quake.service.DepartmentService;
import com.tortoise.quake.service.UserDepartmentService;
import com.tortoise.quake.service.UserRoleService;
import com.tortoise.quake.vo.UserVo;
import org.springframework.beans.BeanUtils;
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
	@Autowired
	private DepartmentService mDepartmentService;


	
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
		if(!StringUtils.isEmpty(pageReqVo.getSearchRealName())){
			queryMap.put("realName", pageReqVo.getSearchRealName());
		}
		List<User> users = mUserService.queryList(queryMap, pageReqVo.getOffset(), pageReqVo.getLimit());
		int count = mUserService.count(queryMap);

		PageRespVo<User> pageRespVo = new PageRespVo<User>();
		pageRespVo.setTotal(count);
		pageRespVo.setRows(users);
		return JsonUtil.toJson(pageRespVo);
	}

	/**
	 *
	 * @Title: getShowUserList
	 * @Description: 获取显示用户列表
	 * @param request
	 * @param response
	 * @param pageReqVo
	 * @return String
	 * @throws
	 */
	@ResponseBody
	@PostMapping(value = "/getShowUserList", produces="application/json;charset=UTF-8")
	public String getShowUserList(HttpServletRequest request, HttpServletResponse response, UserPageReqVo pageReqVo) {
		PageRespVo<UserVo> pageRespVo = new PageRespVo<UserVo>();
		if(!StringUtils.isEmpty(pageReqVo.getSearchDepartmentId())){
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("departmentId", pageReqVo.getSearchDepartmentId());

			List<UserDepartmentEntity> userDepartmentEntities = mUserDepartmentService.queryList(queryMap, pageReqVo.getOffset(), pageReqVo.getLimit());
			List<Department> departments = mDepartmentService.queryAll();
			List<User> users = mUserService.queryAll();

			int count = mUserDepartmentService.count(queryMap);
			List<UserVo> showList = new ArrayList<UserVo>();
			for(int i=0;i<userDepartmentEntities.size();i++){
				UserVo userVo = new UserVo();
				for(int j=0;j<users.size();j++){
					if(users.get(j).getId()==userDepartmentEntities.get(i).getUserId()){
						BeanUtils.copyProperties(users.get(j),userVo);
						break;
					}
				}

				for(int k=0;k<departments.size();k++){
					if(departments.get(k).getId()==userDepartmentEntities.get(i).getDepartmentId()){
						userVo.setDepartmentId(departments.get(k).getId().toString());
						userVo.setDepartmentName(departments.get(k).getDepartmentName());
						break;
					}
				}

				showList.add(userVo);
			}
			pageRespVo.setTotal(count);
			pageRespVo.setRows(showList);
		}else {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			if(!StringUtils.isEmpty(pageReqVo.getSearchUserName())){
				queryMap.put("username", pageReqVo.getSearchUserName());
			}
			if(!StringUtils.isEmpty(pageReqVo.getSearchRealName())){
				queryMap.put("realName", pageReqVo.getSearchRealName());
			}
			List<UserDepartmentEntity> userDepartmentEntities = mUserDepartmentService.queryAll();
			List<Department> departments = mDepartmentService.queryAll();

			List<User> users = mUserService.queryList(queryMap, pageReqVo.getOffset(), pageReqVo.getLimit());
			int count = mUserService.count(queryMap);
			List<UserVo> showList = new ArrayList<UserVo>();
			for(int i=0;i<users.size();i++){
				UserVo userVo = new UserVo();
				BeanUtils.copyProperties(users.get(i),userVo);
				for(int j=0;j<userDepartmentEntities.size();j++){
					if(userVo.getId()==userDepartmentEntities.get(j).getUserId()){
						userVo.setDepartmentId(userDepartmentEntities.get(j).getDepartmentId().toString());
						for(int k=0;k<departments.size();k++){
							if(userVo.getDepartmentId().equals(departments.get(k).getId().toString())){
								userVo.setDepartmentName(departments.get(k).getDepartmentName());
								break;
							}
						}
						break;
					}
				}
				showList.add(userVo);
			}
			pageRespVo.setTotal(count);
			pageRespVo.setRows(showList);
		}



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
