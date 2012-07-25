package com.scholastic.framework.automation.selenium.scriptplayer;

import com.scholastic.framework.Controller;

public class ScriptPlayerController extends Controller {

	public void testScript (String prm_sFileName) {
		ScriptPlayerFuncPlayTestCase v_fn;
		v_fn = new ScriptPlayerFuncPlayTestCase();
		v_fn.setFilename(prm_sFileName);
		v_fn.startFunction();
	}
	
}
