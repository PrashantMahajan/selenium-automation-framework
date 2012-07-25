package com.scholastic.framework.automation.selenium.scriptplayer;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.scholastic.framework.automation.selenium.html5.AutomationTest;

public class ScriptPlayerFuncRunTestScriptAsXML extends ScriptPlayerFunc {

	private class FakeAutomationClass extends AutomationTest {
		@Override
		public void testStart() {
		}
		
	};
	
	private Document g_objCommandRoot = null;
	private Element g_objRoot = null;
	private FakeAutomationClass g_objAutomationTestCase = new FakeAutomationClass();

	public void setCommandRoot (Document prm_xmlCommandRoot) {
		this.g_objCommandRoot = prm_xmlCommandRoot;
		this.g_objRoot = this.g_objCommandRoot.getRootElement();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void startFunction () {
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

	@SuppressWarnings("unchecked")
	private void processCommand (Node v_objNode) {
		String v_sCommandName = null;
		String v_sUniquenesCriteria = null;
		String v_sCommandInput = null;
		List<Node> v_lNodes = null;
		try {
			v_lNodes = v_objNode.selectNodes("/td");
			if (v_lNodes.size() == 1) {
				v_sCommandName = v_lNodes.get(0).getStringValue();
			} else if (v_lNodes.size() == 2) {
				v_sCommandName = v_lNodes.get(0).getStringValue();
				v_sUniquenesCriteria = v_lNodes.get(1).getStringValue();
			} else if (v_lNodes.size() > 2) {
				v_sCommandName = v_lNodes.get(0).getStringValue();
				v_sUniquenesCriteria = v_lNodes.get(1).getStringValue();
				v_sCommandInput = v_lNodes.get(2).getStringValue();
			}
			//TODO : Use Reflection or If/Else to call the API. We prefer Reflections.
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

}
