package com.scholastic.framework.reports;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;
import com.scholastic.framework.context.ApplicationContext;

public class ExportFuncExportDataToQC extends ExportFunc {

	private StringBuilder g_sbReport = new StringBuilder();
	private String g_sTestSuiteName;
	private String g_sTodaysDate = "";
	private Set<AutomationTest> g_lAllFailedTests;
	private Set<AutomationTest> g_lAllTests;
	private ApplicationContext g_objContext = ApplicationContext.getInstance();

	public void setAllTests(Set<AutomationTest> prm_lAllTests) {
		this.g_lAllTests = prm_lAllTests;
	}

	public void setFailedTest(Set<AutomationTest> prm_lAllFailedTests) {
		this.g_lAllFailedTests = prm_lAllFailedTests;
	}

	public void setTestSuiteName(String prm_sTestSuiteName) {
		this.g_sTestSuiteName = prm_sTestSuiteName;
	}

	@Override
	public void startFunction() {
		try {
			this.setInitalVariables();
			this.generateReportFileContents();
			this.exportReportFile();
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void setInitalVariables() {
		Calendar v_objCalendar;
		v_objCalendar = new GregorianCalendar();
		this.g_sTodaysDate = v_objCalendar.get(Calendar.MONTH) + "/" + v_objCalendar.get(Calendar.DATE) + "/" + v_objCalendar.get(Calendar.YEAR);
	}

	private void createSteps() {
		int v_iI = 0;
		try {
			this.g_sbReport.append("" +
				"\n<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
				"\n<run-sequence>" +
				"\n	<run>" +
				"\n		<server_name>"+ this.g_objContext.getProperty("url") +"</server_name>" +
				"\n		<name>" + this.g_sTestSuiteName +"</name>" +
				"\n		<status>" + (this.g_lAllFailedTests.size() > 0? "Failed" : "Pass") + "</status>" +
				"\n		<comments></comments>" +
				"\n		<execution_date>" + this.g_sTodaysDate + "</execution_date>" +
				"\n		<step-sequence>"
			);
				
			for (AutomationTest v_objTest : this.g_lAllTests) {
				this.createStep(v_objTest, ++v_iI);
			}
				
			this.g_sbReport.append("\n			<step id=\"1\">" +
				"\n				<status>Pass</status>" +
				"\n				<execution_date>08/01/2012</execution_date>" +
				"\n				<execution_time>20</execution_time>" +
				"\n				<step_description>Add A Student On Firefox</step_description>" +
				"\n				<Content>This tests the creation of a Student on Firefox</Content>" +
				"\n			</step>" +
				"\n			<step id=\"1\">" +
				"\n				<status>Fail</status>" +
				"\n				<execution_date>08/01/2012</execution_date>" +
				"\n				<execution_time>30</execution_time>" +
				"\n				<step_description>Add A Student On Safari</step_description>" +
				"\n				<Content>This tests the creation of a Student on IE</Content>" +
				"\n			</step>" +
				"\n		</step-sequence>" +
				"\n	</run>" +
				"\n</run-sequence>"
			);
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void createStep(AutomationTest v_objTest, int prm_iStepIndex) {
		this.g_sbReport.append(
			"\n			<step id=\"" + prm_iStepIndex + "\">" +
			"\n				<status>" + this.doesTheTestFailed(v_objTest) + "</status>" +
			"\n				<execution_date>" + this.g_sTodaysDate + "</execution_date>" +
			"\n				<execution_time>30</execution_time>" +
			"\n				<step_description>Add A Student On Safari</step_description>" +
			"\n				<Content>This tests the creation of a Student on IE</Content>" +
			"\n			</step>"
		);
	}

	private String doesTheTestFailed(AutomationTest v_objTest) {
		// TODO Auto-generated method stub
		return null;
	}

	private void exportReportFile() {
		try {
			this.getContoller().exportReportDataToGit(this.g_sbReport.toString());
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void generateReportFileContents() {
		try {
			this.createSteps();
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}
}
