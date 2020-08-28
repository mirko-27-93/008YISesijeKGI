package programranje.yatospace.server.basic.event;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import programranje.yatospace.server.basic.model.SessionInfo;

/**
 * Реактивности везане за одјаву сесије корисника кроз профил базе података. 
 * @author mirko
 * @version 1.0
 */
public abstract class LogoutDBSessionRunnable implements Runnable, Serializable{
	private static final long serialVersionUID = 765543048533858462L;
	private HttpSession session; 
	private SessionInfo info;
	
	
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public SessionInfo getInfo() {
		return info;
	}

	public void setInfo(SessionInfo info) {
		this.info = info;
	}



	@Override
	public abstract void run(); 
}
