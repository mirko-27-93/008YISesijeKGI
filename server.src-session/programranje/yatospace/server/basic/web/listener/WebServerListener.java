package programranje.yatospace.server.basic.web.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import programranje.yatospace.server.basic.task.ServerTimePropagationTask;

/**
 * Оушкивање актиности сервера и задавања задатака и сатова. 
 * @author mirko
 * @version 1.0
 */
@WebListener
public class WebServerListener implements ServletContextListener {
	public final static Timer serverTimeDistributer = new Timer();
	
    public WebServerListener() {}

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	serverTimeDistributer.cancel();
    	serverTimeDistributer.purge();
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	serverTimeDistributer.schedule(new ServerTimePropagationTask(), 1000, 1000);
    }	
}
