package programranje.yatospace.server.basic.object;

import java.io.Serializable;

/**
 * Објекат којим се описује веб прикључница. 
 * @author mirko
 * @version 1.0
 */
public class WebSocketDescriptor implements Serializable{
	private static final long serialVersionUID = -7795794001915297742L;
	
	private String id = "";
	private String protocol = "ws";
	private String host = "127.0.0.1"; 
	private String port = "8080"; 
	private String path = "";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		if(id==null) id=""; 
		this.id = id;
	}
	
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		if(protocol==null) protocol = ""; 
		this.protocol = protocol;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		if(host==null) host = ""; 
		this.host = host;
	}
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		if(port==null) port = ""; 
		this.port = port;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		if(path==null) path = ""; 
		this.path = path;
	} 
}
