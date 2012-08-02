package com.scholastic.framework.context;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

import com.scholastic.framework.exceptionhandling.ExceptionController;
import com.scholastic.framework.exceptionhandling.IExceptionEventListener;
/**
 * This is backbone of the application. There must be a single instance of this class per application thread. Thus you can define all the statics in this class.
 * All the properties/configuration etc. must go through this class.
 * @author prashant
 */
public class ApplicationContext {
	private static ApplicationContext g_objAppContext;
	/**
	 * Gets the instance of the ApplicationContext thread. Initializes the TestCases.properties file.
	 */
	public static ApplicationContext getInstance () {
		if (null == ApplicationContext.g_objAppContext) {
			ApplicationContext.g_objAppContext = new ApplicationContext();
		}
		return ApplicationContext.g_objAppContext;
	}
	private Properties g_objProperties = null;
	private WebDriver g_objWebDriver;
	private WebDriverBackedSelenium g_objSelenium;
	private Map<String, Workbook> g_hWorkbook = new HashMap<String, Workbook>();
	private List<IExceptionEventListener> g_lAllExceptionEventListeners = new LinkedList<IExceptionEventListener>();

	private ApplicationContext () {
		this.init();
	}

	/**
	 * Adds a new Event Handler to exception
	 */
	public void addExceptionEventListeners(IExceptionEventListener prm_objExceptionEventListener) {
		if (null != prm_objExceptionEventListener) {
			this.g_lAllExceptionEventListeners.add(prm_objExceptionEventListener);
		}
	}
	
	/**
	 * Registers a new workbook to the application thread.
	 * @param prm_sFileName : The name of the Excel workbook file.
	 * @param prm_objWorkbook : The actual instance of the workbook object.
	 */
	public void addWorkbook (String prm_sFileName, Workbook prm_objWorkbook) {
		this.g_hWorkbook.put(prm_sFileName, prm_objWorkbook);
	}
	
	/**
	 * Returns a list of all the registered Exception Event listeners
	 */
	public List<IExceptionEventListener> getExceptionEventListeners() {
		return this.g_lAllExceptionEventListeners;
	}
	
	/**
	 * Gets the value of the property as specified in the "TestCases.properties" file.
	 * @param : Name of the property mentioned in the properties file.
	 */
	public String getProperty (String prm_sPropertyName) {
		return (String) this.g_objProperties.get(prm_sPropertyName);
	}

	/**
	 * Returns the active instance of the Selenium Object
	 */
	public WebDriverBackedSelenium getSelenium () {
		return this.g_objSelenium;
	}
	/**
	 * Returns the active instance of the WebDriver object.
	 */
	public WebDriver getWebDriver () {
		return this.g_objWebDriver;
	}
	/**
	 * Gets the instance of the workbook based on the File name
	 * @param prm_sFileName : The name of the Excel file.
	 */
	public Workbook getWorkbook (String prm_sFileName) {
		Workbook v_Return = null;
		v_Return = this.g_hWorkbook.get(prm_sFileName);
		return v_Return;
	}
	
	/**
	 * Registers the active instance of the Selenium object instance.
	 * @param prm_objSelenium : Active Selenium object.
	 */
	public void setSelenium (WebDriverBackedSelenium prm_objSelenium) {
		this.g_objSelenium = prm_objSelenium;
	}

	/**
	 * Registers the active WebDriver instance of the current browser window.
	 * @param prm_objWebDriver : The current active Web Driver.
	 */
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
