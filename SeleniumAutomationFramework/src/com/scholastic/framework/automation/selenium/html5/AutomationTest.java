package com.scholastic.framework.automation.selenium.html5;

import java.util.List;
import java.util.UUID;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.iphone.IPhoneDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.scholastic.framework.Controller;
import com.scholastic.framework.exceptionhandling.ExceptionController;

abstract public class AutomationTest extends TestCase {

	public static enum Browsers {FIREFOX, IE8, IE9, CHROME, SAFARI, IPHONE, ANDROID};
	public static final String MAX_WAIT_TIME_IN_MS = "60000";

	//This framework, right now runs a single instance of the Web-Browser
	private static WebDriver g_objWebDriver;

	public static WebDriver getDriver () {
		return AutomationTest.g_objWebDriver;
	}

	private Controller g_objController;

	public void command_cancel () {
		this.executeJavaScript("$(\"button:contains('Cancel')\").click()");
	}

	public void command_clickButton (String prm_sButtonID) {
		if (null != this.command_getControl(prm_sButtonID)) {
			this.command_getControl(prm_sButtonID).click();
		} else {
			this.command_clickLink(prm_sButtonID);
		}
	}
	public void command_clickCheckbox (String prm_sChekboxId) {
		this.command_getControl(prm_sChekboxId).click();
	}
	
	public void command_clickLink (String prm_sLinkText) {
		this.command_getControl(prm_sLinkText).click();
		this.command_waitInSeconds(1);
	}

	public void command_close () {
		this.command_cancel();
	}
	
	public void command_delete () {
		this.executeJavaScript("$(\"button:contains('Delete')\").click()");
	}
	
	public void command_enterMemo (String prm_sMemoId, String prm_sText) {
		this.command_getControl(prm_sMemoId).sendKeys(prm_sText);
	}
	
	public void command_enterPassword (String prm_sPasswordBoxId, String prm_sPassword) {
		this.command_getControl(prm_sPasswordBoxId).sendKeys(prm_sPassword);
	}
	
	public void command_enterText (String prm_sTextBoxId, String prm_sTextToEnter) {
		this.command_getControl(prm_sTextBoxId).sendKeys(prm_sTextToEnter);
	}
	
	public void command_excel_fillForm (String prm_sFileName, String prm_sSheetName) {
		_AutomationTestFillFormViaExcelsheet v_fn = null;
		try {
			v_fn = new _AutomationTestFillFormViaExcelsheet();
			v_fn.setFileName(prm_sFileName);
			v_fn.setSheetName(prm_sSheetName);
			v_fn.startFunction();
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	public String command_excel_getValue (String prm_sFileName, String prm_sSheetName, String prm_sFieldName) {
		return "";
	}

	public WebElement command_getControl (String prm_sId) {
		WebElement v_Return = null;
		try {
			try {
				v_Return = AutomationTest.g_objWebDriver.findElement(By.id(prm_sId));
			} catch (Exception v_exException) {
			}
			try {
				v_Return = (null != v_Return ? v_Return : AutomationTest.g_objWebDriver.findElement(By.name(prm_sId)));
			} catch (Exception v_exException) {
			}
			try {
				v_Return = (null != v_Return ? v_Return : AutomationTest.g_objWebDriver.findElement(By.linkText(prm_sId)));
			} catch (Exception v_exException) {
			}
			try {
				v_Return = (null != v_Return ? v_Return : AutomationTest.g_objWebDriver.findElement(By.partialLinkText(prm_sId)));
			} catch (Exception v_exException) {
			}
			try {
				v_Return = (null != v_Return ? v_Return : AutomationTest.g_objWebDriver.findElement(By.className(prm_sId)));
			} catch (Exception v_exException) {
			}
			
			try {
				v_Return = (null != v_Return ? v_Return : (WebElement) this.executeJavaScript("" +
						"\n	var v_Return;" +
						"\n	var v_sId;" +
						"\n alert(arguments[0]);" +
						"\n	v_sId = $(\"label:contains(\" + arguments[0] + \")\").attr('for');" +
						"\n	v_Return = $('#' + v_sId);" +
						"\n	if (null == v_Return) {" +
						"\n		v_Return = $(\"label:contains(\" + arguments[0] + \")\").parent().children()[1];" +
						"\n	}" +
						"\n	return v_Return;",
						"'" + prm_sId + "'"
					)
				);
			} catch (Exception v_exException) {
			}
		} catch (Exception v_exException) {
			this.handleException(v_exException);
			System.err.println("The control with the Id:" + prm_sId + " not found!!!");
		}
		return v_Return;
	}
	
	public void command_login (String prm_sUsername, String prm_sPassword) {
		this.command_openURL("http://integration15.education.scholastic.com/dashboard");
		this.command_enterText("username", prm_sUsername);
		this.command_enterPassword("password", prm_sPassword);
		this.command_submitFrom("login");
		this.command_waitInSeconds(2);
	}
	
	public void command_logout () {
		this.command_clickLink("Log Out");
		this.command_waitInSeconds(2);
	}

	public void command_navigatePage (String prm_sURL) {
		try {
			this.command_openURL(prm_sURL);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	public WebDriver command_openURL (String prm_sSite) {
		try {
			if (null == AutomationTest.g_objWebDriver) {
				this.command_setBrowser(Browsers.FIREFOX);
			}
			AutomationTest.g_objWebDriver.get(prm_sSite);
			this.command_waitInSeconds(5);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
		return AutomationTest.g_objWebDriver;
	}

	public String command_randomText (int prm_iLength) {
		String v_Return = "";
		if (prm_iLength == 0) {
			return v_Return;
		} else {
			v_Return = UUID.randomUUID().toString().replace("-", "");
			while (v_Return.length() != prm_iLength) {
				if (v_Return.length() > prm_iLength) {
					v_Return = v_Return.substring(0, prm_iLength);
				} else {
					v_Return += UUID.randomUUID().toString().replace("-", "");
				}
			}
		}
		return v_Return;
	}

	public void command_save () {
		this.executeJavaScript("$(\"button:contains('Save')\").click()");
	}

	public void command_selectComboBox (String prm_ComboBoxId, String prm_sOptionId) {
		WebElement v_objCombobox;
		List<WebElement> v_lAllOptions;
		try {
			v_objCombobox = this.command_getControl(prm_ComboBoxId);
			v_lAllOptions = v_objCombobox.findElements(By.tagName("option"));
			for (WebElement v_objElement : v_lAllOptions) {
				if (v_objElement.getText().equals(prm_sOptionId)) {
					v_objElement.click();
				} else if (v_objElement.getAttribute("value").equals(prm_sOptionId)) {
					v_objElement.click();
				}
			}
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	public void command_selectRadioButton (String prm_sControlName, String prm_sOptionId) {
		List<WebElement> v_lAllOptions;
		try {
			v_lAllOptions = AutomationTest.g_objWebDriver.findElements(By.name(prm_sControlName));
			for (WebElement v_objElement : v_lAllOptions) {
				if (v_objElement.getText().equals(prm_sOptionId)) {
					v_objElement.click();
				} else if (v_objElement.getAttribute("value").equals(prm_sOptionId)) {
					v_objElement.click();
				}
			}
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	public void command_selectTab (String prm_sTabName) {
		this.command_clickLink(prm_sTabName);
	}

	public void command_setBrowser (Browsers prm_iBrowser) {
		try {
			if (null != AutomationTest.g_objWebDriver) {
				AutomationTest.g_objWebDriver.close();
				AutomationTest.g_objWebDriver = null;
			}
			switch (prm_iBrowser) {
			case FIREFOX:
				AutomationTest.g_objWebDriver = new FirefoxDriver();
				break;
			case IE9:
			case IE8:
				AutomationTest.g_objWebDriver = new InternetExplorerDriver();
				break;
			case CHROME:
				AutomationTest.g_objWebDriver = new ChromeDriver();
				break;
			case SAFARI:
				AutomationTest.g_objWebDriver = new SafariDriver();
				break;
			case IPHONE:
				try {
					AutomationTest.g_objWebDriver = new IPhoneDriver();
				} catch (Exception v_exException) {
					this.handleException(v_exException);
				}
				break;
			case ANDROID:
				AutomationTest.g_objWebDriver = new AndroidDriver();
				break;
			}
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	public void command_submitFrom (String prm_sFormId) {
		this.command_getControl(prm_sFormId).submit();
	}
	public String command_uniqueText (int prm_iLength) {
		String v_Return = "";
		if (prm_iLength == 0) {
			return v_Return;
		} else {
			v_Return = UUID.randomUUID().toString().replace("-", "").replaceAll("[0-9]", "");
			while (v_Return.length() != prm_iLength) {
				if (v_Return.length() > prm_iLength) {
					v_Return = v_Return.substring(0, prm_iLength);
				} else {
					v_Return += UUID.randomUUID().toString().replace("-", "").replaceAll("[0-9]", "");
				}
			}
		}
		return v_Return;
	}

	public void command_waitForHalfASecond () {
		try {
			Thread.sleep(500);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	public void command_waitInSeconds (int prm_iSeconds) {
		try {
			Thread.sleep(1000 * prm_iSeconds);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	public Controller getController () {
		if (null == this.g_objController) {
			this.g_objController = AutomationController.getInstance();
		}
		return this.g_objController;
	}

	public void handleException (Exception prm_exException) {
		ExceptionController.getInstance().handleException(prm_exException);
	}

	public void setContoller (Controller prm_objController) {
		this.g_objController = prm_objController;
	}
	public Object executeJavaScript (String prm_sCommand) {
		return this.executeJavaScript(prm_sCommand);
	}
	public Object executeJavaScript (String prm_sCommand, Object ... prm_arrArgs) {
		Object v_Return = null;
		JavascriptExecutor v_objJS;
		try {
			v_objJS = (JavascriptExecutor) AutomationTest.g_objWebDriver;
			v_Return = v_objJS.executeScript(prm_sCommand, prm_arrArgs);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
		return v_Return;
	}

	abstract public void testStart();
}
