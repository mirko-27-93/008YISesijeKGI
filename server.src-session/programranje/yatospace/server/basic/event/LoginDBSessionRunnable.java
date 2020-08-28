package programranje.yatospace.server.basic.event;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import programranje.yatospace.server.basic.model.SessionInfo;

/**
 * Извршавање активност прије и после логовања на сесију базе података. 
 * @author mirko
 * @version 1.0
 */
public abstract class LoginDBSessionRunnable implements Runnable, Serializable{
	private static final long serialVersionUID = 449614057074654112L;
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
