package com.omrbranch.stepdefenition;

import org.junit.Assert;

import io.cucumber.java.en.Then;

public class commonstep {
	@Then("User should verify the status code is {int}")
	public void user_should_verify_the_status_code_is(int expstatusCode) {
		int actstatusCode = TC1_LoginStep.globaldatas.getStatusCode();
		Assert.assertEquals("verify status code", expstatusCode, actstatusCode);
	}


}
