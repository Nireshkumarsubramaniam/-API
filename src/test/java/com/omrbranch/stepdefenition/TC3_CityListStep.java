package com.omrbranch.stepdefenition;

import java.util.ArrayList;
import java.util.List;


import com.omrbranch.base.Baseclass;
import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.payload.address.AddressPayload;
import com.omrbranch.pojo.address.CityList;
import com.omrbranch.pojo.address.CityList_Output_Pojo;
import com.omrbranch.pojo.globaldatas.GlobalDatas;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_CityListStep extends Baseclass {
	static GlobalDatas globalDatas = new GlobalDatas();
	Response response;
	@Given("User add header for to get CityList")
	public void user_add_header_for_to_get_city_list() {
		List<Header> head = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		head.add(h1);
		head.add(h2);

		Headers headers = new Headers(head);
		addHeaders(headers);
	  
	}
	@When("User add request body stateid for get CityList")
	public void user_add_request_body_stateid_for_get_city_list() {
		addBody(AddressPayload.getCityListPayLoad(TC1_LoginStep.globaldatas.getStateIdText()));
		
	  
	}
	@When("User send {string} request for CityList endpoint")
	public void user_send_request_for_city_list_endpoint(String type) {
		response = reqType("POST", Endpoints.CityList);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);
	  
	}
	

	@Then("User verify the CityList response message matches {string} and saved cityid")
	public void user_verify_the_city_list_response_message_matches_and_saved_cityid(String expcityName) {
		
		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);
		ArrayList<CityList> data = cityList_Output_Pojo.getData();
		for (CityList eachCity : data) {
			String CityName = eachCity.getName();
			if (CityName.equals(expcityName)) {
				int Cityid = eachCity.getId();
				System.out.println(Cityid);
				break;
			}
		}

		
	}

	
	



}
