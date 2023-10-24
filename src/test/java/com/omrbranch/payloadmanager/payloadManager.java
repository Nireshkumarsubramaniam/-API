package com.omrbranch.payloadmanager;

import com.omrbranch.payload.address.AddressPayload;
import com.omrbranch.payload.product.ProductPayload;

public class payloadManager {
	private static AddressPayload addressPayload;
	private static ProductPayload productPayload;
	
	public static AddressPayload getAddressPayload() {
		return (addressPayload==null)?addressPayload = new AddressPayload():addressPayload;
	}
	public static ProductPayload getProductPayload() {
		return (productPayload==null)?productPayload = new ProductPayload():productPayload;
	}

}
