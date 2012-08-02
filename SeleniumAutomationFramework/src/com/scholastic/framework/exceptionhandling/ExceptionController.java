package com.scholastic.framework.exceptionhandling;

import com.scholastic.framework.Controller;
import com.scholastic.framework.context.ApplicationContext;

/**
 * One stop-shop for all the exception/test-case failure cases.
 * @author prashant
 */
public class ExceptionController extends Controller {

	private static ExceptionController g_objInstance = null;
	/**
	 * Returns the instance of the ExceptionController.
	 */
	public static ExceptionController getInstance () {
		if (null == ExceptionController.g_objInstance) {
			ExceptionController.g_objInstance = new ExceptionController();
		}
		return ExceptionController.g_objInstance;
	}
	private ExceptionController () {
	}

	/**
	 * Prints the exception trace to the currently active console, then sends and e-mail with stack-trace and screenshot of the browser.
	 * @param prm_exException : Exception, whose stack trace is required to be printed.
	 */
	@Override
	public void handleException (Exception prm_exException) {
		ExceptionFuncHandleException v_fn;
		v_fn = new ExceptionFuncHandleException();
		v_fn.setException(prm_exException);
		v_fn.startFunction();
	}
	
	/**
	 * Registers the Exception Event Listener to the context
	 */
	public void addExceptionEventListener (IExceptionEventListener prm_objExceptionEventListener) {
		ApplicationContext.getInstance().addExceptionEventListeners(prm_objExceptionEventListener);
	}

}
