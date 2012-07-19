package com.scholastic.framework.automation.selenium.html5;

import com.scholastic.framework.Controller;

public class AutomationController extends Controller {

	private static AutomationController g_objController = null;
	public static AutomationController getInstance () {
		if (null == AutomationController.g_objController) {
			AutomationController.g_objController = new AutomationController();
		}
		return AutomationController.g_objController;
	}
	
}
