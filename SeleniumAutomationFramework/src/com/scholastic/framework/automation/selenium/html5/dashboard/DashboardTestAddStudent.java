package com.scholastic.framework.automation.selenium.html5.dashboard;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;

public class DashboardTestAddStudent extends AutomationTest {

	private String g_sUsername = null;
	private String g_sPassword = null;
	@Override
	public void testStart() {
		try {
			this.command_login("teacher03", "Welcome1");
			this.makeStudent();
			this.command_logout();
			this.command_login("teacher03", "Welcome1");
			this.testStudent();
			this.command_logout();
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	private void makeStudent() {
		this.command_clickLink("Gateway");
		this.command_clickLink("Add a Student");
		this.command_enterText("sis_id", this.command_randomText(10));
		this.command_enterText("first_name", "zTestFirstName");
		this.command_enterText("middle_name", "zTestMiddleName");
		this.command_enterText("last_name", "zTestLastName");
		this.command_selectComboBox("grade", "Fifth grade");
		this.command_enterText("suffix", "zTestLastName");
		this.command_enterText("preferred_name", "prefname");
		this.g_sPassword = this.command_randomText(9);
		this.g_sUsername = "a1" + this.command_randomText(9);
		this.command_enterText("user_name", this.g_sUsername);
		this.command_enterText("password", this.g_sPassword);
		this.command_enterText("password_confirmation", this.g_sPassword);

		this.command_selectTab("Demographics");

		this.command_clickCheckbox("ayp_econ_disadvantaged");
		this.command_clickCheckbox("ayp_gifted_talented");
		this.command_clickCheckbox("ayp_migrant");

		this.command_selectRadioButton("gender", "gender_female");
		this.command_selectRadioButton("ethnicity", "ethnic_am_ind_ak");
		this.command_save();
	}

	private void testStudent() {
		
	}
}
