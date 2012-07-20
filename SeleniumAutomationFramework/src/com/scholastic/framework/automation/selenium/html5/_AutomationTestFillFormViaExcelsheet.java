package com.scholastic.framework.automation.selenium.html5;

import com.scholastic.framework.excel.ExcelsheetController;

public class _AutomationTestFillFormViaExcelsheet {

	private String g_sFileName = null;
	private String g_sSheetName = null;

	public void setFileName(String prm_sExcelsheetName) {
		this.g_sFileName = prm_sExcelsheetName;
	}

	public void setSheetName(String prm_sWorkbookName) {
		this.g_sSheetName = prm_sWorkbookName;
	}

	public void startFunction() {
		try {
			//ExcelsheetController.getInstance().getSheet(, prm_sSheet);
		} catch (Exception v_exException) {
			ExcelsheetController.getInstance().handleException(v_exException);
		}
	}
}
