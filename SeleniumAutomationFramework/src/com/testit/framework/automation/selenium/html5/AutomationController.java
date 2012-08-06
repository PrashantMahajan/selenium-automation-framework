package com.testit.framework.automation.selenium.html5;

import com.testit.framework.Controller;

public class AutomationController extends Controller {

	private static AutomationController g_objController = null;
	public static AutomationController getInstance () {
		if (null == AutomationController.g_objController) {
			AutomationController.g_objController = new AutomationController();
		}
		return AutomationController.g_objController;
	}
	
}
