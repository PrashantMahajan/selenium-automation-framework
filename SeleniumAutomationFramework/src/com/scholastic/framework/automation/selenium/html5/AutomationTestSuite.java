package com.scholastic.framework.automation.selenium.html5;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.scholastic.framework.context.ApplicationContext;
import com.scholastic.framework.exceptionhandling.ExceptionController;

/**
 * This class defines the base-class for all the test Suits that we wish to have.
 * @author prashant
 */
public abstract class AutomationTestSuite extends AutomationTest {

	private List<Browsers> g_lAllBrowsersToRunSuite = new LinkedList<AutomationTest.Browsers>();
	protected List<AutomationTest> g_lAllTestsToRun = new LinkedList<AutomationTest>();

	/**
	 * This method must be overrided in the every Test-Suite. The purpose of this method is to register the Tests to run.
	 * The typical overriden function wold look something like:
	 * <br>public void addTestToSuite () {
	 * <br>	this.addTest (new SomeTestCase1());
	 * <br>	this.addTest (new SomeTestCase2());
	 * <br>}
	 */
	abstract protected void addTestsToSuite ();

	/**
	 * This is the method that actually initiates the Test Suite.
	 */
	@Test
	@Override
	public void testStart() {
		try {
			this.addTestsToSuite();//Adds all the tests to the Linked List.
			this.getBrowsersFromProperties();
			
			if (this.g_lAllTestsToRun.size() > 0) {
				this.executeTests();
			}
			this.sendReportToQC();
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}
	/**
	 * Method to automatically create and send a report to QC
	 */
	private void sendReportToQC() {
		
	}

	/**
	 * Registers the Test to the Suite
	 */
	protected void addTest (AutomationTest prm_objTest) {
		if (null != prm_objTest) {
			this.g_lAllTestsToRun.add(prm_objTest);
		}
	}

	/**
	 * Method that actually executes all the registered Tests.
	 */
	private void executeTests() throws Exception {
		if (this.g_lAllBrowsersToRunSuite.size() == 0) {
			throw new Exception("No Browser specified in the TestCases.Properties. Please specify something like 'firefox=true' in the TestCases.Properties.");
		}
		for (AutomationTest v_objTestClass : this.g_lAllTestsToRun) {
			v_objTestClass.testStart();
			//v_objTestClass.command_closeBrowserWindow();
		}
	}
	/**
	 * Populates the List containing all the browsers
	 */
	private void getBrowsersFromProperties () {
		String v_sValue;
		ApplicationContext v_objContext;
		v_objContext = ApplicationContext.getInstance();

		for (Browsers v_objBrowsers : Browsers.values()) {
			v_sValue = v_objContext.getProperty(v_objBrowsers.name().toLowerCase());
			if (null == v_sValue) {
			} else if('t' == v_sValue.charAt(0) || 'T' == v_sValue.charAt(0)) {
				this.g_lAllBrowsersToRunSuite.add(v_objBrowsers);
			}
		}
	}

}
