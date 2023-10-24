package com.omrbranch.stepdefenition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.omrbranch.base.Baseclass;
import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.pojo.globaldatas.GlobalDatas;
import com.omrbranch.pojo.login.Login_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC1_LoginStep extends Baseclass{
	static GlobalDatas globaldatas = new GlobalDatas();
	Response response;
	
	@Given("User add header")
	public void user_add_header() {
		addHeader("accept", "application/json");
		System.out.println();
	   
	}
	@When("User add basic authentication for login")
	public void user_add_basic_authentication_for_login() throws FileNotFoundException, IOException {
		addBasicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));
	 }
	@When("User send {string} request for login endpoint")
	public void user_send_request_for_login_endpoint(String type) {
		response = reqType("POST", Endpoints.Login);
		int statusCode = getStatusCode(response);
		globaldatas.setStatusCode(statusCode);
		
	}
	
	
	@Then("User should verify the login response body firstName present as {string} and get the logtoken saved")
	public void user_should_verify_the_login_response_body_first_name_present_as_and_get_the_logtoken_saved(String expfirstname) {
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String firstname = login_Output_Pojo.getData().getFirst_name();
		String logtoken = login_Output_Pojo.getData().getLogtoken();
		globaldatas.setLogtoken(logtoken);
		Assert.assertEquals("verify first name", expfirstname, firstname);
		System.out.println(logtoken);
	}




}
