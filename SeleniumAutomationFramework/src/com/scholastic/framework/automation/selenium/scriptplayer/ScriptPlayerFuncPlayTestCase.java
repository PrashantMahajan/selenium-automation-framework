package com.scholastic.framework.automation.selenium.scriptplayer;


public class ScriptPlayerFuncPlayTestCase extends ScriptPlayerFunc {

	private String g_sFileName = null;
	private String g_sFileContents = "";

	public void setFilename(String prm_sFileName) {
		this.g_sFileName = prm_sFileName;
	}

	@Override
	public void startFunction() {
		try {
			this.readFile();
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	private void readFile() {
		try {
			
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

}
