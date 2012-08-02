package com.scholastic.framework.exceptionhandling;

import java.util.List;

import com.scholastic.framework.context.ApplicationContext;
import com.scholastic.framework.logger.LoggerController;

/**
 * This is the function call that handles all the exceptions of the application in a generic fashion.
 * @author prashant
 */
public class ExceptionFuncHandleException extends ExceptionFunc {

	private Exception g_exException;

	public void setException(Exception prm_exException) {
		this.g_exException = prm_exException;
	}

	@Override
	public void startFunction() {
		try {
			if (null != this.g_exException) {
				this.notifyEventListeners();
				this.printStackTraceAndSendEMail();
			}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void notifyEventListeners() {
		ApplicationContext v_objContext;
		List<IExceptionEventListener> v_lAllExceptionEventListeners;
		try {
			v_objContext = ApplicationContext.getInstance();
			v_lAllExceptionEventListeners = v_objContext.getExceptionEventListeners();
			for (IExceptionEventListener v_objListener : v_lAllExceptionEventListeners) {
				v_objListener.handleExceptionOccurredEvent(this.g_exException);
			}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void printStackTraceAndSendEMail() {
		this.g_exException.printStackTrace();
		LoggerController.getInstance().sendEmail(this.g_exException);
	}

}
