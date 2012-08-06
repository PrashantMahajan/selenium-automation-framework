package com.testit.framework.logger;

import java.io.File;
import java.io.PrintStream;
import java.util.Date;

import com.testit.framework.context.ApplicationContext;

class LoggerFuncException extends LoggerFunc {

	private Throwable g_exException;

	public void setException(Throwable prm_exException) {
		this.g_exException = prm_exException;
	}

	@Override
	public void startFunction() {
		PrintStream v_printStream;
		try {
			v_printStream = new PrintStream(this.getLoggerFileName());
			this.g_exException.printStackTrace(v_printStream);
			v_printStream.close();
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}
	private String getLoggerFileName() {
		String v_sFileName = "logger.log";
		try {
			v_sFileName = ApplicationContext.getInstance().getProperty("screenshotfolder");
			if (v_sFileName.endsWith("/") || v_sFileName.endsWith("\\")) {
				v_sFileName += new Date().toString().replace(":", "-") + ".log";
			} else {
				v_sFileName += "/" + new Date().toString().replace(":", "-") + ".log";
			}
			if (new File(v_sFileName).exists()) {
			} else {
				new File(v_sFileName).createNewFile();
			}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
		return v_sFileName;
	}

}
