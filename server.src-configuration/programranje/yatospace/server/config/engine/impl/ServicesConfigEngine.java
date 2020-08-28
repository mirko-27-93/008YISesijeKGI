package programranje.yatospace.server.config.engine.impl;

import java.io.File;

import programranje.yatospace.server.config.engine.GeneralPropertyConfigurationResourceEngine;

/**
 * Поставке које се односе на адресе сервиса које се користе у апликацији. 
 * @author mirko
 * @version 1.0
 */
public class ServicesConfigEngine extends GeneralPropertyConfigurationResourceEngine{

	public ServicesConfigEngine(String dir) {
		super(new File(dir, "services.properties"), "/programranje/yatospace/server/config/resources/services.properties");
	}
	
	@Override
	public void initalize() {
		super.initalize();
	}
	
	public String getSessionServicesURL() {
		return getContent().getProperty("session.services"); 
	}
	
	public String getProfilePictureURL() {
		return getContent().getProperty("profile.picture.service"); 
	}
	
	public String getUserPictureURL() {
		return getContent().getProperty("user.picture.service"); 
	}
}
