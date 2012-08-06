package com.testit.framework.reports;

public abstract class ExportFunc {
	private ExportController g_objContoller = null;
	public ExportController getContoller() {
		if (null == this.g_objContoller) {
			this.g_objContoller = ExportController.getInstance();
		}
		return this.g_objContoller;
	}
	public void setContoller(ExportController g_objContoller) {
		this.g_objContoller = g_objContoller;
	}
	abstract public void startFunction();
}
