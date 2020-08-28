package programranje.yatospace.server.basic.bean;

import java.io.Serializable;

import programranje.yatospace.server.basic.util.MessageType;

/**
 * Зрно које се користи за поруке. 
 * @author mirko
 * @version 1.0
 */
public class MessageBean implements Serializable{
	private static final long serialVersionUID = 4018588317678370378L;
	
	private Exception exception; 
	private String message = "";
	private MessageType type = MessageType.INFO;
	
	public  MessageBean() {
		initDefault();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		if(message == null) message = ""; 
		this.message = message;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		if(type==null) type = MessageType.INFO;
		this.type = type;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	} 
	
	public MessageBean initDefault() {
		exception = null; 
		type = MessageType.INFO;
		this.message = "Апликација активна."; 
		return this; 
	}
}
