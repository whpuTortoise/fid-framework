package com.tortoise.quake.vo.page;

import java.util.List;

public class PageRespVo<T> {
	//数据总条数 
	private Integer total;
	//数据
	private List<T> rows;
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
