package com.omrbranch.stepdefenition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.omrbranch.base.Baseclass;
import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.payload.address.AddressPayload;
import com.omrbranch.payloadmanager.payloadManager;
import com.omrbranch.pojo.address.AddUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.DeleteUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.GetUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.UpdateUserAddress_Outpt_Pojo;
import com.omrbranch.pojo.address.UserDetail;
import com.omrbranch.pojo.globaldatas.GlobalDatas;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_AddressStep extends Baseclass {

	static GlobalDatas globalDatas = new GlobalDatas();
	Response response;

	@Given("User add header and bearer authorization for accessing address endpoints")
	public void user_add_header_and_bearer_authorization_for_accessing_address_endpoints() {
		List<Header> head = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogtoken());
		head.add(h1);
		head.add(h2);
		head.add(h3);
		Headers headers = new Headers(head);
		addHeaders(headers);
	}

	@When("User add request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void user_add_request_body_for_add_new_address_and(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		payloadManager.getAddressPayload();
		addBody(AddressPayload.addaddresspayload(first_name, last_name, mobile, apartment, Integer.parseInt(state),
				Integer.parseInt(city), Integer.parseInt(country), zipcode, address, address_type));
	}

	@When("User send {string} request for addUserAddress endpoint")
	public void user_send_request_for_add_user_address_endpoint(String type) {
		response = reqType(type, Endpoints.AddUserAddress);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);

	}

	@Then("User should verify the addUserAddress response message matches {string}")
	public void user_should_verify_the_add_user_address_response_message_matches(String expAddAddress) {
		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String message = addUserAddress_Output_Pojo.getMessage();
		int address_id = addUserAddress_Output_Pojo.getAddress_id();
		String addressId = String.valueOf(address_id);
		Assert.assertEquals("Address added successfully", expAddAddress, message);
		TC1_LoginStep.globaldatas.setAddressId(addressId);
	}

	@When("User add request body for update the address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string} with address id")
	public void user_add_request_body_for_update_the_address_and_with_address_id(String first_name, String last_name,
			String mobile, String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		payloadManager.getAddressPayload();
		addBody(AddressPayload.updateaddresspayload(TC1_LoginStep.globaldatas.getAddressId(), first_name, last_name,
				mobile, apartment, Integer.parseInt(state), Integer.parseInt(city), Integer.parseInt(country), zipcode,
				address, address_type));

	}

	@When("User send {string} request for updateUserAddress endpoint")
	public void user_send_request_for_update_user_address_endpoint(String type) {
		response = reqType(type, Endpoints.UpdateUserAddress);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);

	}

	@Then("User should verify the updateUserAddress response message matches {string}")
	public void user_should_verify_the_update_user_address_response_message_matches(String expupdateaddress) {
		UpdateUserAddress_Outpt_Pojo updateUserAddress_Outpt_Pojo = response.as(UpdateUserAddress_Outpt_Pojo.class);
		String message = updateUserAddress_Outpt_Pojo.getMessage();
		Assert.assertEquals("Verify the updated Address", expupdateaddress, message);

	}

	@When("User add request body for delete the address with address id")
	public void user_add_request_body_for_delete_the_address_with_address_id() {
		payloadManager.getAddressPayload();
		addBody(AddressPayload.deleteAdress(TC1_LoginStep.globaldatas.getAddressId()));

	}

	@When("User send {string} request for deleteUserAddress endpoint")
	public void user_send_request_for_delete_user_address_endpoint(String type) {
		response = reqType(type, Endpoints.DeleteUserAddress);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);

	}

	@Then("User should verify the deleteUserAddress response message matches {string}")
	public void user_should_verify_the_delete_user_address_response_message_matches(
			String expAddressdeletedSuccessfully) {
		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String message = deleteUserAddress_Output_pojo.getMessage();
		Assert.assertEquals("Verify Address deleted successfully", expAddressdeletedSuccessfully, message);

	}

	@When("User send {string} request for getUserAddress endpoint")
	public void user_send_request_for_get_user_address_endpoint(String type) {
		response = reqType(type, Endpoints.GetUserAddress);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globaldatas.setStatusCode(statusCode);

	}

	@Then("User should verify the getUserAddress response message matches {string} and saved city name")
	public void user_should_verify_the_get_user_address_response_message_matches_and_saved_city_name(String string) {
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		ArrayList<UserDetail> addressList = getUserAddress_Output_Pojo.getData();
		for (UserDetail eachAddressDetail : addressList) {
			String address = eachAddressDetail.getAddress();
			System.out.println(address);
			if (address.equals("Chennai")) {
				String city = eachAddressDetail.getCity();
				System.out.println(city);
				Assert.assertEquals(city, "Yercaud", "Verify city name");
				break;
			}

		}
	}

}
