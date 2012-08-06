package com.testit.framework.automation.selenium.html5;

import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;

public class SeleniumAutomationTest extends DefaultSelenium {

	public SeleniumAutomationTest(CommandProcessor prm_objProcessor) {
		super(prm_objProcessor);
	}

	public SeleniumAutomationTest(String prm_sServerHost, int prm_sServerPort, String prm_sBrowserStartCommand, String prm_sBrowserURL) {
		super(prm_sServerHost, prm_sServerPort, prm_sBrowserStartCommand, prm_sBrowserURL);
	}

}
