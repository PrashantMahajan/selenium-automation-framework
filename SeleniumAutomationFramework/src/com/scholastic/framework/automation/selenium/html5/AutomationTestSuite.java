package com.scholastic.framework.automation.selenium.html5;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import com.scholastic.framework.context.ApplicationContext;
import com.scholastic.framework.exceptionhandling.ExceptionController;
import com.scholastic.framework.exceptionhandling.IExceptionEventListener;
import com.scholastic.framework.reports.ExportController;

/**
 * This class defines the base-class for all the test Suits that we wish to have.
 * @author prashant
 */
public abstract class AutomationTestSuite extends AutomationTest implements IExceptionEventListener {

	private String g_sTestSuiteName = "Autoamtion Test";
	private AutomationTest g_objTest = null;
	private Set<AutomationTest> g_lAllFailedTests = new LinkedHashSet<AutomationTest>();
	private Set<Browsers> g_lAllBrowsersToRunSuite = new LinkedHashSet<AutomationTest.Browsers>();
	protected Set<AutomationTest> g_lAllTestsToRun = new LinkedHashSet<AutomationTest>();

	/**
	 * @return Test Suite Name.
	 */
	public String getTestSuiteName() {
		return this.g_sTestSuiteName;
	}

	/**
	 * Listens to all the errors occurring in the test cases
	 */
	public void handleExceptionOccurredEvent (Exception prm_exException) {
		try {
			if (this.g_objTest != null) {
				this.g_lAllFailedTests.add(this.g_objTest);
			}
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}
	public void setTestSuiteName(String g_sTestSuiteName) {
		this.g_sTestSuiteName = g_sTestSuiteName;
	}
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
				this.registerSuiteToExceptionContoller();
				this.executeTests();
			}
			this.sendReportToQC();
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}

	private void registerSuiteToExceptionContoller() {
		ExceptionController.getInstance().addExceptionEventListener(this);
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
	 * This method must be overrided in the every Test-Suite. The purpose of this method is to register the Tests to run.
	 * The typical overriden function wold look something like:
	 * <br>public void addTestToSuite () {
	 * <br>	this.addTest (new SomeTestCase1());
	 * <br>	this.addTest (new SomeTestCase2());
	 * <br>}
	 */
	abstract protected void addTestsToSuite ();
	/**
	 * Method that actually executes all the registered Tests.
	 */
	private void executeTests() throws Exception {
		if (this.g_lAllBrowsersToRunSuite.size() == 0) {
			throw new Exception("No Browser specified in the TestCases.Properties. Please specify something like 'firefox=true' in the TestCases.Properties.");
		}
		for (AutomationTest v_objTestClass : this.g_lAllTestsToRun) {
			this.g_objTest = v_objTestClass;
			v_objTestClass.testStart();
			v_objTestClass.command_closeBrowserWindow();
		}
		this.g_objTest = null;
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

	/**
	 * Method to automatically create and send a report to QC
	 */
	private void sendReportToQC() {
		try {
			ExportController.getInstance().exportDataToQC(this.getName(), this.g_lAllTestsToRun, this.g_lAllFailedTests);
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}

}
