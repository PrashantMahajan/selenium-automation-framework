package com.testit.framework.logger;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.testit.framework.automation.selenium.html5.AutomationTest;
import com.testit.framework.context.ApplicationContext;
/**
 * Sends an e-mail containing the exception stack trace.
 * @param prm_objException : <br> Method : this.setException(Exception prm_exException) <br>The exception whose stack-trace is required to be sent as part of e-mail. 
 * @author prashant
 */
class LoggerFuncEmailing extends LoggerFunc {

	private String g_sFrom = "";
	private String g_sTo = "";
	private String g_sHost = "";
	private String g_sPassword = "";
	private Exception g_objException = null;
	private File g_objFile = null;
	private MimeMessage g_objEmail = null;
	private Multipart g_objMultiPart = null;
	private Session g_objEmailSession = null;

	public void setException (Exception prm_exException) {
		this.g_objException = prm_exException;
	}

	/**
	 * Sets the Image file to be included with the E-mail
	 */
	public void setFileAttachment (File prm_objFile) {
		this.g_objFile = prm_objFile;
	}

	@Override
	public void startFunction() {
		try {
			this.composeMessage();
			this.sendEMail();
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void composeMessage() {
		try {
			this.setBasicVariables();
			this.initSession();
			this.initMessage();
			this.setSubject();
			this.setMessage();
			this.setAttachment();
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private String extractMessageFromException () {
		String v_Return = "";
		int v_iI = 0;
		int v_iAutomationClassLine = -1;
		PrintWriter v_objPrintStream;
		StackTraceElement v_objErrorLine = null;
		StringWriter v_objStringWriter = null;
		StackTraceElement[] v_arrStackTraceElement;
		try {
			v_arrStackTraceElement = this.g_objException.getStackTrace();
			for (v_iI = 0; v_iI < v_arrStackTraceElement.length; v_iI++) {
				v_objErrorLine = v_arrStackTraceElement[v_iI];
				if (v_objErrorLine.getClassName().equals(AutomationTest.class.getName()) ) {
					v_iAutomationClassLine = v_iI;
				} else if (v_iAutomationClassLine > -1) {
					v_Return = "The test-case : " + v_objErrorLine.getClassName() + " failed at line number : " + v_objErrorLine.getLineNumber();
					break;
				}
			}

			v_objStringWriter = new StringWriter();
			v_objPrintStream = new PrintWriter(v_objStringWriter);
			this.g_objException.printStackTrace(v_objPrintStream);
			v_Return += "\nStack Trace: \n" + v_objStringWriter.toString();
			
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
		return v_Return;
	}

	private void initMessage() {
		try {
			this.g_objEmail = new MimeMessage(this.g_objEmailSession);
			this.g_objEmail.setFrom(new InternetAddress(this.g_sFrom));
			this.g_objEmail.addRecipient(Message.RecipientType.TO, new InternetAddress(this.g_sTo));
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void initSession() {
		Properties v_objEmailProperties;
		try {
			v_objEmailProperties = System.getProperties();
			v_objEmailProperties.put("mail.smtp.auth", "true");
			v_objEmailProperties.put("mail.smtp.host", this.g_sHost);
			v_objEmailProperties.put("mail.smtp.user", this.g_sFrom);
			v_objEmailProperties.put("mail.smtp.port", "25");
			v_objEmailProperties.put("mail.smtp.password", this.g_sPassword);
			this.g_objEmailSession = Session.getInstance(v_objEmailProperties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
						g_sFrom, g_sPassword);// Specify the Username and the Password
					}
			});
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void sendEMail() {
		try {
			Transport.send(this.g_objEmail);
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void setAttachment() {
		BodyPart v_objMessageBody;
		try {
			if (null != this.g_objFile) {
				v_objMessageBody = new MimeBodyPart();
				this.g_objMultiPart.addBodyPart(v_objMessageBody);
				v_objMessageBody.setDataHandler(new DataHandler(new FileDataSource(this.g_objFile)));
				v_objMessageBody.setFileName(this.g_objFile.getName());
			}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void setBasicVariables() {
		String v_sVal;
		ApplicationContext v_objContext;
		try {
			v_objContext = ApplicationContext.getInstance();
			v_sVal = v_objContext.getProperty("email.host");
			if (null == v_sVal || "".equals(v_sVal)) {
			} else {
				this.g_sHost = v_sVal;
			}
			v_sVal = v_objContext.getProperty("email.from");
			if (null == v_sVal || "".equals(v_sVal)) {
			} else {
				this.g_sFrom = v_sVal;
			}
			v_sVal = v_objContext.getProperty("email.to");
			if (null == v_sVal || "".equals(v_sVal)) {
			} else {
				this.g_sTo = v_sVal;
			}
			v_sVal = v_objContext.getProperty("email.password");
			if (null == v_sVal || "".equals(v_sVal)) {
			} else {
				this.g_sPassword = v_sVal;
			}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void setMessage() {
		BodyPart v_objMessageBody;
		try {
			this.g_objMultiPart = new MimeMultipart();
			this.g_objEmail.setContent(this.g_objMultiPart);
			v_objMessageBody = new MimeBodyPart();
			this.g_objMultiPart.addBodyPart(v_objMessageBody);

			if (null != this.g_objException) {
				v_objMessageBody.setText(this.extractMessageFromException());

			} else {
				v_objMessageBody.setText("You have a bug");
			}
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}

	private void setSubject() {
		try {
			this.g_objEmail.setSubject("Error Encountered while executing Test Cases.");
		} catch (Exception v_exException) {
			v_exException.printStackTrace();
		}
	}
}
