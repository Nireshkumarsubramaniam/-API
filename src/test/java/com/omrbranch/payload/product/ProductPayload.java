package com.omrbranch.payload.product;

import com.omrbranch.pojo.product.SearchProduct_Input_Pojo;

public class ProductPayload {
	public static SearchProduct_Input_Pojo searchproductpayload(String productname){
		SearchProduct_Input_Pojo SearchProduct_Input_Pojo = new SearchProduct_Input_Pojo(productname);
		return SearchProduct_Input_Pojo;
		
	}

	
}
