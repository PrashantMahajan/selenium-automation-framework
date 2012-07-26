package com.scholastic.framework.exceptionhandling;

import com.scholastic.framework.Controller;
import com.scholastic.framework.logger.LoggerController;

public class ExceptionController extends Controller {

	private static ExceptionController g_objInstance;
	public static ExceptionController getInstance () {
		if (null == ExceptionController.g_objInstance) {
			ExceptionController.g_objInstance = new ExceptionController();
		}
		return ExceptionController.g_objInstance;
	}
	
	@Override
	public void handleException (Exception prm_exException) {
		prm_exException.printStackTrace();
		LoggerController.getInstance().sendEmail(prm_exException);
	}

}
