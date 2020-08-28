package programranje.yatospace.server.config.engine.impl;

import java.io.File;

import programranje.yatospace.server.config.engine.GeneralPropertyConfigurationResourceEngine;

/**
 * Поставке општих параметара везаних за апликацију и који се у њој користе. 
 * @author MV
 * @version 1.0
 */
public class AppParametersConfigEngine extends GeneralPropertyConfigurationResourceEngine{
	
	public AppParametersConfigEngine(String dir) {
		super(new File(dir, "parameters.properties"), "/programranje/yatospace/server/config/resources/parameters.properties");
	}
	
	@Override
	public void initalize() {
		super.initalize();
	}
	
	public String getGeneralTitle() {
		return this.getContent().getProperty("general.title"); 
	}
	
	public String getErorrTitle() {
		return this.getContent().getProperty("error.title"); 
	}
	
	public String getGeneralIcon() {
		return this.getContent().getProperty("general.icon"); 
	}
	
	public String getErrorIcon() {
		return this.getContent().getProperty("error.icon"); 
	}
	
	public String getErrorHeader() {
		return this.getContent().getProperty("error.header"); 
	}
}
