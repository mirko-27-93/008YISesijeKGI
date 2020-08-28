package programranje.yatospace.server.basic.event;

import javax.servlet.http.HttpSession;

import programranje.yatospace.server.basic.model.SessionInfo;

/**
 * Модел који се користи за реактивности при пријавама на сесије база података/сервера. 
 * @author mirko
 * @version 1.0
 */
public class LoginDBSessionRModel extends ReactionModel<LoginDBSessionRunnable>{
	private HttpSession session; 
	private SessionInfo info;
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
		for(String idAction: getBeforeActions()) {
			LoginDBSessionRunnable preaction = getBefore(idAction); 
			if(preaction!=null) preaction.setSession(session);
		}
		for(String idAction: getAfterActions()) {
			LoginDBSessionRunnable postaction = getAfter(idAction); 
			if(postaction!=null) postaction.setSession(session);
		}
	}
	public SessionInfo getInfo() {
		return info;
	}
	public void setInfo(SessionInfo info) {
		this.info = info;
		for(String idAction: getBeforeActions()) {
			LoginDBSessionRunnable preaction = getBefore(idAction); 
			if(preaction!=null) preaction.setInfo(info);
		}
		for(String idAction: getAfterActions()) {
			LoginDBSessionRunnable postaction = getAfter(idAction); 
			if(postaction!=null) postaction.setInfo(info);
		}
	} 
}
