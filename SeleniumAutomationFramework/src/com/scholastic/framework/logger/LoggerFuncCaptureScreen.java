package com.scholastic.framework.logger;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.scholastic.framework.context.ApplicationContext;

class LoggerFuncCaptureScreen extends LoggerFunc {

	public File g_objScreenShot = null;
	public File getScreen () {
		return this.g_objScreenShot;
	}
	@Override
	public void startFunction() {
		WebDriver v_objDriver;
		TakesScreenshot v_objScreenshotDriver;
		File v_objScreenshot;
		try {
			v_objDriver = ApplicationContext.getInstance().getWebDriver();
			if(null == v_objDriver) {
			} else if (v_objDriver instanceof TakesScreenshot) {
				v_objScreenshotDriver = (TakesScreenshot) v_objDriver;
				this.g_objScreenShot = v_objScreenshot = v_objScreenshotDriver.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(v_objScreenshot, this.getFileName());
			}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}
	
	private File getFileName () {
		File v_Return = null;
		String v_sFileName = "";
		Properties v_objProperties;
		try {
			v_objProperties = new Properties();
			v_objProperties.load(ClassLoader.getSystemResourceAsStream("TestCases.properties"));
			v_sFileName = (String)v_objProperties.get("screenshotfolder");
			if (v_sFileName.endsWith("/") || v_sFileName.endsWith("\\")) {
				v_sFileName += new Date().toString().replace(":", "-") + ".png";
			} else {
				v_sFileName += "/" + new Date().toString().replace(":", "-") + ".png";
			}
			v_Return = new File(v_sFileName);
			if (v_Return.exists()) {
				v_Return.createNewFile();
			}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
		return v_Return;
	}

}
