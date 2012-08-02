package com.scholastic.framework.reports;

import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.scholastic.framework.context.ApplicationContext;
/**
 * The function call exports the desired Report data to the QC server. The configuration of the QC server must be specified in the
 * TestCass.Properties file.
 * @author prashant
 */
public class ExportFuncExportDataToGitRepository extends ExportFunc {

	private String g_sReportData = null;
	private String g_sReportDirectory = null;
	private String g_sReportFileName = null;
	private ApplicationContext g_objContext = ApplicationContext.getInstance();

	public void setReportData(String prm_sReportData) {
		this.g_sReportData = prm_sReportData;
	}

	@Override
	public void startFunction() {
		try {
			this.setInitialVariables();
			this.createReport();
			this.commitReport();
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void commitReport () {
		try {
			System.out.println("Please nvigate to :'" + this.g_sReportDirectory + this.g_sReportFileName + "' to review the Test Case Report.");
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void createReport () {
		Calendar v_objCalendar;
		FileWriter v_objWriter;
		try {
			v_objCalendar = new GregorianCalendar();
			this.g_sReportFileName = "Dashboard-";
			this.g_sReportFileName += v_objCalendar.get(Calendar.YEAR) + "-" + v_objCalendar.get(Calendar.MONTH) + "-" + v_objCalendar.get(Calendar.DAY_OF_MONTH);
			this.g_sReportFileName += "_results.xml";
			v_objWriter = new FileWriter(this.g_sReportDirectory + this.g_sReportFileName);
			v_objWriter.write(this.g_sReportData);
			v_objWriter.close();
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void setInitialVariables () {
		this.g_sReportDirectory = this.g_objContext.getProperty("reportdirectory");
		if (this.g_sReportDirectory.endsWith("/")) {
		} else {
			this.g_sReportDirectory += "/";
		}
	}

}
