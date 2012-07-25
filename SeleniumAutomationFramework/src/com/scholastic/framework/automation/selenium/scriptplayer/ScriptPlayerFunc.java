package com.scholastic.framework.automation.selenium.scriptplayer;

import com.scholastic.framework.exceptionhandling.ExceptionController;

import junit.framework.TestCase;

public abstract class ScriptPlayerFunc extends TestCase {

	private ExceptionController g_objExceptionHandler;

	public void handleException (Exception prm_exException) {
		if (null == this.g_objExceptionHandler) {
			this.g_objExceptionHandler = ExceptionController.getInstance();
		}
		this.g_objExceptionHandler.handleException(prm_exException);
	}
	
	abstract public void startFunction();
	
}
