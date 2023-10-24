package com.omrbranch.pojo.globaldatas;



public class GlobalDatas {
	private int StatusCode;
	private int stateIdNum;
	private int cityId;
	private String stateIdText;
	private String addressId;
	private String pName;
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	private String logtoken;
	public int getStatusCode() {
		return StatusCode;
	}
	public void setStatusCode(int statusCode) {
		StatusCode = statusCode;
	}
	public int getStateIdNum() {
		return stateIdNum;
	}
	public void setStateIdNum(int stateIdNum) {
		this.stateIdNum = stateIdNum;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getStateIdText() {
		return stateIdText;
	}
	public void setStateIdText(String stateIdText) {
		this.stateIdText = stateIdText;
	}
	public String getLogtoken() {
		return logtoken;
	}
	public void setLogtoken(String logtoken) {
		this.logtoken = logtoken;
	}

	

	
}
