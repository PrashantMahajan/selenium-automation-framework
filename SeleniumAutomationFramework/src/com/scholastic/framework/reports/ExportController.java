package com.scholastic.framework.reports;

import com.scholastic.framework.Controller;

/**
 * This controller defines all the methods to export the data to the 3rd Party vendors/external servers.
 */
public class ExportController extends Controller {
	
	private static ExportController g_objInstance = null;
	/**
	 * Returns an instance of the ExportController.
	 */
	public static ExportController getInstance () {
		if (null == ExportController.g_objInstance) {
			ExportController.g_objInstance = new ExportController();
		}
		return ExportController.g_objInstance;
	}
	private ExportController () {
	}
	/**
	 * If called, this method looks at the current ApplicationContext, fetches the information of all the test cases that have failed. 
	 * Then generates a report, finally pushes the report to the specified QC server.
	 */
	public void exportDataToQC () {
		ExportFuncExportDataToQC v_fn;
		v_fn = new ExportFuncExportDataToQC();
		v_fn.startFunction();
	}
	/**
	 */
	public void exportFilesToGit () {

	}
}
