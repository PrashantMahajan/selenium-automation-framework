package com.scholastic.framework.automation.selenium.scriptplayer;

import org.junit.Test;

import junit.framework.TestCase;

public class ScriptPlayerFuncPlayTestCaseTest extends TestCase {

	@Test
	public void test() {
		ScriptPlayerController.getInstance().runTestScriptAsAFile("Login.xml");
	}

}
