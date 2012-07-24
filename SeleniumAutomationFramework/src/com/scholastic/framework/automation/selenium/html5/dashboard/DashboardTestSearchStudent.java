package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;

public class DashboardTestSearchStudent extends AutomationTest {
//class to test the search a student link
	@Override
	public void testStart() {
try {
		
			
			this.command_login("teacher03", "Welcome1");
			this.searchStudentLink();
			this.command_logout();
			
			
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}
	//method to test search student link
	private void searchStudentLink() {
		//method to test search student link
		this.command_clickLink("Gateway");
		this.command_clickLink("Search Student");
		this.command_close();
	}
	}


