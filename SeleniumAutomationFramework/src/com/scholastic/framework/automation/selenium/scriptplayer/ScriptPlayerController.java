package com.scholastic.framework.automation.selenium.scriptplayer;


import java.io.ByteArrayInputStream;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

import com.scholastic.framework.Controller;
import com.scholastic.framework.exceptionhandling.ExceptionController;

public class ScriptPlayerController extends Controller {

	private static ScriptPlayerController g_objController = null;

	public static ScriptPlayerController getInstance () {
		if (null == ScriptPlayerController.g_objController) {
			ScriptPlayerController.g_objController = new ScriptPlayerController();
		}
		return ScriptPlayerController.g_objController;
	}
	private ScriptPlayerController () {
	}

	public void runAllTestsInPackage (String prm_sDirectory) {
		try {
			
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}

	public void runTestScriptAsAFile (String prm_sFileName) {
		Document v_objCommandTree;
		SAXReader v_objReader;
		try {
			v_objReader = new SAXReader(DocumentFactory.getInstance());
			v_objCommandTree = v_objReader.read(ClassLoader.getSystemResourceAsStream(prm_sFileName));
			this.runTestScriptAsXML(v_objCommandTree);
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}
	public void runTestScriptAsAString (String prm_sCommandXML) {
		Document v_objCommandTree;
		SAXReader v_objReader;
		try {
			v_objReader = new SAXReader(DocumentFactory.getInstance());
			v_objCommandTree = v_objReader.read(new ByteArrayInputStream(prm_sCommandXML.getBytes()));
			this.runTestScriptAsXML(v_objCommandTree);
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}
	
	public void runTestScriptAsXML (Document prm_xmlCommandRoot) {
		ScriptPlayerFuncRunTestScriptAsXML v_fn;
		try {
			v_fn = new ScriptPlayerFuncRunTestScriptAsXML();
			v_fn.setCommandRoot(prm_xmlCommandRoot);
			v_fn.startFunction();
		} catch (Exception v_exException) {
			ExceptionController.getInstance().handleException(v_exException);
		}
	}

}
