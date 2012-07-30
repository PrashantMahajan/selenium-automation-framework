package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest.Browsers;

import junit.framework.TestCase;

public class TeacherDashboardSmokeTest extends TestCase {

	public void testStart() {
		this.runFirefox();
//		this.runIE8();
//		this.runSafari();
	}

	private void runAddAClassTestOnFireFox() {
		DashboardTestAddClass v_tst;
		v_tst = new DashboardTestAddClass();
		v_tst.command_setBrowser(Browsers.FIREFOX);
		v_tst.testStart();
	}

	private void runAddAClassTestOnIE() {
		DashboardTestAddClass v_tst;
		v_tst = new DashboardTestAddClass();
		v_tst.command_setBrowser(Browsers.IE8);
		v_tst.testStart();
	}

	private void runAddAClassTestOnSafari() {
		DashboardTestAddClass v_tst;
		v_tst = new DashboardTestAddClass();
		v_tst.command_setBrowser(Browsers.SAFARI);
		v_tst.testStart();
		v_tst.command_waitInSeconds(2);
	}

	private void runAddAStudentTestOnFirefox() {
		DashboardTestAddStudent v_tst;
		v_tst = new DashboardTestAddStudent();
		v_tst.command_setBrowser(Browsers.FIREFOX);
		v_tst.testStart();
	}

	private void runAddAStudentTestOnIE() {
		DashboardTestAddStudent v_tst;
		v_tst = new DashboardTestAddStudent();
		v_tst.command_setBrowser(Browsers.IE8);
		v_tst.testStart();
	}

	private void runAddAStudentTestOnSafari() {
		DashboardTestAddStudent v_tst;
		v_tst = new DashboardTestAddStudent();
		v_tst.command_setBrowser(Browsers.SAFARI);
		v_tst.testStart();
		v_tst.command_waitInSeconds(2);
	}

	private void runAReportOnFirefox() {
		DashboardTestReports v_tst;
		v_tst = new DashboardTestReports();
		v_tst.command_setBrowser(Browsers.FIREFOX);
		v_tst.testStart();
	}

	private void runAReportOnIE() {
		DashboardTestReports v_tst;
		v_tst = new DashboardTestReports();
		v_tst.command_setBrowser(Browsers.IE8);
		v_tst.testStart();
	}

	private void runAReportOnSafari() {
		DashboardTestReports v_tst;
		v_tst = new DashboardTestReports();
		v_tst.command_setBrowser(Browsers.SAFARI);
		v_tst.testStart();
	}
	private void testSearchStudentLinkFirefox() {
		DashboardTestSearchStudent v_tst;
		v_tst = new DashboardTestSearchStudent();
		v_tst.command_setBrowser(Browsers.FIREFOX);
		v_tst.testStart();
	}

	private void testSearchStudentlinkIE() {
		DashboardTestSearchStudent v_tst;
		v_tst = new DashboardTestSearchStudent();
		v_tst.command_setBrowser(Browsers.IE8);
		v_tst.testStart();
	}

	private void testSearchStudentLinkSafari() {
		DashboardTestSearchStudent v_tst;
		v_tst = new DashboardTestSearchStudent();
		v_tst.command_setBrowser(Browsers.SAFARI);
		v_tst.testStart();
	}
	private void runFirefox() {
		this.runAddAStudentTestOnFirefox();
		this.runAddAClassTestOnFireFox();
		this.runAReportOnFirefox();
		this.testSearchStudentLinkFirefox();
	
	}

	private void runIE8() {
		this.runAddAStudentTestOnIE();
		this.runAddAClassTestOnIE();
		this.runAReportOnIE();
		this.testSearchStudentlinkIE();
	}

	private void runSafari() {
		this.runAddAClassTestOnSafari();
		this.runAddAStudentTestOnSafari();
		this.runAReportOnSafari();
		this.testSearchStudentLinkSafari();
		
	}
	
}
