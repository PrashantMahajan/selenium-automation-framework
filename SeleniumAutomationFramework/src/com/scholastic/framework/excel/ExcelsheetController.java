package com.scholastic.framework.excel;


import org.apache.poi.ss.usermodel.Sheet;

import com.scholastic.framework.Controller;
import com.scholastic.framework.exceptionhandling.ExceptionController;

public class ExcelsheetController extends Controller {

	public static ExcelsheetController getInstance () {
		return new ExcelsheetController();
	}

	private ExcelsheetController () {
	}

	public Sheet getSheet (String prm_sFileName, String prm_sSheet) {
		Sheet v_Return = null;
		ExcelsheetRead v_fn;
		try {
			v_fn = new ExcelsheetRead();
			v_fn.setFileName(prm_sFileName);
			v_fn.setSheet(prm_sSheet);
			v_fn.startFunction();
			v_Return = v_fn.getResult();
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
		return v_Return;
	}
	
}
