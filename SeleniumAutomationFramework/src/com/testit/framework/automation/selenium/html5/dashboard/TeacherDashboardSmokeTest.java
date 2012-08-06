package com.testit.framework.automation.selenium.html5.dashboard;

import com.testit.framework.automation.selenium.html5.AutomationTestSuite;

public class TeacherDashboardSmokeTest extends AutomationTestSuite {

	@Override
	protected void addTestsToSuite() {
		this.command_setTestSuiteName("Dashboard Smoke Test");
		this.addTest(new DashboardTestLogin());
		this.addTest(new DashboardTestProductSettings());
		this.addTest(new DashboardTestSearchStudent());
		this.addTest(new DashboardTestAddStudent());
		this.addTest(new DashboardTestAddClass());
		this.addTest(new DashboardTestReports());
	}

}
