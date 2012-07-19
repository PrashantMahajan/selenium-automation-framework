package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;

public class DashboardTestAddClass extends AutomationTest {

	@Override
	public void testStart() {
		/*
		 * Go to Gateway
		 * Click "Add a Class" link
		 * Enter data in mandatory fields
		 * class name
		 * select a grade and Application
		 * Go to Student Roster tab and select students that you want to put in this class.
		 * Click SAVE
		 * 
		 */
		this.command_login("teacher03", "Welcome1");
		this.command_clickLink("Gateway");
		this.command_clickLink("Add a Class");
		this.command_enterText("display_name", this.command_randomText(10));
		this.command_clickCheckbox("grade0");
		this.command_clickCheckbox("application[0]");
		this.command_selectTab("Student Roster");
		this.command_clickCheckbox("2j24c95s8eits58gl3o5unbi_h910h03");
	}

}
