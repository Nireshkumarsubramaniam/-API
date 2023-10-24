package com.omrbranch.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.omrbranch.base.Baseclass;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting extends Baseclass {

	public static void generateJvmReport(String jsonFile) throws FileNotFoundException, IOException {
		
		File file = new File(getProjectPath()+getPropertyFileValue("jvmPath"));
		
		Configuration configuration = new Configuration(file, "OMRBranchHotelAutomation");
		
		configuration.addClassifications("OS", "WIN 11");
		configuration.addClassifications("browserName", "chrome");
		configuration.addClassifications("browser version", "119");
		configuration.addClassifications("sprint", "34");
		
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add(jsonFile);
		
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		reportBuilder.generateReports();
				
	}
}
