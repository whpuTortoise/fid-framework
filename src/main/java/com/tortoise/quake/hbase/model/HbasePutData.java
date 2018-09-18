package com.tortoise.quake.hbase.model;

import java.io.Serializable;

/**  
 * @Project: quake
 * @Title: HbasePutData.java
 * @Package com.tortoise.quake.hbase.model
 * @Description: TODO
 * @author WangZhi
 * @date 2018年5月30日 下午5:54:25
 * @Copyright: 2018 
 * @version V1.0  
 */

public class HbasePutData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String rowName;
	private String familyName;
	private String qualifier;
	private byte[] value;
	public String getRowName() {
		return rowName;
	}
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	public byte[] getValue() {
		return value;
	}
	public void setValue(byte[] value) {
		this.value = value;
	}
	
	
}
