package com.tortoise.quake.vo.page;


/**  
 * 
 * @Project: quake
 * @Title: DepartmentTypePageReqVo.java
 * @Package com.tortoise.quake.vo
 * @Description: TODO 机构类型管理列表查询入参
 * @author pp
 * @date 2018年12月03日 下午8:34:07
 * @Copyright: 2018 
 * @version V1.0  
 */

public class DepartmentTypePageReqVo extends PageReqVo {
	
	private String searchTypeCode;
	
	private String searchTypeName;

	public String getSearchTypeCode() {
		return searchTypeCode;
	}

	public void setSearchTypeCode(String searchTypeCode) {
		this.searchTypeCode = searchTypeCode;
	}

	public String getSearchTypeName() {
		return searchTypeName;
	}

	public void setSearchTypeName(String searchTypeName) {
		this.searchTypeName = searchTypeName;
	}



}
