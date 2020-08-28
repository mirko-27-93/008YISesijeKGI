package programranje.yatospace.server.basic.web.listener;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import programranje.yatospace.server.basic.controller.SessionController;

/**
 * Ослушкивање отварања и затварања веб сесије. 
 * @author mirko
 * @version 1.0
 */
@WebListener
public class WebSessionListener implements HttpSessionListener {
	public final static HashMap<HttpSession, SessionController> sessionControllers = new HashMap<>();
	
    public WebSessionListener() {}

    public void sessionCreated(HttpSessionEvent arg)  { 
    	sessionControllers.put(arg.getSession(), new SessionController());
    }

    public void sessionDestroyed(HttpSessionEvent arg)  { 
    	sessionControllers.remove(arg.getSession()); 
    }
    
    public static SessionController getSessionController(HttpSession session) {
    	return sessionControllers.get(session); 
    }
}
