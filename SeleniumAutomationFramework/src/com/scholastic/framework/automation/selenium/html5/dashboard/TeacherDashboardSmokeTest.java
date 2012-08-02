package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTestSuite;

public class TeacherDashboardSmokeTest extends AutomationTestSuite {

	@Override
	protected void addTestsToSuite() {
		this.addTest(new DashboardTestLogin());
		this.addTest(new DashboardTestProductSettings());
		this.addTest(new DashboardTestSearchStudent());
		this.addTest(new DashboardTestAddStudent());
		this.addTest(new DashboardTestAddClass());
		this.addTest(new DashboardTestReports());
	}

}
