package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;

public class DashboardTestDeleteAReport extends AutomationTest {

	@Override
	public void testStart() {
		try {
			//this.command_setBrowser(Browsers.IE8);
			this.command_login("teacher03", "Welcome1");
			this.deleteReport();
			this.command_logout();
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	private void deleteReport() {
		this.command_clickLink("Reports");
		this.command_clickButton("confirmReportbtn");
		this.command_clickButton("runReportbtn");
	}
}

