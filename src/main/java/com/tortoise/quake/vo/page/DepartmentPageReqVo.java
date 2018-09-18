package com.tortoise.quake.vo.page;

/**  
 * @Project: quake
 * @Title: DepartmentPageReqVo.java
 * @Package com.tortoise.quake.vo.page
 * @Description: TODO
 * @author PengPeng
 * @date 2018年5月9日 下午9:26:16
 * @Copyright: 2018 
 * @version V1.0  
 */

public class DepartmentPageReqVo extends PageReqVo {
	
	private String searchDepartmentName;
	private String searchDepartmentCode;
	
	public String getSearchDepartmentName() {
		return searchDepartmentName;
	}
	public void setSearchDepartmentName(String searchDepartmentName) {
		this.searchDepartmentName = searchDepartmentName;
	}
	public String getSearchDepartmentCode() {
		return searchDepartmentCode;
	}
	public void setSearchDepartmentCode(String searchDepartmentCode) {
		this.searchDepartmentCode = searchDepartmentCode;
	}


}
