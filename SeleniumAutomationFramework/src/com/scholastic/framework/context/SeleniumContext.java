package com.scholastic.framework.context;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

public class SeleniumContext {
	public static SeleniumContext g_objSelenium;
	private SeleniumContext () {
	}
	public static SeleniumContext getInstance () {
		if (null == SeleniumContext.g_objSelenium) {
			SeleniumContext.g_objSelenium = new SeleniumContext();
		}
		return SeleniumContext.g_objSelenium;
	}
	
	private Map<String, Workbook> g_hWorkbook = new HashMap<String, Workbook>();

	public Workbook getWorkbook (String prm_sFileName) {
		Workbook v_Return = null;
		v_Return = this.g_hWorkbook.get(prm_sFileName);
		return v_Return;
	}
	
	public void addWorkbook (String prm_sFileName, Workbook prm_objWorkbook) {
		this.g_hWorkbook.put(prm_sFileName, prm_objWorkbook);
	}
}
