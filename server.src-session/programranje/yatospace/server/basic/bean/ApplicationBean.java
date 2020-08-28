package programranje.yatospace.server.basic.bean;

import java.io.Serializable;

import programranje.yatospace.server.config.controller.GeneralConfigController;

/**
 * Зрно којим се размјењују параметри апликације.
 * @author MV
 * @version 1.0
 */
public class ApplicationBean implements Serializable{
	private static final long serialVersionUID = -5880399619847272928L;
	private String generalIcon = ""; 
	private String generalTitle = ""; 
	private String errorTitle = ""; 
	private String errorIcon = ""; 
	private String errorHeader = "";
	
	public ApplicationBean() {
		initDefaultObjectFoo();
	}
	
	public String getGeneralIcon() {
		return generalIcon;
	}
	public void setGeneralIcon(String generalIcon) {
		this.generalIcon = generalIcon;
	}
	public String getGeneralTitle() {
		return generalTitle;
	}
	public void setGeneralTitle(String generalTitle) {
		this.generalTitle = generalTitle;
	}
	public String getErrorTitle() {
		return errorTitle;
	}
	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}
	public String getErrorIcon() {
		return errorIcon;
	}
	public void setErrorIcon(String errorIcon) {
		this.errorIcon = errorIcon;
	}
	public String getErrorHeader() {
		return errorHeader;
	}
	public void setErrorHeader(String errorHeader) {
		this.errorHeader = errorHeader;
	} 
	
	public String initDefaultStringFoo() {
		initDefaultObjectFoo();
		return "";
	}
	public ApplicationBean initDefaultObjectFoo() {
		generalIcon = GeneralConfigController.mainAppConfigEngine.appParams.getGeneralIcon(); 
		generalTitle = GeneralConfigController.mainAppConfigEngine.appParams.getGeneralTitle();
		errorIcon = GeneralConfigController.mainAppConfigEngine.appParams.getErrorIcon(); 
		errorTitle = GeneralConfigController.mainAppConfigEngine.appParams.getErorrTitle();
		errorHeader = GeneralConfigController.mainAppConfigEngine.appParams.getErrorHeader();
		return this;
	}
}
