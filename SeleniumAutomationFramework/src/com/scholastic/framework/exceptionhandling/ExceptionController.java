package com.scholastic.framework.exceptionhandling;

import com.scholastic.framework.Controller;
import com.scholastic.framework.logger.LoggerController;

/**
 * One stop-shop for all the exception/test-case failure cases.
 * @author prashant
 */
public class ExceptionController extends Controller {

	private static ExceptionController g_objInstance;
	/**
	 * Returns the instance of the ExceptionController.
	 */
	public static ExceptionController getInstance () {
		if (null == ExceptionController.g_objInstance) {
			ExceptionController.g_objInstance = new ExceptionController();
		}
		return ExceptionController.g_objInstance;
	}
	
	/**
	 * Prints the exception trace to the currently active console, then sends and e-mail with stack-trace and screenshot of the browser.
	 * @param prm_exException : Exception, whose stack trace is required to be printed.
	 */
	@Override
	public void handleException (Exception prm_exException) {
		prm_exException.printStackTrace();
		LoggerController.getInstance().sendEmail(prm_exException);
	}

}
