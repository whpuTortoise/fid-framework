package com.tortoise.framework.paginter.domain;

import java.util.List;

/**
 * dataTable列表json
 */
public class JsonTable {

	public JsonTable() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public JsonTable(List data, int total) {
		super();
		this.total = total;
		this.data = data;
	}

	@SuppressWarnings("rawtypes")
	public JsonTable(List data, int total, Object callBack) {
		super();
		this.total = total;
		this.data = data;
		this.callBack = callBack;
	}

	private int total = 0;

	/**
	 * /** 数据
	 */
	@SuppressWarnings({ "rawtypes" })
	private List data;

	private Object callBack;

	@SuppressWarnings("rawtypes")
	public List getData() {
		return data;
	}

	@SuppressWarnings("rawtypes")
	public void setData(List data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getCallBack() {
		return callBack;
	}

	public void setCallBack(Object callBack) {
		this.callBack = callBack;
	}

}
