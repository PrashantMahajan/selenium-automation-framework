package com.testit.framework.automation.selenium.html5;

import com.testit.framework.automation.selenium.html5.AutomationTestSuite;
import com.testit.framework.automation.selenium.html5.dashboard.DashboardTestAddClass;
import com.testit.framework.automation.selenium.html5.dashboard.DashboardTestAddStudent;

public class AutomationTestSuiteTest extends AutomationTestSuite {

	@Override
	protected void addTestsToSuite() {
		this.addTest(new DashboardTestAddClass());
		this.addTest(new DashboardTestAddStudent());
	}

}
