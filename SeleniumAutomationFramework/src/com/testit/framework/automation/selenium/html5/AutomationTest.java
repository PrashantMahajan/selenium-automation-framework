package com.testit.framework.automation.selenium.html5;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import junit.framework.TestCase;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.iphone.IPhoneDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.testit.framework.Controller;
import com.testit.framework.context.ApplicationContext;
import com.testit.framework.excel.ExcelsheetController;
import com.testit.framework.exceptionhandling.ExceptionController;

/**
 * This is the base class for all the Selenium test cases that we wish to write.
 * This framework recommends using this class but doesn't force you to use it.
 * You can and may write your own test case class for your custom needs.
 * <br />
 * <h1>NOTE:</h1> This class, right now runs a single instance of the Web-Browser only.
 * If you wish to work with multiple instance of the browsers, you can extend this
 * class or can make a new one.
 * @author prashant
 */
abstract public class AutomationTest extends TestCase {

	/** 
	 * Right now we support the following 7 browsers
	 * <ol>
	 * 	<li>FIREFOX</li>
	 * 	<li>IE8</li>
	 * 	<li>IE9</li>
	 * 	<li>CHROME</li>
	 * 	<li>SAFARI</li>
	 * 	<li>IPHONE</li>
	 * 	<li>ANDROID</li>
	 * </ol>
	 */
	public static enum Browsers {FIREFOX, IE8, IE9, CHROME, SAFARI, IPHONE, ANDROID};

	private long g_iExecutionTime = 0;
	private String g_sURL = "";
	private String g_sTestCaseName = this.getClass().getSimpleName();
	private String g_sTestCaseDescription = "";
	private ApplicationContext g_objContext = ApplicationContext.getInstance();
	private Controller g_objController;
	private WebDriver g_objWebDriver;
	protected WebDriverBackedSelenium selenium;

	public AutomationTest () {
		try {
			this.g_sURL = ApplicationContext.getInstance().getProperty("url");
			if (null == this.g_sURL || "".equals(this.g_sURL)) {
				System.err.println("You may wish to set the URL in the TestCases.properties file.");
			}
			this.command_setTestCaseDescription(this.command_setTestCaseName(this.getClass().getSimpleName()));
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	/**
	 * Clicks the Cancel Button on the current Popup window.
	 */
	public void command_cancel () {
		this.executeJavaScript("$(\"button:contains('Cancel')\").click()");
	}

	/**
	 * Clicks the button. In-case of multiple buttons on the screen having the same id/name/label, this method
	 * would click the first one.
	 * @param prm_sButtonID : The valid information that you can pass in the parameter is:
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 * 
	 */
	public void command_clickButton (String prm_sButtonID) {
		WebElement v_ctlButton;

		v_ctlButton = (WebElement)this.executeJavaScript("" +
				"\n var v_ctlButton = null;" +
				"\n var v_arrButtons = null;" +
				"\n v_arrButtons = $(\"button:contains('" + prm_sButtonID + "')\");" +
				"\n if (v_arrButtons.length > 0) {" +
				"\n 	v_ctlButton = v_arrButtons[0];" +
				"\n }" +
				"\n return v_ctlButton;"
		);
		if (null == v_ctlButton) {
			v_ctlButton = this.command_getControl(prm_sButtonID);
		}
		v_ctlButton.click();
	}
	/**
	 * Clicks the Checkbox. In-case of multiple checkboxes on the screen having the same id/name/label, this method
	 * would click the first one.
	 * @param prm_sChekboxId : The valid information that you can pass in the parameter is:
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 */
	public void command_clickCheckbox (String prm_sChekboxId) {
		this.command_getControl(prm_sChekboxId).click();
	}
	
	/**
	 * Clicks the Link. In-case of multiple links on the screen having the same id/name/label, this method
	 * would click the first one. This method auto-waits for 1 second after clicking the link. 
	 * @param prm_sLinkText : The valid information that you can pass in the parameter is:
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 * <br>4. Link Text
	 * <br>5. Link Partial Text
	 */
	public void command_clickLink (String prm_sLinkText) {
		this.command_getControl(prm_sLinkText).click();
		this.command_waitInSeconds(2);
	}

	/**
	 * This method would click the close icon on the currently opened popup.
	 */
	public void command_close () {
		this.command_clickLink("close");
	}
	
	/**
	 * This command is going to close the current browser window.
	 */
	public void command_closeBrowserWindow () {
		this.g_objWebDriver.close();
	}

	/**
	 * Fetches the value if the control. In-case of multiple controls on the screen having the same id/name/label, this method
	 * would return the value of the first one.
	 * @param prm_sControlId : The valid information that you can pass in the parameter is:
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 */
	public String command_controlGetValue (String prm_sControlId) {
		String v_Return = null;
		v_Return = this.command_getControl(prm_sControlId).getText();
		return v_Return;
	}

	/**
	 * Sets the value if the control. In-case of multiple controls on the screen having the same id/name/label, this method
	 * would set the value in the first one.
	 * @param prm_sControlId : The valid information that you can pass in the first parameter is:
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 * 
	 * @param prm_sControlValue : The Control value, that is required to be set.
	 * 
	 */
	public void command_controlSetValue (String prm_sControlId, String prm_sControlValue) {
		String v_sTagName;
		String v_sType;
		WebElement v_objControl;
		v_objControl = this.command_getControl(prm_sControlId);
		if (null != v_objControl) {
			v_sTagName = v_objControl.getTagName().toLowerCase();
			switch (v_sTagName.charAt(0)) {
			case 'i': //Input
				if (v_sTagName.equals("input")) {
					v_sType = v_objControl.getAttribute("type").toLowerCase();
					switch (v_sType.charAt(0)) {
					case 'b'://Button
						break;
					case 'c'://Checkbox
						if (prm_sControlValue.charAt(0) == 'y' || prm_sControlValue.charAt(0) == 'Y' || prm_sControlValue.charAt(0) == 'T' || prm_sControlValue.charAt(0) == 't') {
							v_objControl.clear();
							v_objControl.click();
						}
						break;
					case 'f'://File
						break;
					case 'h'://Hidden
						v_objControl.clear();
						v_objControl.sendKeys(prm_sControlValue);
						break;
					case 'i'://Image
						break;
					case 'p'://Password
						v_objControl.clear();
						v_objControl.sendKeys(prm_sControlValue);
						break;
					case 'r'://radio/reset
						if (prm_sControlValue.charAt(0) == 'y' || prm_sControlValue.charAt(0) == 'Y' || prm_sControlValue.charAt(0) == 'T' || prm_sControlValue.charAt(0) == 't') {
							v_objControl.clear();
							v_objControl.click();
						}
						break;
					case 's'://Submit
						break;
					case 't'://Text
					default:
						this.command_enterText(prm_sControlId, prm_sControlValue);
						break;
					}
				}
				break;
			case 's': //select
				if (v_sTagName.equals("select")) {
					this.command_selectComboBox(prm_sControlId, prm_sControlValue);
				}
				break;
			case 'b': //Button
				if (v_sTagName.equals("button")) {
				}
				break;
			default:
				break;
			}
		}
	}
	/**
	 * Clicks the Delete button on the currently opened popup.
	 */
	public void command_delete () {
		this.executeJavaScript("$(\"button:contains('Delete')\").click()");
	}
	
	/**
	 * Sets the value if the TextArea/Memo control. In-case of multiple controls on the screen having the same id/name/label, this method
	 * would set the value in the first one.
	 * @param prm_sMemoId : The valid information that you can pass in the first parameter is:
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 * 
	 * @param prm_sText : The Control value, that is required to be set.
	 * 
	 */
	public void command_enterMemo (String prm_sMemoId, String prm_sText) {
		this.command_getControl(prm_sMemoId).clear();
		this.command_getControl(prm_sMemoId).sendKeys(prm_sText);
	}

	/**
	 * Sets the value in the password control.In-case of multiple controls on the screen having the same id/name/label, this method
	 * would set the value in the first one.
	 * @param prm_sPasswordBoxId : The valid information that you can pass in the parameter is:
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 * 
	 * @param prm_sPassword : The value that you wish to set.
	 */
	public void command_enterPassword (String prm_sPasswordBoxId, String prm_sPassword) {
		this.command_getControl(prm_sPasswordBoxId).clear();
		this.command_getControl(prm_sPasswordBoxId).sendKeys(prm_sPassword);
	}
	/**
	 * Sets the value in the Textbox control. In-case of multiple controls on the screen having the same id/name/label, this method
	 * would set the value in the first one.
	 * @param prm_sTextBoxId : The valid information that you can pass in the parameter is
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 * 
	 * @param prm_sTextToEnter : The value that you wish to set.
	 */
	public void command_enterText (String prm_sTextBoxId, String prm_sTextToEnter) {
		this.command_getControl(prm_sTextBoxId).clear();
		this.command_getControl(prm_sTextBoxId).sendKeys(prm_sTextToEnter);
	}

	/**
	 * This method would automatically read the Excel sheet and would auto-populate the Current form with the 
	 * values specified in the Excel sheet.
	 * 
	 * @param prm_sFileName : The Name of the Excel File. This method in-turn uses classloader to find the File.
	 * Thus please ensure that the file is present in the class path.
	 * @param prm_sSheetName : The Excel sheet that you wish to read from with-in the workbook.
	 * 
	 */
	public void command_excel_fillForm (String prm_sFileName, String prm_sSheetName) {
		_AutomationTestFillFormViaExcelsheet v_fn = null;
		try {
			v_fn = new _AutomationTestFillFormViaExcelsheet();
			v_fn.setFileName(prm_sFileName);
			v_fn.setSheetName(prm_sSheetName);
			v_fn.setParent(this);
			v_fn.startFunction();
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	/**
	 * This method would fetch the Control value from the excel-sheet. This method assumes that the first column is the 
	 * Control name and the second column is the Control value.
	 * @param prm_sFileName : The Name of the Excel File. This method in-turn uses classloader to find the File.
	 * Thus please ensure that the file is present in the class path
	 * @param prm_sSheetName : The Excel sheet that you wish to read from with-in the workbook.
	 * @param prm_sControlValue : The Control name specified in the excelsheet.
	 */
	public String command_excel_getCellValue (String prm_sFileName, String prm_sSheetName, String prm_sControlValue) {
		String v_Return = "";
		Row v_objRow;
		Sheet v_objSheet;
		Iterator<Row> v_itr;
		try {
			v_objSheet = this.command_excel_getSheet(prm_sFileName, prm_sSheetName);
			v_itr = v_objSheet.rowIterator();
			while (v_itr.hasNext()) {
				v_objRow = v_itr.next();
				if (v_objRow.getCell(0).getStringCellValue().equals(prm_sControlValue)) {
					v_Return = v_objRow.getCell(1).getStringCellValue();
				}
			}
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
		return v_Return;
	}
	/**
	 * This method returns the actual instance of the Excel Workbooks' sheet. The sheet then can be used to set/get any value.
	 * @param prm_sFileName : The name of the Excel file. This file must be present in the classpath.
	 * @param prm_sSheetName : The name of the sheet in the workbook that you wish to read/write.
	 */
	public Sheet command_excel_getSheet (String prm_sFileName, String prm_sSheetName) {
		Sheet v_Return = null;
		try {
			v_Return = ExcelsheetController.getInstance().getSheet(prm_sFileName, prm_sSheetName);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
		return v_Return;
	}
	
	/**
	 * Returns the name of the browser
	 */
	public String command_getBrowserName () {
		String v_Return = null;
		try {
			v_Return = this.selenium.getEval("navigator.userAgent");
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
		return v_Return;
	}

	/**
	 * This method returns the actual Control object.
	 * @param prm_sId : The valid information that you can pass in the parameter is:
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 */
	public WebElement command_getControl (String prm_sId) {
		WebElement v_Return = null;
		try {
			try {
				v_Return = this.g_objWebDriver.findElement(By.id(prm_sId));
			} catch (Exception v_exException) {
			}
			try {
				v_Return = (null != v_Return ? v_Return : this.g_objWebDriver.findElement(By.name(prm_sId)));
			} catch (Exception v_exException) {
			}
			try {
				v_Return = (null != v_Return ? v_Return : this.g_objWebDriver.findElement(By.linkText(prm_sId)));
			} catch (Exception v_exException) {
			}
			
			try {
				v_Return = (null != v_Return ? v_Return : (WebElement) this.executeJavaScript("" +
						"\n		var v_Return;" +
						"\n		var v_sId;" +
						"\n		v_sId = $(\"label:contains('" + prm_sId + "')\").attr('for');" +
						"\n		v_Return = $('#' + v_sId);" +
						"\n		if (null == v_Return) {" +
						"\n			v_Return = $(\"label:contains('" + prm_sId + "')\").parent().children()[1];" +
						"\n		}" +
						"\n		if (null != v_Return && v_Return.length > 0) {" +
						"\n			v_Return = v_Return[0];" +
						"\n		} else if (null == v_Return || 0 == v_Return.length) {" +
						"\n			v_Return = $($(\"div:contains('" + prm_sId + "')\").last()).children().last();" +
						"\n			if (null != v_Return && v_Return.length > 0) {" +
						"\n				v_Return = v_Return[0];" +
						"\n			} else if (null == v_Return || 0 == v_Return.length) {" +
						"\n				v_Return = null;" +
						"\n			}" +
						"\n		}" +
						"\n		return v_Return;"
					)
				);
			} catch (Exception v_exException) {
			}
			try {
				v_Return = (null != v_Return ? v_Return : this.g_objWebDriver.findElement(By.partialLinkText(prm_sId)));
			} catch (Exception v_exException) {
			}
			try {
				v_Return = (null != v_Return ? v_Return : this.g_objWebDriver.findElement(By.className(prm_sId)));
			} catch (Exception v_exException) {
			}
		} catch (Exception v_exException) {
			this.handleException(v_exException);
			System.err.println("The control with the Id:" + prm_sId + " not found!!!");
		}
		return v_Return;
	}
	/**
	 * Returns the Description about the Test Case.
	 */
	public String command_getTestCaseDescription() {
		return this.g_sTestCaseDescription;
	}
	
	/**
	 * Returns the Name of the Test Case
	 */
	public String command_getTestCaseName() {
		return g_sTestCaseName;
	}

	
	/**
	 * Returns the active URL of the browser-window being tested.
	 */
	public String command_getURL () {
		return this.g_sURL;
	}

	/**
	 * Logs the user in the application. This method auto waits for 2 seconds after executing the login command.
	 * @param prm_sUsername: Username
	 * @param prm_sPassword: Password
	 */
	public void command_login (String prm_sUsername, String prm_sPassword) {
		this.command_openURL(this.g_sURL);
		this.command_enterText("username", prm_sUsername);
		this.command_enterPassword("password", prm_sPassword);
		this.command_submitFrom("login");
		this.command_waitInSeconds(2);
	}

	/**
	 * Logs-out the user from the application. This method auto waits for 2 seconds after executing the logout command.
	 */
	public void command_logout () {
		this.command_clickLink("Log Out");
		this.command_waitInSeconds(2);
	}

	/**
	 * Navigates the current page to the specified URL.
	 * @param prm_sURL : The URL of the website in the format : http://www.scholastic.com/
	 * @see command_openURL
	 */
	public void command_navigatePage (String prm_sURL) {
		try {
			this.command_openURL(prm_sURL);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}

	/**
	 * Opens the specified URL in the pre-opened window. If the window is not open, it would one.
	 * This command would auto-wait for 5 seconds after opening any new URL.
	 * @param prm_sSite : The URL of the website in the format : http://www.scholastic.com/
	 */
	public WebDriver command_openURL (String prm_sSite) {
		try {
			if (null == this.g_objWebDriver) {
				this.command_setBrowser(Browsers.FIREFOX);
			}
			this.g_objWebDriver.get(prm_sSite);
			this.command_waitInSeconds(5);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
		return this.g_objWebDriver;
	}

	/**
	 * Auto generates random text with the length specified by the user.
	 * @param prm_iLength : Desired length of the Text
	 */
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

	/**
	 * Clicks the Save button on the currently opened Popup window.
	 */
	public void command_save () {
		this.executeJavaScript("$(\"button:contains('Save')\").click()");
	}
	/**
	 * Sets the value in the Combobox control. In-case of multiple controls on the screen having the same id/name/label, this method
	 * would set the value in the first one.
	 * @param prm_ComboBoxId : The valid information that you can pass in the parameter is
	 * <br>1. ID
	 * <br>2. Name
	 * <br>3. Label
	 * 
	 * @param prm_sOptionId : The value that you wish to set. The value can be both, actual value or the display value.
	 */
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
	/**
	 * Sets the value in the Radio control. In-case of multiple controls on the screen having the same id/name/label, this method
	 * would set the value in the first one.
	 * @param prm_sControlName : The valid information that you can pass in the parameter is
	 * <br>1. Name
	 * 
	 * @param prm_sOptionId : The value that you wish to set. The value can be both, actual value or the display value.
	 */
	public void command_selectRadioButton (String prm_sControlName, String prm_sOptionId) {
		List<WebElement> v_lAllOptions;
		try {
			v_lAllOptions = this.g_objWebDriver.findElements(By.name(prm_sControlName));
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
	/**
	 * Auto-Finds and clicks the tab on the popup screen.
	 * @param prm_sTabName : The Text on the tab.
	 */
	public void command_selectTab (String prm_sTabName) {
		this.command_clickLink(prm_sTabName);
	}

	/**
	 * Sets the browser window type. If this command is executed while a test case is running,
	 * the the current browser would be closed and a new window would be opened.
	 * @param prm_iBrowser : Any of the values from the enum {@link Browsers}
	 */
	public void command_setBrowser (Browsers prm_iBrowser) {
		try {
			if (null != this.g_objWebDriver) {
				this.g_objWebDriver.close();
				this.g_objWebDriver = null;
			}
			switch (prm_iBrowser) {
			case FIREFOX:
				this.g_objWebDriver = new FirefoxDriver();
				break;
			case IE9:
			case IE8:
				this.g_objWebDriver = new InternetExplorerDriver();
				break;
			case CHROME:
				this.g_objWebDriver = new ChromeDriver();
				break;
			case SAFARI:
				this.g_objWebDriver = new SafariDriver();
				break;
			case IPHONE:
				try {
					this.g_objWebDriver = new IPhoneDriver();
				} catch (Exception v_exException) {
					this.handleException(v_exException);
				}
				break;
			case ANDROID:
				this.g_objWebDriver = new AndroidDriver();
				break;
			}
			this.selenium = new WebDriverBackedSelenium(this.g_objWebDriver, ".");
			this.g_objContext.setWebDriver(this.g_objWebDriver);
			this.g_objContext.setSelenium(this.selenium);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}
	/**
	 * Sets the Description of the Test Case.
	 */
	public void command_setTestCaseDescription(String g_sTestCaseDescription) {
		this.g_sTestCaseDescription = g_sTestCaseDescription;
	}

	/**
	 * Sets the Name of the Test Case
	 */
	public String command_setTestCaseName(String g_sTestCaseName) {
		return this.g_sTestCaseName;
	}

	/**
	 * Sets the usl that is required to be tested.
	 * @param prm_sURL : The URL to be tested in the following format : http://www.scholastic.com
	 */
	public void command_setURL (String prm_sURL) {
		this.g_sURL = prm_sURL;
	}

	/**
	 * Submits the currently opened popup.
	 * @param prm_sFormId : The id of the Form that is required to be submitted.
	 */
	public void command_submitFrom (String prm_sFormId) {
		this.command_getControl(prm_sFormId).submit();
	}

	/**
	 * Auto-Generates a random text having the length specified by the user.
	 * @param prm_iLength : The total length of the Text.
	 */
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

	/**
	 * Halts the Script-execution for Half-a-second
	 */
	public void command_waitForHalfASecond () {
		try {
			Thread.sleep(500);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}
	/**
	 * Halts the Script-execution for the number of seconds specified.
	 * @param prm_iSeconds : The number of seconds that you wish the application to wait.
	 */
	public void command_waitInSeconds (int prm_iSeconds) {
		try {
			Thread.sleep(1000 * prm_iSeconds);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
	}
	
	/**
	 * Executes the JavaScript and returns the result
	 * @param prm_sCommand : JavaScript code as a string.
	 * @return : Any Object that is returned by the browser Javascript.
	 */
	public Object executeJavaScript (String prm_sCommand) {
		Object v_Return = null;
		JavascriptExecutor v_objJS;
		try {
			v_objJS = (JavascriptExecutor) this.g_objWebDriver;
			v_Return = v_objJS.executeScript(prm_sCommand);
		} catch (Exception v_exException) {
			this.handleException(v_exException);
		}
		return v_Return;
	}
	/**
	 * The Controller where this Method is being used from. If none is specified then it would return a new instance of {@link AutomationController}
	 */
	public Controller getController () {
		if (null == this.g_objController) {
			this.g_objController = AutomationController.getInstance();
		}
		return this.g_objController;
	}

	/**
	 * Returns the total Execution time.
	 */
	public long getExecutionTime () {
		return this.g_iExecutionTime;
	}

	/**
	 * Handles the exceptions thrown by the test cases.
	 * @param prm_exException : Exception caught by the test-case.
	 */
	public void handleException (Exception prm_exException) {
		ExceptionController.getInstance().handleException(prm_exException);
		fail();
	}

	/**
	 * Set the controller where this function is intended to be registered.
	 */
	public void setContoller (Controller prm_objController) {
		this.g_objController = prm_objController;
	}

	/**
	 * Sets the total execution time of the test.
	 * @param prm_iSeconds The execution time in seconds.
	 */
	public void setExecutionTime (long prm_iSeconds) {
		this.g_iExecutionTime = prm_iSeconds;
	}

	/**
	 * The method that must be implemented by all the inheriting classes to run the test-case
	 */
	abstract public void testStart();
}

