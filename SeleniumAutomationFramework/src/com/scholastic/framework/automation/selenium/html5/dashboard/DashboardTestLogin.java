package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;

public class DashboardTestLogin extends AutomationTest {

	private String g_sUsername = "teacher03";
	private String g_sPassword = "Welcome1";

	public void setPassword(String prm_sPassword) {
		this.g_sPassword = prm_sPassword;
	}

	public void setUsername(String prm_sUserName) {
		this.g_sUsername = prm_sUserName;
	}

	@Override
	public void testStart() {
		try {
			this.command_setBrowser(Browsers.FIREFOX);
			this.command_login(this.g_sUsername, this.g_sPassword);
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}
	
}
