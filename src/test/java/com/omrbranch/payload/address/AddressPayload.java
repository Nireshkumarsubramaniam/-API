package com.omrbranch.payload.address;

import com.omrbranch.pojo.address.AddUserAddressInput_Pojo;
import com.omrbranch.pojo.address.CityList_Input_Pojo;
import com.omrbranch.pojo.address.DeleteUserAddress_Input_Pojo;
import com.omrbranch.pojo.address.UpdateUserAddress_Input_Pojo;

public class AddressPayload {
	public static CityList_Input_Pojo getCityListPayLoad(String StateIdText) {
		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(StateIdText); 
		return cityList_Input_Pojo;
	}
	public static AddUserAddressInput_Pojo addaddresspayload(String first_name, String last_name, String mobile, String apartment, int state, int city,
			int country, String zipcode, String address, String address_type) {
		AddUserAddressInput_Pojo addUserAddressInput_Pojo = new AddUserAddressInput_Pojo( first_name,  last_name,  mobile,  apartment,  state,  city,
				 country,  zipcode,  address,  address_type);	
		return addUserAddressInput_Pojo;
		
	}
	
	public static UpdateUserAddress_Input_Pojo updateaddresspayload(String addressId, String first_name, String last_name, String mobile, String apartment, int state, int city,
			int country, String zipcode, String address, String address_type) {
		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(addressId ,first_name,  last_name,  mobile,  apartment,  state,  city,
				 country,  zipcode,  address,  address_type);	
		return updateUserAddress_Input_Pojo;
		
	}
	public static DeleteUserAddress_Input_Pojo deleteAdress(String addressId ) {
		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(addressId);
		return deleteUserAddress_Input_Pojo;
		
		
	}
}
