package programranje.yatospace.server.picture.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import programranje.yatospace.server.picture.center.PictureCacheCenter;

/**
 * Ослушкивач сервера који се односни на иницијализацију и финализацију 
 * кеша за корисничке и профилне слике. 
 * @author MV
 * @version 1.0
 */
@WebListener
public class PictureCacheWebInitalizer implements ServletContextListener {

    public PictureCacheWebInitalizer() {}

    public void contextDestroyed(ServletContextEvent arg0)  {}

    public void contextInitialized(ServletContextEvent arg0)  {
    	try { PictureCacheCenter.profilePictureCacheManager.close();}
    	catch (Exception ex) { ex.printStackTrace(); }
    }
}
