package com.scholastic.framework.excel;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.scholastic.framework.context.ApplicationContext;
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
			v_objWorkbook = ApplicationContext.getInstance().getWorkbook(this.g_sFileName);
			try {
				if (null == v_objWorkbook) {
					v_objWorkbook = new XSSFWorkbook(ClassLoader.getSystemResourceAsStream(this.g_sFileName));
					ApplicationContext.getInstance().addWorkbook(this.g_sFileName, v_objWorkbook);
				}
			} catch (Exception v_exException) {
				v_objWorkbook = new HSSFWorkbook(ClassLoader.getSystemResourceAsStream(this.g_sFileName));
				ApplicationContext.getInstance().addWorkbook(this.g_sFileName, v_objWorkbook);
			}
			this.g_Return = v_objWorkbook.getSheet(g_sSheetName);
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}

}
