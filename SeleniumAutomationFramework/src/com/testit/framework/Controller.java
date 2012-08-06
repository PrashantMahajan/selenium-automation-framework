package com.testit.framework;

import junit.framework.TestCase;

import com.testit.framework.exceptionhandling.ExceptionController;

/**
 * <br /> Base class for all the controllers in the automation framework.
 * @author prashant
 */
public abstract class Controller extends TestCase {

	/**
	 * Handles the exception using {@link ExceptionController}
	 * @param prm_exException Exception that is required to be sent to the Error console and as an e-mail.
	 */
	public void handleException (Exception prm_exException) {
		ExceptionController.getInstance().handleException(prm_exException);
	}
	
}
