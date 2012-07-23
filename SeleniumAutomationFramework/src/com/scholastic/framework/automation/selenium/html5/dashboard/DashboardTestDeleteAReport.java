package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;
import com.scholastic.framework.automation.selenium.html5.AutomationTest.Browsers;

public class DashboardTestDeleteAReport extends AutomationTest {

	@Override
	public void testStart() {

			try {
			
				
				this.command_setBrowser(Browsers.IE8);
				this.command_login("teacher03", "");
				this.deleteReport();
				this.command_logout();
				
				
			} catch (Exception v_exException) {
				this.handleException(v_exException);
			}
		}
private void deleteReport() {
	this.command_clickLink("Reports");
	//this.command_clickButton("c0sk7vu1er1n1q7rh0d1blck-h910h03-icon.ui-icon-trash");
	//this.command_selectTab("Demographics");	
		}

}
		

	


