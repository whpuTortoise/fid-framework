package com.tortoise.quake.vo.page;


/**  
 * 
 * @Project: quake
 * @Title: UserPageReqVo.java
 * @Package com.tortoise.quake.vo
 * @Description: TODO 用户管理列表查询入参
 * @author WangZhi
 * @date 2018年4月11日 下午8:34:07
 * @Copyright: 2018 
 * @version V1.0  
 */

public class UserPageReqVo extends PageReqVo {
	
	private String searchUserName;
	
	private String searchRealName;

	private String searchDepartmentId;


	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getSearchRealName() {
		return searchRealName;
	}

	public void setSearchRealName(String searchRealName) {
		this.searchRealName = searchRealName;
	}

	public String getSearchDepartmentId() {
		return searchDepartmentId;
	}

	public void setSearchDepartmentId(String searchDepartmentId) {
		this.searchDepartmentId = searchDepartmentId;
	}
}
