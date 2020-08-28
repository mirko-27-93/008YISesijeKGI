package programranje.yatospace.server.basic.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import programranje.yatospace.server.basic.controller.SessionController;
import programranje.yatospace.server.basic.model.SessionInfo;
import programranje.yatospace.server.basic.util.MessageType;
import programranje.yatospace.server.basic.util.SessionInfoDefaultGenerator;
import programranje.yatospace.server.basic.util.WebBeanGenerator;
import programranje.yatospace.server.basic.web.listener.WebSessionListener;

/**
 * Зрно помоћу ког се барата са сесијом, односно пријавом. 
 * @author mirko
 * @version 1.0
 */
public class SessionBean implements Serializable{
	private static final long serialVersionUID = -3720950147670680673L;
	private final SessionInfo nullInfo = SessionInfoDefaultGenerator.generateDefaultSessionInfo(); 
	
	private SessionInfo info = null;
	private String password = ""; 
	
	public SessionInfo getInfo() {
		if(info==null) return nullInfo; 
		return info;
	}
	public void setInfo(SessionInfo info) {
		this.info = info;
	} 
	
	public String login(HttpSession session) {
		updateBean(session); 
		SessionController controller = WebSessionListener.getSessionController(session);
		controller.login(session, getInfo(), password); 
		
		MessageBean info = WebBeanGenerator.generateOrGetMessageBean(session); 
		info.setException(null);
		
		if(controller.isLogged()) {
			info.setMessage("Корисник је успјешно пријављен.");
			info.setType(MessageType.SUCCESS);
		}else {
			info.setMessage("Пријава корисника није успјела. Провјерити параметре.");
			info.setType(MessageType.ERROR);
		}
		
		setInfo(controller.session());
		password = "";
		return ""; 
	}
	
	public String logout(HttpSession session) {
		updateBean(session);
		SessionController controller = WebSessionListener.getSessionController(session);
		controller.logout(session); 
		
		MessageBean info = WebBeanGenerator.generateOrGetMessageBean(session); 
		info.setException(null);
		
		if(!controller.isLogged()) {
			info.setMessage("Корисник је успјешно одјављен.");
			info.setType(MessageType.SUCCESS);
		}else {
			info.setMessage("Одјава корисника није успјела. Провјерити параметре.");
			info.setType(MessageType.ERROR);
		}
		
		setInfo(null);
		return ""; 
	}
	
	public boolean isLogged(HttpSession session) {
		SessionController controller = WebSessionListener.getSessionController(session);
		return controller.isLogged();
	}
	public boolean isLoggedUserProfile(HttpSession session) {
		SessionController controller = WebSessionListener.getSessionController(session);
		return controller.isLoggedViaUserProfile();
	}
	
	public String getPassword() {
		if(password==null) password = ""; 
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String resetPassword() {
		this.password = "";
		return ""; 
	}
	
	public String updateBean(HttpSession session) {
		SessionController controller = WebSessionListener.getSessionController(session);
		setInfo(controller.session());
		return ""; 
	}
	
	public String userProfileSessionId(HttpSession session) {
		if(!isLoggedUserProfile(session)) return ""; 
		SessionController controller = WebSessionListener.getSessionController(session);
		return controller.userProfileSessionId(); 
	}
}
