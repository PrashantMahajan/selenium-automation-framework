package com.testit.framework.persongenerator;

import com.testit.framework.exceptionhandling.ExceptionController;

abstract class PersonGeneratorFunc {

	private PersonGeneratorController g_objController;
	public PersonGeneratorController getController () {
		if (null == this.g_objController) {
			this.g_objController = PersonGeneratorController.getInstance();
		}
		return this.g_objController;
	}
	
	public void setController (PersonGeneratorController prm_objController) {
		this.g_objController = prm_objController;
	}
	
	protected void handleException (Exception prm_exException) {
		ExceptionController.getInstance().handleException(prm_exException);
	}
	abstract public void startFunction();

}
