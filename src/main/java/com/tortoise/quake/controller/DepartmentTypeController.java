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
import com.tortoise.quake.model.DepartmentType;
import com.tortoise.quake.model.Role;
import com.tortoise.quake.service.DepartmentTypeService;
import com.tortoise.quake.vo.page.DepartmentTypePageReqVo;
import com.tortoise.quake.vo.page.PageRespVo;

/**
 * 
* @Project: quake
* @Title: DepartmentTypeController.java
* @Package com.tortoise.quake.controller
* @Description: 机构类型管理
* @author pp
* @date 2018年12月3日 下午7:57:06
* @Copyright: 2018 
* @version V1.0
 */
@RequestMapping("/departmentType")
@Controller
public class DepartmentTypeController {
	@Autowired
	private DepartmentTypeService mDepartmentTypeService;
	
	/**
	 * 机构类型管理页面跳转
	 * @param model
	 * @return
	 */
	@GetMapping("/manager")
	public String manager(Model model) {
		return "system/departmentType/departmentTypeManager";
	}
	
	
	/**
	 * 获取所有机构类型
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/getAllDepartmentType",produces="application/json;charset=UTF-8")
	public ApiResult getAllDepartmentType(HttpServletRequest request, HttpServletResponse response){
		List<DepartmentType> types = mDepartmentTypeService.queryAll();
		return new ApiResult(ApiResult.SUCCESS, "", types);
	}
	
	
	/**
	 * 
	* @Title: getDepartmentTypeList 
	* @Description: 获取机构类型列表
	* @param request
	* @param response
	* @param pageReqVo
	* @return String     
	* @throws
	 */
	@ResponseBody
	@PostMapping(value = "/getDepartmentTypeList", produces="application/json;charset=UTF-8")
	public String getDepartmentTypeList(HttpServletRequest request, HttpServletResponse response, DepartmentTypePageReqVo pageReqVo) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(pageReqVo.getSearchTypeCode())){
			queryMap.put("typeCode", pageReqVo.getSearchTypeCode());
		}
		if(!StringUtils.isEmpty(pageReqVo.getSearchTypeName())){
			queryMap.put("typeName", pageReqVo.getSearchTypeName());
		}
		List<DepartmentType> departmentTypes = mDepartmentTypeService.queryList(queryMap, pageReqVo.getOffset(), pageReqVo.getLimit());
		int count = mDepartmentTypeService.count(queryMap);

		PageRespVo<DepartmentType> pageRespVo = new PageRespVo<DepartmentType>();
		pageRespVo.setTotal(count);
		pageRespVo.setRows(departmentTypes);
		return JsonUtil.toJson(pageRespVo);
	}
	
	/**
	 * 保存机构类型
	 * @param request
	 * @param response
	 * @param departmentType
	 * @return
	 */
	@ResponseBody
	@PostMapping("/saveDepartmentType")
	public ApiResult saveDepartmentType(HttpServletRequest request, HttpServletResponse response, DepartmentType departmentType) {
		try {
			if(StringUtils.isEmpty(departmentType.getId())){
				mDepartmentTypeService.insert(departmentType);
			}else{
				mDepartmentTypeService.update(departmentType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "保存失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "保存成功！", null);
	}
	
	/**
	 * 
	* @Title: deleteDepartmentTypes 
	* @Description: 删除机构类型
	* @param request
	* @param response
	* @param ids
	* @return String     
	* @throws
	 */
	@ResponseBody
	@PostMapping("/deleteDepartmentTypes")
	public ApiResult deleteDepartmentTypes(HttpServletRequest request, HttpServletResponse response, String ids) {
		try {
			mDepartmentTypeService.batchDelete(ids.split(","), String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(ApiResult.FAILURE, "删除失败！", null);
		}
		return new ApiResult(ApiResult.SUCCESS, "删除成功！", null);
	}
	
}
