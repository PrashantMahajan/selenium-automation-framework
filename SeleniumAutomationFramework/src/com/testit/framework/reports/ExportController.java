package com.testit.framework.reports;

import java.util.Set;

import com.testit.framework.Controller;
import com.testit.framework.automation.selenium.html5.AutomationTest;

/**
 * This controller defines all the methods to export the data to the 3rd Party vendors/external servers.
 */
public class ExportController extends Controller {
	
	private static ExportController g_objInstance = null;
	/**
	 * Returns an instance of the ExportController.
	 */
	public static ExportController getInstance () {
		if (null == ExportController.g_objInstance) {
			ExportController.g_objInstance = new ExportController();
		}
		return ExportController.g_objInstance;
	}
	private ExportController () {
	}
	/**
	 * If called, this method looks at the current ApplicationContext, fetches the information of all the test cases that have failed. 
	 * Then generates a report, finally pushes the report to the specified QC server.
	 * @param prm_sTestSuiteName : Name of the Test Suite.
	 * @param prm_lAllTests : All the Tests that have been executed in the Suite.
	 * @param prm_lAllFailedTests : List of all the test cases that have failed.
	 */
	public void exportDataToQC (String prm_sTestSuiteName, Set<AutomationTest> prm_lAllTests, Set<AutomationTest> prm_lAllFailedTests) {
		ExportFuncExportDataToQC v_fn;
		v_fn = new ExportFuncExportDataToQC();
		v_fn.setTestSuiteName(prm_sTestSuiteName);
		v_fn.setAllTests(prm_lAllTests);
		v_fn.setFailedTest(prm_lAllFailedTests);
		v_fn.startFunction();
	}
	/**
	 * The method exports the desired Report data to the QC server. The configuration of the QC server must be specified in the
	 * TestCass.Properties file.
	 */
	public void exportReportDataToGit (String prm_sReportData) {
		ExportFuncExportDataToGitRepository v_fn;
		v_fn = new ExportFuncExportDataToGitRepository();
		v_fn.setReportData(prm_sReportData);
		v_fn.startFunction();
	}
}
