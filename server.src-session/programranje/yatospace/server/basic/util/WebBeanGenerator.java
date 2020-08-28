package programranje.yatospace.server.basic.util;

import javax.servlet.http.HttpSession;

import programranje.yatospace.server.basic.bean.ApplicationBean;
import programranje.yatospace.server.basic.bean.MessageBean;
import programranje.yatospace.server.basic.bean.ProfileBean;
import programranje.yatospace.server.basic.bean.ServerBean;
import programranje.yatospace.server.basic.bean.SessionBean;
import programranje.yatospace.server.basic.bean.UserBean;
import programranje.yatospace.server.basic.config.SessionDataConstantsConfiguration;

/**
 * Генератор зрна из окриужења као што су веб сесије. 
 * @author mirko
 * @version 1.0
 */
public final class WebBeanGenerator {
	private WebBeanGenerator() {}
	
	public static ProfileBean generateOrGetProfileBean(HttpSession session) {
		ProfileBean bean = (ProfileBean) session.getAttribute(SessionDataConstantsConfiguration.PROFILE_BEAN); 
		if(bean==null) {
			bean = new ProfileBean(); 
			session.setAttribute(SessionDataConstantsConfiguration.PROFILE_BEAN, bean);
		}
		return bean; 
	}
	
	public static UserBean generateOrGetUserBean(HttpSession session) {
		UserBean bean = (UserBean) session.getAttribute(SessionDataConstantsConfiguration.USER_BEAN); 
		if(bean==null) {
			bean = new UserBean();
			session.setAttribute(SessionDataConstantsConfiguration.USER_BEAN, bean);
		}
		return bean; 
	}
	
	public static SessionBean generateOrGetSessionBean(HttpSession session) {
		SessionBean bean = (SessionBean) session.getAttribute(SessionDataConstantsConfiguration.SESSION_BEAN); 
		if(bean==null){
			bean = new SessionBean();
			session.setAttribute(SessionDataConstantsConfiguration.SESSION_BEAN, bean);
		}
		return bean;  
	}
	
	public static MessageBean generateOrGetMessageBean(HttpSession session) {
		MessageBean bean = (MessageBean) session.getAttribute(SessionDataConstantsConfiguration.MESSAGE_BEAN); 
		if(bean==null){
			bean = new MessageBean();
			session.setAttribute(SessionDataConstantsConfiguration.MESSAGE_BEAN, bean);
		}
		return bean;  
	}
	
	public static ServerBean generateOrGetServerBean(HttpSession session) {
		ServerBean bean = (ServerBean) session.getAttribute(SessionDataConstantsConfiguration.SERVER_BEAN); 
		if(bean==null){
			bean = new ServerBean();
			session.setAttribute(SessionDataConstantsConfiguration.SERVER_BEAN, bean);
		}
		return bean;  
	}
	
	public static ApplicationBean generateOrGetApplicationBean(HttpSession session) {
		ApplicationBean bean = (ApplicationBean) session.getAttribute(SessionDataConstantsConfiguration.APPLICATION_BEAN); 
		if(bean==null){
			bean = new ApplicationBean();
			session.setAttribute(SessionDataConstantsConfiguration.APPLICATION_BEAN, bean);
		}
		return bean;  
	}
}
