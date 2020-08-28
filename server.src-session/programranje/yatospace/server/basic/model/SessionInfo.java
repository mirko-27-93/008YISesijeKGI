package programranje.yatospace.server.basic.model;

import java.io.Serializable;

/**
 * Сесије које се односе на сесије корисника базе података.
 * @author mirko
 * @version 1.0
 */
public class SessionInfo implements Serializable{
	private static final long serialVersionUID = 7409443288891856544L;
	private String userName = ""; 
	private String userPassword = ""; 
	private String dataBase = "";
	private String dataBaseSvcName = ""; 
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		if(userName==null) userName = "";
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		if(userPassword==null) userPassword = ""; 
		this.userPassword = userPassword;
	}
	public String getDataBase() {
		return dataBase;
	}
	public void setDataBase(String dataBase) {
		if(dataBase==null) dataBase = ""; 
		this.dataBase = dataBase;
	}
	public String getDataBaseSvcName() {
		return dataBaseSvcName;
	}
	public void setDataBaseSvcName(String dataBaseSvcName) {
		this.dataBaseSvcName = dataBaseSvcName;
	} 
}
