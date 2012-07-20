package com.scholastic.framework.logger;

import com.scholastic.framework.exceptionhandling.ExceptionController;

public class LoggerFuncEmailing extends LoggerFunc {

	@Override
	public void startFunction() {
		try {
			this.composeMessage();
			this.sendEMail();
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}

	private void composeMessage() {
		try {
			
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}

	private void sendEMail() {
		try {
			
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}
}
