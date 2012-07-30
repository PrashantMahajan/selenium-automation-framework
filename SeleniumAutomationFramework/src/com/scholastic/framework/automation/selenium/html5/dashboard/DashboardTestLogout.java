package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;

public class DashboardTestLogout extends AutomationTest {

	@Override
	public void testStart() {
		try {
			this.command_logout();
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

}
