package com.grocery;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.omrbranch.base.Baseclass;
import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.payload.address.AddressPayload;
import com.omrbranch.payload.product.ProductPayload;
import com.omrbranch.pojo.address.AddUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.CityList;
import com.omrbranch.pojo.address.CityList_Output_Pojo;
import com.omrbranch.pojo.address.DeleteUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.GetUserAddress_Output_Pojo;
import com.omrbranch.pojo.address.StateList;
import com.omrbranch.pojo.address.StateList_Output_pojo;
import com.omrbranch.pojo.address.UpdateUserAddress_Outpt_Pojo;
import com.omrbranch.pojo.address.UserDetail;
import com.omrbranch.pojo.login.Login_Output_Pojo;
import com.omrbranch.pojo.product.SecarchProduct_output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class sample extends Baseclass {

	String StateIdText;
	String logtoken;
	String addressId;

	@Test(priority = 1)
	private void login() {
		addHeader("accept", "application/json");
		addBasicAuth("nireshkumar8596@gmail.com", "Niree8596@");
		Response response = reqType("POST", Endpoints.Login);
		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "verify status code");
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);

		String first_name = login_Output_Pojo.getData().getFirst_name();
		logtoken = login_Output_Pojo.getData().getLogtoken();
		Assert.assertEquals(first_name, "Nireshkumar", "verify first name");

	}

	@Test(priority = 2)
	private void getStateList() {
		addHeader("accept", "application/json");
		Response response = reqType("GET", Endpoints.StateList);
		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "verify status code");
		System.out.println(statusCode);
		StateList_Output_pojo stateList_Output_pojo = response.as(StateList_Output_pojo.class);
//Find the state TN
		ArrayList<StateList> stateList = stateList_Output_pojo.getData();
		for (StateList eachStateList : stateList) {
			String name = eachStateList.getName();
			if (name.equals("Tamil Nadu")) {
				int id = eachStateList.getId();
				StateIdText = String.valueOf(id);
				System.out.println(id);
				Assert.assertEquals(id, 35, "verify stateId of TN");
			}

		}

	}

	@Test(priority = 3)
	private void getCityList() {
//1.header

		List<Header> head = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		head.add(h1);
		head.add(h2);

		Headers headers = new Headers(head);
		addHeaders(headers);
//payload
//		CityList_Input_Pojo cityList_Input_pojo = new CityList_Input_Pojo(StateIdText);
		addBody(AddressPayload.getCityListPayLoad(StateIdText));
//req
		Response response = reqType("POST", Endpoints.CityList);
		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "verify status code");
		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);
//find the city ID Of Yercaud
		ArrayList<CityList> data = cityList_Output_Pojo.getData();
		for (CityList eachCity : data) {
			String CityName = eachCity.getName();
			if (CityName.equals("Erode")) {
				int Cityid = eachCity.getId();
				System.out.println(Cityid);
				// Assert.assertEquals(Cityid, 4440, "Verify cityid of yercard");
				break;
			}
		}

	}

	@Test(priority = 4)
	private void SearchProduct() {
		List<Header> head = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		head.add(h1);
		head.add(h2);
		Headers headers = new Headers(head);
		addHeaders(headers);
//payload
//		SearchProduct_Input_Pojo searchProduct_Input_Pojo = new SearchProduct_Input_Pojo("nuts");
		addBody(ProductPayload.searchproductpayload("nuts"));
//req
		Response response = reqType("POST", Endpoints.SearchProduct);
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verify status code");
		SecarchProduct_output_Pojo secarchProduct_output_Pojo = response.as(SecarchProduct_output_Pojo.class);
		String message = secarchProduct_output_Pojo.getMessage();
		System.out.println(message);
		Assert.assertEquals(message, "OK", "Verify OK");
	}

	@Test(priority = 5)
	private void addAddress() {
//1.Header
		List<Header> head = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + logtoken);
		head.add(h1);
		head.add(h2);
		head.add(h3);
		Headers headers = new Headers(head);
		addHeaders(headers);
//2.payLoad
//		AddUserAddressInput_Pojo addUserAddressInput_Pojo = new AddUserAddressInput_Pojo("niresh", "kumar", "9965263222","KG", 35, 4440, 101, "600028", "Chennai", "home");
		addBody(AddressPayload.addaddresspayload("Niresh", "kumar", "9965263222", "49", 35, 4440, 101, "600115",
				"chennai", "Home"));
//reqType
		Response response = reqType("POST", Endpoints.AddUserAddress);
		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "verify status code");
		AddUserAddress_Output_Pojo addUserAddress_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String message = addUserAddress_Output_Pojo.getMessage();
		int address_id = addUserAddress_Output_Pojo.getAddress_id();
		System.out.println(address_id);
		addressId = String.valueOf(address_id);
		Assert.assertEquals(message, "Address added successfully", "verify Address Added Successfully");

	}

//	@Test(priority = 6)
//	private void updateAddress() {
////1.Header
//		List<Header> head = new ArrayList<>();
//		Header h1 = new Header("accept", "application/json");
//		Header h2 = new Header("Content-Type", "application/json");
//		Header h3 = new Header("Authorization", "Bearer " + logtoken);
//		head.add(h1);
//		head.add(h2);
//		head.add(h3);
//		Headers headers = new Headers(head);
//		addHeaders(headers);
////payLoad
////		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(addressId,
////				"Kathir", "Avan", "9629603696", "KG", 35, 4440, 101, "600028", "Chennai", "home");
//		addBody(AddressPayload.updateaddresspayload(addressId, "Niresh", "kumar", "9965263222", "49", 35, 4440, 101,
//				"600115", "yercurd", "Home"));
////3.Req
//		Response response = reqType("PUT", Endpoints.UpdateUserAddress);
//		int statusCode = getStatusCode(response);
//
//		Assert.assertEquals(statusCode, 200, "verify status code");
//		UpdateUserAddress_Outpt_Pojo updateUserAddress_Outpt_Pojo = response.as(UpdateUserAddress_Outpt_Pojo.class);
//		String message = updateUserAddress_Outpt_Pojo.getMessage();
//		Assert.assertEquals(message, "Address updated successfully", "Verify the updated Address");
//
//	}

//	@Test(priority = 7)
//	private void deleteAddress() {
////1.Header
//		List<Header> head = new ArrayList<>();
//		Header h1 = new Header("accept", "application/json");
//		Header h2 = new Header("Content-Type", "application/json");
//		Header h3 = new Header("Authorization", "Bearer " + logtoken);
//		head.add(h1);
//		head.add(h2);
//		head.add(h3);
//		Headers headers = new Headers(head);
//		addHeaders(headers);
//		// 2.PayLoad
////		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(addressId);
//		addBody(AddressPayload.deleteAdress(addressId));
//		// req
//		Response response = reqType("DELETE", Endpoints.DeleteUserAddress);
//		int statusCode = getStatusCode(response);
//		Assert.assertEquals(statusCode, 200, "verify statuscode");
//		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
//		String message = deleteUserAddress_Output_Pojo.getMessage();
//		Assert.assertEquals(message, "Address deleted successfully", "verify Address deleted successfully");
//
//	}

	@Test(priority = 8)
	private void GetuserAddress() {
//1.Header
		List<Header> head = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		head.add(h1);
		head.add(h2);
		Headers headers = new Headers(head);
		addHeaders(headers);
//req
		Response response = reqType("GET", Endpoints.GetUserAddress);
		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "verify StatusCode");
//response
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
//get required user address
		ArrayList<UserDetail> AddressList = getUserAddress_Output_Pojo.getData();
		for (UserDetail eachAddressDetail : AddressList) {
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
//	@Test(priority = 9)
//	private void ProductList() {
//		//1.header
//
//				List<Header> head = new ArrayList<>();
//				Header h1 = new Header("accept", "application/json");
//				Header h2 = new Header("Content-Type", "application/json");
//				head.add(h1);
//				head.add(h2);
//
//				Headers headers = new Headers(head);
//				addHeaders(headers);
//		//req
//				Response response = reqType("POST", Endpoints.productlist);
//				int statusCode = getStatusCode(response);
//				Assert.assertEquals(statusCode,200,"verify StatusCode");
//		
//
//	}

	@Test(priority = 10)
	private void GetSearchDetails() {
	// 1.header

		List<Header> head = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + logtoken);
		head.add(h1);
		head.add(h2);
		head.add(h3);
		Headers headers = new Headers(head);
		addHeaders(headers);
	// req
		Response response = reqType("POST", Endpoints.GetSearchProduct);
		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "verify StatusCode");

	}

//	@Test(priority = 11)
//	private void AddToCart() {
//
//	}
//	@Test(priority =11)
//	private void GetCartItem() {
//		
//
//	}
//	@Test(priority =12)
//	private void DeleteCartItem() {
//		
//
//	}
//	@Test(priority =13)
//	private void ClearCart() {
//		
//
//	}
//	@Test(priority =14)
//	private void SetAddress() {
//		
//
//	}
//	@Test(priority =15)
//	private void CreateOrder() {
//		
//
//	}
//	@Test(priority =16)
//	private void GetDeliverySlot() {
//		
//
//	}
//	@Test(priority =17)
//	private void GetAllAdress() {
//		
//
//	}
//	@Test(priority =18)
//	private void CancelOrder() {
//		
//
//	}

}