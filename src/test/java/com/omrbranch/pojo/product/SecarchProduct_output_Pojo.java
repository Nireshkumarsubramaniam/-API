package com.omrbranch.pojo.product;

import java.util.ArrayList;


public class SecarchProduct_output_Pojo {
	private int status;
    private String message;
    private ArrayList<Product> data;
    private String currency;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ArrayList<Product> getData() {
		return data;
	}
	public void setData(ArrayList<Product> data) {
		this.data = data;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
