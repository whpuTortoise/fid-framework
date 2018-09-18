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
import com.tortoise.quake.model.Department;
import com.tortoise.quake.service.DepartmentService;
import com.tortoise.quake.vo.page.PageRespVo;
import com.tortoise.quake.vo.page.DepartmentPageReqVo;

@RequestMapping("/department")
@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService mDepartmentService;
	
	/**
	 * 部门管理页面跳转
	 * @param model
	 * @return
	 */
	@GetMapping("/manager")
	public String manager(Model model) {
		return "system/department/departmentManager";
	}
	
	/**
	 * 获取所有部门
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getAllDepartment")
	public ApiResult getAllDepartment(HttpServletRequest request, HttpServletResponse response){
		List<Department> departments = mDepartmentService.queryAll();
		return new ApiResult(ApiResult.SUCCESS, "", departments);
	}
	
	/**
	 * 
	* @Title: getDepartmentsList 
	* @Description: 获取部门列表
	* @param request
	* @param response
	* @param pageReqVo
	* @return String     
	* @throws
	 */
	@ResponseBody
	@PostMapping("/getDepartmentsList")
	public String getDepartmentsList(HttpServletRequest request, HttpServletResponse response, DepartmentPageReqVo pageReqVo) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(pageReqVo.getSearchDepartmentName())){
			queryMap.put("departmentName", pageReqVo.getSearchDepartmentName());
		}
		if(!StringUtils.isEmpty(pageReqVo.getSearchDepartmentCode())){
			queryMap.put("departmentCode", pageReqVo.getSearchDepartmentCode());
		}
		
		List<Department> departments = mDepartmentService.queryList(queryMap, pageReqVo.getOffset(), pageReqVo.getLimit());
		int count = mDepartmentService.count(queryMap);

		PageRespVo<Department> pageRespVo = new PageRespVo<Department>();
		pageRespVo.setTotal(count);
		pageRespVo.setRows(departments);
		return JsonUtil.toJson(pageRespVo);
	}
	
	/**
	 * 保存部门
	 * @param request
	 * @param response
	 * @param department
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveDepartment")
	public ApiResult saveDepartment(HttpServletRequest request, HttpServletResponse response, Department department) {
		try {
			if(StringUtils.isEmpty(department.getId())){
				mDepartmentService.insert(department);
			}else{
				mDepartmentService.update(department);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "保存失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "保存成功！", null);
	}
	
	/**
	 * 
	* @Title: deleteDepartments 
	* @Description: 删除部门
	* @param request
	* @param response
	* @param ids
	* @return String     
	* @throws
	 */
	@ResponseBody
	@PostMapping("/deleteDepartments")
	public ApiResult deleteRoles(HttpServletRequest request, HttpServletResponse response, String ids) {
		try {
			mDepartmentService.batchDelete(ids.split(","), String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "删除失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "删除成功！", null);
	}
	
	
}
