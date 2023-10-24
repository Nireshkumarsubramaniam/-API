package com.omrbranch.stepdefenition;

import java.util.ArrayList;

import org.junit.Assert;

import com.omrbranch.base.Baseclass;
import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.pojo.address.StateList;
import com.omrbranch.pojo.address.StateList_Output_pojo;
import com.omrbranch.pojo.globaldatas.GlobalDatas;
import io.restassured.response.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC2_StateListStep extends Baseclass {
	static GlobalDatas globalDatas = new GlobalDatas();
	Response response;
	
	@Given("User add headers for to stateList")
	public void user_add_headers_for_to_state_list() {
		addHeader("accept", "application/json");
	 }
	@When("User send {string} request for stateList endpoint")
	public void user_send_request_for_state_list_endpoint(String string) {
		response = reqType("GET", Endpoints.StateList);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);
		
	}
	

	@Then("User should verify the stateList responce message match {string} and saved stateid")
	public void user_should_verify_the_state_list_responce_message_match_and_saved_stateid(String expStateName) {
		StateList_Output_pojo stateList_Output_pojo = response.as(StateList_Output_pojo.class);
		//Find the state TN
				ArrayList<StateList> stateList = stateList_Output_pojo.getData();
				for (StateList eachStateList : stateList) {
					String name = eachStateList.getName();
					if (name.equals("Tamil Nadu")) {
						int id = eachStateList.getId();
						String StateIdText = String.valueOf(id);
						TC1_LoginStep.globaldatas.setStateIdNum(id);
						TC1_LoginStep.globaldatas.setStateIdText(StateIdText);
						System.out.println(id);
						Assert.assertEquals("verify stateId of TN", expStateName,name );
					}

				}
	}

}
