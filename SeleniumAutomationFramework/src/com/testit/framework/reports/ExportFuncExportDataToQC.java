package com.testit.framework.reports;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import com.testit.framework.automation.selenium.html5.AutomationTest;
import com.testit.framework.context.ApplicationContext;

/**
 * This function call is responsible for exporting the Report data to QC.
 * @author prashant
 */
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
		try {
			v_objCalendar = new GregorianCalendar();
			this.g_sTodaysDate = v_objCalendar.get(Calendar.MONTH) + "/" + v_objCalendar.get(Calendar.DATE) + "/" + v_objCalendar.get(Calendar.YEAR);
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
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
				
			this.g_sbReport.append(
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
			"\n				<status>" + this.getTestStatus(v_objTest) + "</status>" +
			"\n				<execution_date>" + this.g_sTodaysDate + "</execution_date>" +
			"\n				<execution_time>" + v_objTest.getExecutionTime() + "</execution_time>" +
			"\n				<step_description>" + v_objTest.getName() + "</step_description>" +
			"\n				<Content>" + v_objTest.getName() + "</Content>" +
			"\n			</step>"
		);
	}

	private String getTestStatus(AutomationTest prm_objTest) {
		String v_Return = "Pass";
		String v_sClassname;
		try {
			v_sClassname = prm_objTest.getClass().getName();
			for (AutomationTest v_objTest : this.g_lAllTests) {
				if (v_objTest.getClass().getName().equals(v_sClassname)) {
					v_Return = "Fail";
				}
			}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
		return v_Return;
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
