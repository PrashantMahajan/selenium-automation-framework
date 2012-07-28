package com.scholastic.framework.logger;

import java.io.File;

import com.scholastic.framework.Controller;
/**
 * This class is a one stop-shop for all the logging related actions.
 * @author prashant
 */
public class LoggerController extends Controller {

	/**
	 * Returns a new instance of the LggerController
	 */
	public static LoggerController getInstance () {
		return new LoggerController();
	}
	
	/**
	 * Takes a screen shot of the current browser and save it in the folder as specified in the "TestCases.properties" file.
	 */
	public void captureScreen () {
		LoggerFuncCaptureScreen v_fn;
		v_fn = new LoggerFuncCaptureScreen();
		v_fn.startFunction();
	}

	/**
	 * Takes the Screenshot and returns the File
	 * @return Imae file containing the current screen-shot.
	 */
	public File getCaptureScreen () {
		LoggerFuncCaptureScreen v_fn;
		v_fn = new LoggerFuncCaptureScreen();
		v_fn.startFunction();
		return v_fn.getScreen();
	}
	
	/**
	 * Logs the exception in the logger file as specified in the "TestCases.properties"
	 * @param prm_exException : Exception that is required to be logged.
	 */
	public void logException (Throwable prm_exException) {
		LoggerFuncException v_fn;
		v_fn = new LoggerFuncException();
		v_fn.setException(prm_exException);
		v_fn.startFunction();
	}
	
	/**
	 * Sends an e-mail containing the exception stack trace.
	 * @param prm_objException : The exception whose stack-trace is required to be sent as part of e-mail.
	 */
	public void sendEmail (Exception prm_objException) {
		LoggerFuncEmailing v_fn;
		v_fn =  new LoggerFuncEmailing();
		v_fn.setException(prm_objException);
		v_fn.setFileAttachment(this.getCaptureScreen());
		v_fn.startFunction();
	}

	/**
	 * Sends an e-mail containing both the stack-trace and Screen-shot
	 * @param prm_objFile : The .png file containing the screenshot of the browser at the time of test-case failure.
	 * @param prm_objException : The exception whose stack-trace is required to be sent as part of e-mail.
	 */
	public void sendEmail (File prm_objFile, Exception prm_objException) {
		LoggerFuncEmailing v_fn;
		v_fn =  new LoggerFuncEmailing();
		v_fn.setException(prm_objException);
		v_fn.setFileAttachment(prm_objFile);
		v_fn.startFunction();
	}
	
}
