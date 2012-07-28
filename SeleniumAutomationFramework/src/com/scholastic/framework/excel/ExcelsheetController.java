package com.scholastic.framework.excel;

import org.apache.poi.ss.usermodel.Sheet;

import com.scholastic.framework.Controller;
import com.scholastic.framework.exceptionhandling.ExceptionController;
/**
 * Performs all the required operations on the ExcelSheets.
 * For any operation that is required on the Excel workbook. Please register the method here.
 * @author prashant
 */
public class ExcelsheetController extends Controller {

	/**
	 * Get the instance of the {@link ExcelsheetController}
	 */
	public static ExcelsheetController getInstance () {
		return new ExcelsheetController();
	}

	private ExcelsheetController () {
	}

	/**
	 * Gets an instance of the Excelsheet. The Application registers all the workboks opened, thus the same 
	 * workbook+sheet would return the same object always.
	 * @param prm_sFileName : The name of the Excel workbook. This method used Classloader to find the file. 
	 * Thus please ensure that the file is kept in the classpath.
	 * @param prm_sSheet : The sheet in the workbook that is required to be fetched.
	 */
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
