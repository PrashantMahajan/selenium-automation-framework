package com.scholastic.framework.automation.selenium.html5;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.scholastic.framework.excel.ExcelsheetController;

class _AutomationTestFillFormViaExcelsheet {

	private String g_sFileName = null;
	private String g_sSheetName = null;
	private AutomationTest g_objParent = null;

	public void setFileName (String prm_sExcelsheetName) {
		this.g_sFileName = prm_sExcelsheetName;
	}

	public void setParent (AutomationTest prm_objAutomationTest) {
		this.g_objParent = prm_objAutomationTest;
	}

	public void setSheetName (String prm_sWorkbookName) {
		this.g_sSheetName = prm_sWorkbookName;
	}

	public void startFunction () {
		Sheet v_objSheet;
		try {
			v_objSheet = ExcelsheetController.getInstance().getSheet(this.g_sFileName, this.g_sSheetName);
			this.fillEveryField(v_objSheet.rowIterator());
		} catch (Exception v_exException) {
			ExcelsheetController.getInstance().handleException(v_exException);
		}
	}

	private void fillEveryField (Iterator<Row> prm_itrRow) {
		String v_sControlName;
		String v_sControlValue;
		Row v_objRow;
		try {
			while (prm_itrRow.hasNext()) {
				try {
					v_objRow = prm_itrRow.next();
					if (null == v_objRow) {
						break;
					} else if (null == v_objRow.getCell(0)) {
						break;
					} else {
						v_sControlName = v_objRow.getCell(0).getStringCellValue();
						v_sControlValue = v_objRow.getCell(1).getStringCellValue();
						if (null == v_sControlName || "".equalsIgnoreCase(v_sControlName)) {
						} else {
							if (null == v_sControlValue || "".equals(v_sControlValue)) {
								v_sControlValue = "true";
							}
							this.g_objParent.command_controlSetValue(v_sControlName, v_sControlValue);
						}
					}
				} catch (Exception v_exException) {
					ExcelsheetController.getInstance().handleException(v_exException);
				}
			}
		} catch (Exception v_exException) {
			ExcelsheetController.getInstance().handleException(v_exException);
		}
	}

}
