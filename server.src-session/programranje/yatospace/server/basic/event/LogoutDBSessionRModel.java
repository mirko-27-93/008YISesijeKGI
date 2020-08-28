package programranje.yatospace.server.basic.event;

import javax.servlet.http.HttpSession;

import programranje.yatospace.server.basic.model.SessionInfo;

/**
 * Реакције на одјаву серверске корисничке сесије (сесије базе података). 
 * @author mirko
 * @version 1.0
 */
public class LogoutDBSessionRModel extends ReactionModel<LogoutDBSessionRunnable>{
	private HttpSession session; 
	private SessionInfo info;
	
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
		for(String idAction: getBeforeActions()) {
			LogoutDBSessionRunnable preaction = getBefore(idAction); 
			if(preaction!=null) preaction.setSession(session);
		}
		for(String idAction: getAfterActions()) {
			LogoutDBSessionRunnable postaction = getAfter(idAction); 
			if(postaction!=null) postaction.setSession(session);
		}
	}
	public SessionInfo getInfo() {
		return info;
	}
	public void setInfo(SessionInfo info) {
		this.info = info;
		for(String idAction: getBeforeActions()) {
			LogoutDBSessionRunnable preaction = getBefore(idAction); 
			if(preaction!=null) preaction.setInfo(info);
		}
		for(String idAction: getAfterActions()) {
			LogoutDBSessionRunnable postaction = getAfter(idAction); 
			if(postaction!=null) postaction.setInfo(info);
		}
	} 
}
