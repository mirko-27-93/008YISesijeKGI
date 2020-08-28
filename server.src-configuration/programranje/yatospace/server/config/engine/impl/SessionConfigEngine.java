package programranje.yatospace.server.config.engine.impl;

import java.io.File;

import programranje.yatospace.server.basic.model.SessionInfo;
import programranje.yatospace.server.config.engine.GeneralPropertyConfigurationResourceEngine;

/**
 * Општи оквири за иницијализацију подразумијеваних вриједности на сесији. 
 * @author mirko
 * @version 1.0
 */
public class SessionConfigEngine extends GeneralPropertyConfigurationResourceEngine{

	public SessionConfigEngine(String generalDir) {
		super(new File(generalDir, "session.properties"), "/programranje/yatospace/server/config/resources/session.properties");
	}

	@Override
	public void initalize() {
		super.initalize();
	}
	
	public String databaseName() {
		return this.getContent().getProperty("database.name");
	}
	
	public String databaseAddress() {
		return this.getContent().getProperty("database.address");
	}
	
	public void init(SessionInfo sessions) {
		sessions.setDataBaseSvcName(databaseAddress()!=null?databaseAddress():"");
		sessions.setDataBase(databaseName()!=null?databaseName():"");
	}
}
