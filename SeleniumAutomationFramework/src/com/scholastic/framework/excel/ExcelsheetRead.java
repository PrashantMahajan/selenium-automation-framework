package com.scholastic.framework.excel;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.scholastic.framework.exceptionhandling.ExceptionController;

public class ExcelsheetRead extends ExcelFunc {

	private Sheet g_Return;
	private String g_sSheetName;
	private String g_sFileName;

	public Sheet getResult () {
		return this.g_Return;
	}

	public void setFileName(String prm_sFileName) {
		this.g_sFileName = prm_sFileName;
	}

	public void setSheet(String prm_sSheet) {
		this.g_sSheetName = prm_sSheet;
	}

	@Override
	public void startFunction() {
		Workbook v_objWorkbook;
		try {
			try {
				v_objWorkbook = new XSSFWorkbook(ClassLoader.getSystemResourceAsStream(this.g_sFileName));
			} catch (Exception v_exException) {
				v_objWorkbook = new HSSFWorkbook(ClassLoader.getSystemResourceAsStream(this.g_sFileName));
			}
			this.g_Return = v_objWorkbook.getSheet(g_sSheetName);
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}

}
