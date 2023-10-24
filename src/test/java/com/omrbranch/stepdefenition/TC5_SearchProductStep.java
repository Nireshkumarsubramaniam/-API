package com.omrbranch.stepdefenition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.omrbranch.base.Baseclass;
import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.payload.product.ProductPayload;
import com.omrbranch.payloadmanager.payloadManager;
import com.omrbranch.pojo.product.SecarchProduct_output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC5_SearchProductStep extends Baseclass {
	Response response;

	@Given("User add headers for to search product")
	public void user_add_headers_for_to_search_product() {
		List<Header> head = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		head.add(h1);
		head.add(h2);
		Headers headers = new Headers(head);
		addHeaders(headers);
	   
	}
	@When("User add request body for search product {string}")
	public void user_add_request_body_for_search_product(String pName) {
		payloadManager.getProductPayload();
		addBody(ProductPayload.searchproductpayload(TC1_LoginStep.globaldatas.getpName()));
		
	  
	}
	@When("User send {string} request for search product endpoint")
	public void user_send_request_for_search_product_endpoint(String type) {
		response = reqType(type, Endpoints.SearchProduct);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);
		
	   
	}
	@Then("User verify the search product response message matches {string}")
	public void user_verify_the_search_product_response_message_matches(String expOKMsg) {
		SecarchProduct_output_Pojo searchproduct_Output_Pojo = response.as(SecarchProduct_output_Pojo.class);
		
		String message = searchproduct_Output_Pojo.getMessage();
		Assert.assertEquals("Verify OK",expOKMsg, message);
	}




}
