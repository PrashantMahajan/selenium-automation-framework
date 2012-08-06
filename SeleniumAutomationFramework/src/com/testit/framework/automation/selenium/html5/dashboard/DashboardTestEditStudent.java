package com.testit.framework.automation.selenium.html5.dashboard;

import com.testit.framework.automation.selenium.html5.AutomationTest;

public class DashboardTestEditStudent extends AutomationTest {

	private String g_sUsername = null;
	private String g_sPassword = null;

	@Override
	public void testStart() {
		try {
			this.command_login("MichaelJackson", "abcdefg1ijg");
			this.makeStudent();
			this.command_logout();
			//this.editStudent();
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	private void editStudent() {
		String v_sFirstName;
		String v_sLastName;

		v_sFirstName = this.command_excel_getCellValue("TestCaseData.xlsx", "Student", "First Name");
		v_sLastName = this.command_excel_getCellValue("TestCaseData.xlsx", "Student", "Last Name");

		selenium.click("xpath=(//a[contains(text(),'Roster')])[10]");
		selenium.click("xpath=(//a[contains(text(),'" + v_sFirstName + " " + v_sLastName +  "')])[4]");
		selenium.select("id=grade", "label=Pre-Kindergarten");
		selenium.click("//button[@type='button']");
	}

	private void makeStudent() {
		this.command_clickLink("Gateway");
		this.command_clickLink("Add a Student");

		this.command_excel_fillForm("TestCaseData.xlsx", "Student");//Fills the form from Excel sheet.

		this.command_controlSetValue("Student ID", this.command_randomText(10));
		this.g_sPassword = "p" + this.command_randomText(9) + "2";
		this.g_sUsername = "a1" + this.command_randomText(9);
		this.command_controlSetValue("Username*", this.g_sUsername);
		this.command_controlSetValue("Password", this.g_sPassword);
		this.command_controlSetValue("Password Confirmation", this.g_sPassword);
		this.command_clickCheckbox("ce698o2fgh8vsqvvkmouccvb-h910h03");
		this.command_selectTab("Demographics");
		this.command_clickCheckbox("Economically Disadvantaged");
		this.command_clickCheckbox("Gifted and Talented");
		this.command_clickCheckbox("Migrant");

		this.command_selectRadioButton("gender", "Male");
		this.command_selectRadioButton("ethnicity", "American Indian/Alaskan Native");

		this.command_save();
	}



}


