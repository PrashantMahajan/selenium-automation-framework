package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.Controller;

public class DashboardSmokeTestCase extends Controller {

	public static DashboardSmokeTestCase getInstance () {
		return new DashboardSmokeTestCase();
	}

	public void testLogin() {
		DashboardTestLogin v_tst;
		v_tst = new DashboardTestLogin();
		v_tst.testStart();
		v_tst.command_waitForHalfASecond();
	}
	public void testLogin (String prm_sUserName, String prm_sPassword) {
		DashboardTestLogin v_tst;
		v_tst = new DashboardTestLogin();
		v_tst.setUsername(prm_sUserName);
		v_tst.setPassword(prm_sPassword);
		v_tst.testStart();
		v_tst.command_waitForHalfASecond();
	}
	
	public void testAddStudent () {
		DashboardTestAddStudent v_tst;
		v_tst = new DashboardTestAddStudent();
		v_tst.testStart();
		v_tst.command_waitForHalfASecond();
	}

	public void testAddClass () {
		DashboardTestAddClass v_tst;
		v_tst = new DashboardTestAddClass();
		v_tst.testStart();
		v_tst.command_waitForHalfASecond();
	}
	
	public void testReports () {
		DashboardTestReports v_tst;
		v_tst = new DashboardTestReports();
		v_tst.testStart();
		v_tst.command_waitForHalfASecond();
	}

}
