package com.scholastic.framework.automation.selenium.scriptplayer;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

public class ScriptPlayerFuncRunTestScriptAsXML extends ScriptPlayerFunc {

	private Document g_objCommandRoot = null;
	private Element g_objRoot = null;

	public void setCommandRoot(Document prm_xmlCommandRoot) {
		this.g_objCommandRoot = prm_xmlCommandRoot;
		this.g_objRoot = this.g_objCommandRoot.getRootElement();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void startFunction() {
		List<Node> v_lAllCommands;
		try {
			if (null == this.g_objRoot) {
			} else {
				v_lAllCommands = this.g_objRoot.selectNodes("//html/body/table/tbody/tr");
				for (Node v_objNode : v_lAllCommands) {
					this.processCommand(v_objNode);
				}
			}
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	private void processCommand(Node v_objNode) {
		try {
			
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

}
