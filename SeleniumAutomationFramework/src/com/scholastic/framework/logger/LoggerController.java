package com.scholastic.framework.logger;

import java.io.File;

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

	public File getCaptureScreen () {
		LoggerFuncCaptureScreen v_fn;
		v_fn = new LoggerFuncCaptureScreen();
		v_fn.startFunction();
		return v_fn.getScreen();
	}
	
	public void logException (Throwable prm_exException) {
		LoggerFuncException v_fn;
		v_fn = new LoggerFuncException();
		v_fn.setException(prm_exException);
		v_fn.startFunction();
	}
	
	public void sendEmail (Exception prm_objException) {
		LoggerFuncEmailing v_fn;
		v_fn =  new LoggerFuncEmailing();
		v_fn.setException(prm_objException);
		v_fn.setFileAttachment(this.getCaptureScreen());
		v_fn.startFunction();
	}

	public void sendEmail (File prm_objFile, Exception prm_objException) {
		LoggerFuncEmailing v_fn;
		v_fn =  new LoggerFuncEmailing();
		v_fn.setException(prm_objException);
		v_fn.setFileAttachment(prm_objFile);
		v_fn.startFunction();
	}
	
}
