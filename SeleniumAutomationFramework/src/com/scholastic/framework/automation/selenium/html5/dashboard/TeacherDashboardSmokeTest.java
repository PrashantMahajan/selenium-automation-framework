package com.scholastic.framework.automation.selenium.html5.dashboard;

import java.util.LinkedList;
import java.util.List;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;
import com.scholastic.framework.exceptionhandling.ExceptionController;

public class TeacherDashboardSmokeTest extends AutomationTest {

	private List<Class<AutomationTest>> g_lAllTests = new LinkedList<Class<AutomationTest>>();
	public void testStart() {
		this.runForAllBrowsers();

	}
// User can add any browser here.
	private void runForAllBrowsers() {
		int v_iI = 0;
		Browsers[] v_allBrowsers = {Browsers.FIREFOX, Browsers.SAFARI, Browsers.IE8};
		AutomationTest v_objTest;

		try {
			this.attachTestCases();
			for (v_iI = 0; v_iI < v_allBrowsers.length; v_iI++) {
				for (Class<AutomationTest> v_objClass : this.g_lAllTests) {
					v_objTest = v_objClass.newInstance();
					v_objTest.command_setBrowser(v_allBrowsers[v_iI]);
					v_objTest.testStart();
					v_objTest.command_closeBrowserWindow();
				}
			}
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}
//List all your Testcases here.
	private void attachTestCases() {
		this.g_lAllTests.add((Class<AutomationTest>)(Object)DashboardTestLogin.class);
		this.g_lAllTests.add((Class<AutomationTest>)(Object)DashboardTestProductSettings.class);
		this.g_lAllTests.add((Class<AutomationTest>)(Object)DashboardTestSearchStudent.class);
		this.g_lAllTests.add((Class<AutomationTest>)(Object)DashboardTestAddStudent.class);
		this.g_lAllTests.add((Class<AutomationTest>)(Object)DashboardTestAddClass.class);
		this.g_lAllTests.add((Class<AutomationTest>)(Object)DashboardTestReports.class);
		
	}

	

}
