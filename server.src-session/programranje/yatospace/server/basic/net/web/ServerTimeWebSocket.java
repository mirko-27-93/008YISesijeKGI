package programranje.yatospace.server.basic.net.web;

import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Веб сокет којим се испоручује тренутно серверско вријеме, 
 * са промјенама на једној секунди. 
 * @author mirko
 * @version 1.0
 */

@ServerEndpoint("/server/time")
public class ServerTimeWebSocket {
	public static ArrayList<Session> sessions = new ArrayList<>(); 
	
	@OnOpen
    public void onOpen(Session session) {
		sessions.add(session); 
    }
    
	@OnClose
    public void onClose(Session session) {
        sessions.remove(session); 
    }
    
    @OnError public void onError(Throwable t) {}
}
