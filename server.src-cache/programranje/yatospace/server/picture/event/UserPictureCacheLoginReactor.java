package programranje.yatospace.server.picture.event;

import programiranje.yi.user.app.services.client.UserInfoClient;
import programranje.yatospace.server.basic.event.LoginDBSessionRunnable;
import programranje.yatospace.server.picture.center.PictureCacheCenter;

/**
 * Корисничке реакције при пријави корисника. 
 * @author MV
 * @version 1.0
 */
public class UserPictureCacheLoginReactor extends LoginDBSessionRunnable{
	private static final long serialVersionUID = 1483571177527338189L;
	private transient UserInfoClient serviceAdapter;  
	
	public UserPictureCacheLoginReactor(UserInfoClient serviceAdapter) {
		this.serviceAdapter = serviceAdapter;
	}
	
	public UserInfoClient getServiceAdapter() {
		return serviceAdapter; 
	}
	
	public String getUserPicture() {
		return serviceAdapter.get().getUserImage();
	}
	
	public String getProfilePicture() {
		return serviceAdapter.get().getProfileImage();
	}
	
	public void setServiceAdapter(UserInfoClient serviceAdapter) {
		this.serviceAdapter = serviceAdapter;
	}

	@Override
	public void run() {
		String sessionId = getSession().getId();
		String username =  getInfo().getUserName();
		String userPicture = getUserPicture(); 
		String profilePicture = getProfilePicture();
		if(sessionId==null || username==null) return; 
		
		if(profilePicture!=null && profilePicture.trim().length()!=0)
			PictureCacheCenter.profilePictureCacheManager.register(sessionId, username, profilePicture);
		if(userPicture!=null && userPicture.trim().length()!=0)	
			PictureCacheCenter.userPictureCacheManager.register(sessionId, username, userPicture);
	}
	
}
