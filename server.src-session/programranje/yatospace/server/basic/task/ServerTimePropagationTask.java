package programranje.yatospace.server.basic.task;

import java.util.ArrayList;
import java.util.TimerTask;

import javax.websocket.Session;

import programiranje.yi.user.app.services.client.ServerTimeClient;
import programiranje.yi.user.app.services.client.SessionClient;
import programranje.yatospace.server.basic.net.web.ServerTimeWebSocket;
import programranje.yatospace.server.config.controller.GeneralConfigController;

/**
 * Задатак за часовник, који се односи на пропагирање 
 * северског времена на веб прикључнице, односно 
 * странице сваке секунде. 
 * @author mirko
 * @version 1.0
 */
public class ServerTimePropagationTask extends TimerTask{
	public static final SessionClient timeServerSession = new SessionClient(); 
	public static final ServerTimeClient timeServer = new ServerTimeClient().setSessionClient(timeServerSession).trySessionSynchronize().tryReinit();
	
	static {
		GeneralConfigController.mainAppConfigEngine.initalize();
		timeServerSession.setServerAddress(GeneralConfigController.mainAppConfigEngine.services.getSessionServicesURL()); 
	}
	
	@Override
	public void run() {
		for(Session session : new ArrayList<>(ServerTimeWebSocket.sessions)) {
			session.getAsyncRemote().sendText(timeServer.covnvertServer()); 
		}
	}
}
