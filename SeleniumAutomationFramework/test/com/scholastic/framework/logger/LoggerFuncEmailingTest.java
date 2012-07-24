package com.scholastic.framework.logger;

import java.io.File;

import org.junit.Test;

import junit.framework.TestCase;

public class LoggerFuncEmailingTest extends TestCase {

	@Test
	public void testStartFunction() {
		LoggerFuncEmailing v_tst;
		v_tst = new LoggerFuncEmailing();
		v_tst.setFileAttachment(new File("/Users/prashant/git/selenium-automation-framework/SeleniumAutomationFramework/src/TestCaseData.xlsx"));
		v_tst.setException(new NullPointerException());
		v_tst.startFunction();
	}

}
