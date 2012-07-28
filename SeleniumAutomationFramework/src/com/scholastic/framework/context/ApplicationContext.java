package com.scholastic.framework.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

import com.scholastic.framework.exceptionhandling.ExceptionController;

public class ApplicationContext {
	private static ApplicationContext g_objAppContext;
	public static ApplicationContext getInstance () {
		if (null == ApplicationContext.g_objAppContext) {
			ApplicationContext.g_objAppContext = new ApplicationContext();
			
		}
		return ApplicationContext.g_objAppContext;
	}
	private Properties g_objProperties = null;
	private Map<String, Workbook> g_hWorkbook = new HashMap<String, Workbook>();
	private WebDriver g_objWebDriver;
	private WebDriverBackedSelenium g_objSelenium;

	private ApplicationContext () {
		this.init();
	}
	
	public void addWorkbook (String prm_sFileName, Workbook prm_objWorkbook) {
		this.g_hWorkbook.put(prm_sFileName, prm_objWorkbook);
	}

	public String getProperty (String prm_sPropertyName) {
		return (String) this.g_objProperties.get(prm_sPropertyName);
	}

	public WebDriverBackedSelenium getSelenium () {
		return this.g_objSelenium;
	}
	
	public WebDriver getWebDriver () {
		return this.g_objWebDriver;
	}
	
	public Workbook getWorkbook (String prm_sFileName) {
		Workbook v_Return = null;
		v_Return = this.g_hWorkbook.get(prm_sFileName);
		return v_Return;
	}
	
	public void setSelenium (WebDriverBackedSelenium prm_objSelenium) {
		this.g_objSelenium = prm_objSelenium;
	}
	public void setWebDriver (WebDriver prm_objWebDriver) {
		this.g_objWebDriver = prm_objWebDriver;
	}
	
	private void init() {
		this.readProperties();
	}

	private void readProperties() {
		try {
			this.g_objProperties = new Properties();
			this.g_objProperties.load(ClassLoader.getSystemResourceAsStream("TestCases.properties"));
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}
	
}
