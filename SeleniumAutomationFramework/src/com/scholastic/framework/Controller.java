package com.scholastic.framework;

import junit.framework.TestCase;

import com.scholastic.framework.exceptionhandling.ExceptionController;

public abstract class Controller extends TestCase {

	public void handleException (Exception prm_exException) {
		ExceptionController.getInstance().handleException(prm_exException);
	}
	
}
