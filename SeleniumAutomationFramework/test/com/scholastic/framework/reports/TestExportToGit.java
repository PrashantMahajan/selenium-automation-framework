package com.scholastic.framework.reports;

import junit.framework.TestCase;

public class TestExportToGit extends TestCase {

	public void testExport () {
		ExportController.getInstance().exportReportDataToGit(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
				"<run-sequence>" +
				"	<run>" +
				"		<server_name>http://integration15.education.scholastic.com/dashboard/</server_name>" +
				"		<name>Dashboard-AutomationTests</name>"+
				"		<status>Failed</status>"+
				"		<comments>Regression Tests for HTML5 Dashboard</comments>"+
				"		<execution_date>08/01/2012</execution_date>"+
				"		<step-sequence>"+
				"			<step id=\"1\">"+
				"				<status>Pass</status>"+
				"				<execution_date>08/01/2012</execution_date>"+
				"				<execution_time>20</execution_time>"+
				"				<step_description>Add A Student On Firefox</step_description>"+
				"				<Content>This tests the creation of a Student on Firefox</Content>"+
				"			</step>"+
				"			<step id=\"1\">"+
				"				<status>Fail</status>"+
				"				<execution_date>08/01/2012</execution_date>"+
				"				<execution_time>30</execution_time>"+
				"				<step_description>Add A Student On Safari</step_description>"+
				"				<Content>This tests the creation of a Student on IE</Content>"+
				"			</step>"+
				"		</step-sequence>"+
				"	</run>"+
				"</run-sequence>"
		);
	}
}
