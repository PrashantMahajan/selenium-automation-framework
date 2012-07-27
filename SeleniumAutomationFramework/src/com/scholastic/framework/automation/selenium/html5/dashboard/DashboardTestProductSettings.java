package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;

public class DashboardTestProductSettings extends AutomationTest {

	@Override
	public void testStart() {
		try {
			this.command_login("teacher03", "Welcome1");
			this.productSettings();
			this.command_logout();
			AutomationTest.getDriver().close();
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}
	//test settings link for a class
	private void productSettings() {
		this.command_clickLink("Settings");
		this.command_waitInSeconds(5);
	
		//this.command_close();
	}

	}

