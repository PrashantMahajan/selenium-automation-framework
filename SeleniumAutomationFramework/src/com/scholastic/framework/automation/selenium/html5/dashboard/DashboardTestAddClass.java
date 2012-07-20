 package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;

public class DashboardTestAddClass extends AutomationTest {

	
	@Override
	public void testStart() {
		try {
		
			
			this.command_setBrowser(Browsers.IE8);
			this.command_login("teacher03", "");
			this.makeClass();
			this.command_logout();
			
			
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	private void makeClass() {
		
		this.command_clickLink("Gateway");
		this.command_clickLink("Add a Class");
		this.command_cancel();
		this.command_clickLink("Gateway");
		this.command_clickLink("Add a Class");
		this.command_enterText("display_name",this.command_uniqueText(10));
		this.command_clickCheckbox("grade");
		this.command_clickCheckbox("grade1");
		this.command_clickCheckbox("grade2");
		this.command_clickCheckbox("application[0]");
		//go to student roster tab
		this.command_selectTab("Student Roster");
		//select the students that you want to include in the class
		this.command_clickCheckbox("e3l7u7afjqahrne3egaqal3b-h910h03");
		this.command_clickCheckbox("n17petl3qga082ept57ol5i9-h910h03");
		this.command_clickCheckbox("rathg62rcmq005nncfoblijn-h910h03");
		this.command_clickCheckbox("0a284osg3uqgcuivuajru5c3-h910h03");
		this.command_save();
	
		
	
	
	}

}