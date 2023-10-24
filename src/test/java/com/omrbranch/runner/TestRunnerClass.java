package com.omrbranch.runner;



import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.omrbranch.base.Baseclass;
import com.omrbranch.report.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
@RunWith(Cucumber.class)
@CucumberOptions(tags = ("@Login or @State or @City or @AddAddress or @UpdateAddress or @SearchProduct"), stepNotifications = true, snippets = SnippetType.UNDERSCORE, publish = true, monochrome = false, dryRun = false, plugin = {
		"pretty", "json:target\\index.json" }, features = "src/test/resources", glue = "com.omrbranch.stepdefenition")


public class TestRunnerClass {
	
	public class testrunnerclass extends Baseclass{
		@AfterClass
		public void afterClass() throws FileNotFoundException, IOException {
			Reporting.generateJvmReport(getProjectPath() + getPropertyFileValue("jsonPath"));

		}
	}


}
//@Login or @State or @City or @AddAddress or @UpdateAddress or @DeleteAddress or @GetAddress or @SearchProduct