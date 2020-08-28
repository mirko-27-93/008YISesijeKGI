package programranje.yatospace.server.basic.controller;

import javax.servlet.http.HttpSession;

import programiranje.yi.user.app.services.client.DatabaseConfigurationClient;
import programiranje.yi.user.app.services.client.FastUserInfoClient;
import programiranje.yi.user.app.services.client.MessageConfigurationClient;
import programiranje.yi.user.app.services.client.ProfileImageAdapter;
import programiranje.yi.user.app.services.client.ServerTimeClient;
import programiranje.yi.user.app.services.client.SessionClient;
import programiranje.yi.user.app.services.client.UserImageAdapter;
import programiranje.yi.user.app.services.client.UserInfoClient;
import programiranje.yi.user.app.services.model.SessionInformation;
import programranje.yatospace.server.basic.event.LoginDBSessionRModel;
import programranje.yatospace.server.basic.event.LogoutDBSessionRModel;
import programranje.yatospace.server.basic.model.SessionInfo;
import programranje.yatospace.server.picture.event.UserPictureCacheLoginReactor;
import programranje.yatospace.server.picture.event.UserPictureCacheLogoutReactor;

/**
 * Контролер који се односи на пријаве и одјаве односно сесије база података.
 * @author mirko
 * @version 1.0
 */
public class SessionController {
	private SessionInfo logged; 
	
	private SessionClient sessionAdapter = new SessionClient(); 
	private UserInfoClient userAdapter = new UserInfoClient().setSessionClient(sessionAdapter);
	private MessageConfigurationClient messageConfigAdapter = new MessageConfigurationClient().setSessionClient(sessionAdapter);
	private DatabaseConfigurationClient databaseConfigurationAdapter = new DatabaseConfigurationClient().setSessionClient(sessionAdapter);
	
	private ServerTimeClient serverTimeAdapter = new ServerTimeClient().setSessionClient(sessionAdapter).tryReinit();
	private FastUserInfoClient fastInfoAdapter = new FastUserInfoClient().setSessionClient(sessionAdapter); 
	
	private UserImageAdapter userImageAdapter = new UserImageAdapter().setSessionClient(sessionAdapter); 
	private ProfileImageAdapter profileImageAdapter = new ProfileImageAdapter().setSessionClient(sessionAdapter); 
	
	
	public UserImageAdapter getUserImageAdapter() {
		return userImageAdapter;
	}

	public ProfileImageAdapter getProfileImageAdapter() {
		return profileImageAdapter;
	}

	public void reinitSessionAdapter() {
		sessionAdapter = new SessionClient(); 
		userAdapter.setSessionClient(sessionAdapter); 
		messageConfigAdapter.setSessionClient(sessionAdapter); 
		databaseConfigurationAdapter.setSessionClient(sessionAdapter); 
		serverTimeAdapter.setSessionClient(sessionAdapter).tryReinit(); 
		fastInfoAdapter.setSessionClient(sessionAdapter); 
		userImageAdapter = new UserImageAdapter().setSessionClient(sessionAdapter); 
		profileImageAdapter = new ProfileImageAdapter().setSessionClient(sessionAdapter); 
	}
	
	public SessionInfo login(HttpSession session, SessionInfo info, String password) {	
		try {
			loginReactor.setSession(session);
			loginReactor.setInfo(info);
			loginReactor.runBefore();
			SessionInformation i = new SessionInformation(); 
			i.setUsername(info.getUserName());
			i.setDatabase(info.getDataBase());
			i.setFile("");
			i.setEngine("");
			i.setType("DATABASE");
			i.setId(info.getDataBaseSvcName());
			i.setName(info.getDataBaseSvcName());
			
			sessionAdapter.login(i, password);
			loginReactor.runAfter();
			logged = info; 
		}catch(Exception ex) {
			reinitSessionAdapter();
			logged = null; 
		}
		return logged; 
	}
	public SessionInfo session() {
		return logged;
	}
	public boolean isLogged() {
		return logged!=null; 
	}
	public boolean isLoggedViaUserProfile() {
		return logged!=null; 
	}
	public String userProfileSessionId() {
		try {
			String[] parts = sessionAdapter.getSessionId().split(";"); 
			for(String part: parts) {
				if(part.startsWith("JSESSIONID=")) return part.substring("JSESSIONID=".length()); 
			}
			return "";
		}catch(Exception ex) {
			reinitSessionAdapter();
			return "";
		}
	}
	public SessionInfo logout(HttpSession session) {
		logoutReactor.setSession(session);
		logoutReactor.setInfo(logged);
		try {
			logoutReactor.runBefore();
			sessionAdapter.logout();
			logoutReactor.runAfter();
			logged = null; 
		}catch(Exception ex) {
			logged = null; 
		}finally {
			reinitSessionAdapter();
		}
		return logged;
	}
	
	public SessionClient getSessionAdapter() {
		return sessionAdapter;
	}
	public UserInfoClient getUserAdapter() {
		return userAdapter;
	}
	public MessageConfigurationClient getMessageConfigAdapter() {
		return messageConfigAdapter;
	}
	public DatabaseConfigurationClient getDatabaseConfigurationAdapter() {
		return databaseConfigurationAdapter;
	}
	public ServerTimeClient getServerTimeAdapter() {
		return serverTimeAdapter;
	}
	public FastUserInfoClient getFastInfoAdapter() {
		return fastInfoAdapter;
	}
	
	private LoginDBSessionRModel loginReactor = new LoginDBSessionRModel();
	private LogoutDBSessionRModel logoutReactor = new LogoutDBSessionRModel();

	public LoginDBSessionRModel getLoginReactor() {
		return loginReactor;
	}
	public void setLoginReactor(LoginDBSessionRModel loginReactor) {
		this.loginReactor = loginReactor;
	}
	public LogoutDBSessionRModel getLogoutReactor() {
		return logoutReactor;
	}
	public void setLogoutReactor(LogoutDBSessionRModel logoutReactor) {
		this.logoutReactor = logoutReactor;
	}
	
	private UserPictureCacheLoginReactor loginUPCacheRM = new UserPictureCacheLoginReactor(userAdapter); 
	private UserPictureCacheLogoutReactor logoutUPCacheRM = new UserPictureCacheLogoutReactor(userAdapter);
	{
		loginReactor.setAfter("cache.picture.up", loginUPCacheRM);
		logoutReactor.setBefore("cache.picture.up", logoutUPCacheRM);
	}
}
