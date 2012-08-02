package com.scholastic.framework.automation.selenium.html5;

import com.scholastic.framework.automation.selenium.html5.dashboard.DashboardTestAddClass;

public class AutomationTestSuiteTest extends AutomationTestSuite {

	@Override
	protected void addTestsToSuite() {
		this.addTest(new DashboardTestAddClass());
	}

}
