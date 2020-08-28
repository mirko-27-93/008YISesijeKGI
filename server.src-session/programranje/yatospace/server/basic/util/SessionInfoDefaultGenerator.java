package programranje.yatospace.server.basic.util;

import programranje.yatospace.server.basic.model.SessionInfo;
import programranje.yatospace.server.config.controller.GeneralConfigController;

/**
 * Опште фабричке методе када је у питању сесијски поразумијевани подаци.
 * @author mirko
 * @version 1.0
 */
public final class SessionInfoDefaultGenerator {
	private SessionInfoDefaultGenerator() {}
	
	public static SessionInfo generateDefaultSessionInfo() {
		SessionInfo si = new SessionInfo();
		String dbName = GeneralConfigController.mainAppConfigEngine.session.databaseName();
		String dbAddress = GeneralConfigController.mainAppConfigEngine.session.databaseAddress();
		si.setDataBase(dbAddress);
		si.setDataBaseSvcName(dbName);
		return si; 
	}
}
