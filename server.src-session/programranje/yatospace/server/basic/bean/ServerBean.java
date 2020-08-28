package programranje.yatospace.server.basic.bean;

import java.io.Serializable;

import programranje.yatospace.server.basic.object.WebSocketDescriptor;
import programranje.yatospace.server.basic.task.ServerTimePropagationTask;
import programranje.yatospace.server.config.controller.GeneralConfigController;

/**
 * Зрно којим се дијеле информације о серверу, 
 * укључив и серверско вријеме.
 * @author mirko
 * @version 1.0
 */
public class ServerBean implements Serializable{
	private static final long serialVersionUID = -7529655028830416416L;
	private String serverTimeRecord = "";
	private WebSocketDescriptor serverTimeSocketDescribe = GeneralConfigController.mainAppConfigEngine.webSockets.getServerTimeDescription();
	
	
	
	public String getServerTimeRecord() {
		return serverTimeRecord;
	}
	public void setServerTimeRecord(String serverTimeRecord) {
		if(serverTimeRecord!=null) serverTimeRecord=""; 
		this.serverTimeRecord = serverTimeRecord;
	} 
	
	
	
	public WebSocketDescriptor getServerTimeSocketDescribe() {
		return serverTimeSocketDescribe;
	}
	public void setServerTimeSocketDescribe(WebSocketDescriptor serverTimeSocketDescribe) {
		this.serverTimeSocketDescribe = serverTimeSocketDescribe;
	}
	
	
	public ServerBean init() {
		serverTimeRecord = ServerTimePropagationTask.timeServer.covnvertServer();
		return this; 
	} 
}
