package programranje.yatospace.server.config.controller;

import java.io.File;

import programranje.yatospace.server.config.engine.impl.AppParametersConfigEngine;
import programranje.yatospace.server.config.engine.impl.ServicesConfigEngine;
import programranje.yatospace.server.config.engine.impl.SessionConfigEngine;
import programranje.yatospace.server.config.engine.impl.WebSocketConfigEngine;

/**
 * Опште мјесто за конфигурације. 
 * @author mirko
 * @version 1.0
 */
public class GeneralConfigController implements GeneralInitializerInterface, GeneralFinalizerInterface{
	public static final GeneralConfigController mainAppConfigEngine = new GeneralConfigController();
	
	public final static  String GENERAL_FILE_FOLDER_DIR = "C:\\Users\\MV\\Documents\\Eclipse\\eclipse-workspace-2\\008YISesijeKGI"; 
	public final static  String GENERAL_CONFIGURATION_DIR = "C:\\Users\\MV\\Documents\\Eclipse\\eclipse-workspace-2\\008YISesijeKGI\\configuration"; 
	
	public void initGeneralConfigDir() {
		try {
			File file = new File(GENERAL_CONFIGURATION_DIR);
			if(!file.exists()) file.mkdirs();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private boolean initialized; 
	private boolean finalized; 
	
	@Override
	public void finish() {
		if(finalized) return; 
		finalized = true; 
	}

	@Override
	public void initalize() {
		if(initialized) return; 
		initGeneralConfigDir();
		session.initalize();
		services.initalize();
		webSockets.initalize();
		appParams.initalize();
		initialized = true; 
	} 
	
	public final SessionConfigEngine session = new SessionConfigEngine(GENERAL_CONFIGURATION_DIR);
	public final ServicesConfigEngine services = new ServicesConfigEngine(GENERAL_CONFIGURATION_DIR);
	public final WebSocketConfigEngine webSockets = new WebSocketConfigEngine(GENERAL_CONFIGURATION_DIR);
	public final AppParametersConfigEngine appParams = new AppParametersConfigEngine(GENERAL_CONFIGURATION_DIR);
	
	public boolean isInitialized() {
		return initialized;
	}

	public boolean isFinalized() {
		return finalized;
	}
}
