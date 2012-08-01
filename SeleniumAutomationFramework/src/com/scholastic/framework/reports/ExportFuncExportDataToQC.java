package com.scholastic.framework.reports;

import java.util.Iterator;

import com.scholastic.framework.context.ApplicationContext;

public class ExportFuncExportDataToQC extends ExportFunc {

	private ApplicationContext g_objContext = ApplicationContext.getInstance();
	private StringBuilder g_sbReport = new StringBuilder();

	@Override
	public void startFunction() {
		try {
			this.generateReportFileContents();
			this.exportReportFile();
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void exportReportFile() {
		try {
			
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void generateReportFileContents() {
		try {
			this.createSteps();
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void createSteps() {
		int v_iI = 0;
		Iterator<String> v_itr = null;
		try {
			//v_itr = this.g_objContext.getFailedTestCase();
			//while (v_itr.hasNext()) {
				this.createStep(v_itr.next(), ++v_iI);
			//}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}
	
	private void createStep (String prm_sFailureData, int prm_iStepIndex) {
		this.g_sbReport.append("<step id=\"" + prm_iStepIndex + "\">");
		this.g_sbReport.append("");
	}

}
