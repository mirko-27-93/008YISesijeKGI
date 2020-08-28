package programranje.yatospace.server.config.engine.impl;

import java.io.File;

import programranje.yatospace.server.basic.object.WebSocketDescriptor;
import programranje.yatospace.server.config.engine.GeneralPropertyConfigurationResourceEngine;

/**
 * Конфигурације које се односе на веб прикључнице. 
 * @author mirko
 * @version 1.0
 */
public class WebSocketConfigEngine extends GeneralPropertyConfigurationResourceEngine{

	public WebSocketConfigEngine(String dir) {
		super(new File(dir, "websocks.properties"), "/programranje/yatospace/server/config/resources/websocks.properties");
	}
	
	@Override
	public void initalize() {
		super.initalize();
	}
	
	public WebSocketDescriptor getServerTimeDescription() {
		WebSocketDescriptor wsd = new WebSocketDescriptor();
		wsd.setId("server_time");
		wsd.setProtocol(getContent().getProperty("ws.server_time.protocol"));
		wsd.setHost(getContent().getProperty("ws.server_time.host"));
		wsd.setPort(getContent().getProperty("ws.server_time.port"));
		wsd.setPath(getContent().getProperty("ws.server_time.path"));
		return wsd; 
	}
}
