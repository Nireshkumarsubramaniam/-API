package com.omrbranch.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Baseclass {
	static RequestSpecification reqSpec;
	static Response response;

	public static void addHeader(String key, String value) {
		// Headers are developer defined("content-Type","application/json")
		// reqSpec = reqSpec.header(key,value);
		reqSpec = RestAssured.given().header(key, value);

	}
	public void addHeaders(Headers headers) {

		reqSpec = RestAssured.given().headers(headers);
	}

	public void addBasicAuth(String username, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(username, password);

	}
	public static void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}
	public static void addBody(Object body) {
		reqSpec = reqSpec.body(body);
	}

	public static Response reqType(String type, String endpoint) {

		switch (type) {
		case "GET":
			response = reqSpec.log().all().get(endpoint);
			break;
		case "POST":
			response = reqSpec.post(endpoint);
			break;
		case "PUT":
			response = reqSpec.put(endpoint);
			break;
		case "DELETE":
			response = reqSpec.delete(endpoint);
			break;
		case "PATCH":
			response = reqSpec.patch(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	public static int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public static String getResBodyAsString(Response response) {
		String asString = response.asString();
		return asString;
	}

	public static String getResAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;
	}
	public static String getProjectPath() {
		String path = System.getProperty("user.dir");
		return path;
	}

	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(getProjectPath()
				+ "\\config\\config.properties"));
		Object object = properties.get(key);
		String value =(String)object;
		return value;
	}



}
