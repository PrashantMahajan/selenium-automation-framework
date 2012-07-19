package com.scholastic.framework.logger;

import com.scholastic.framework.Controller;

public class LoggerController extends Controller {

	public static LoggerController getInstance () {
		return new LoggerController();
	}
	
	public void captureScreen () {
		LoggerFuncCaptureScreen v_fn;
		v_fn = new LoggerFuncCaptureScreen();
		v_fn.startFunction();
	}
	
	public void logException (Throwable prm_exException) {
		LoggerFuncException v_fn;
		v_fn = new LoggerFuncException();
		v_fn.setException(prm_exException);
		v_fn.startFunction();
	}
	
}
