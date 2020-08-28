package programranje.yatospace.server.picture.event;

import programiranje.yi.user.app.services.client.UserInfoClient;
import programranje.yatospace.server.basic.event.LogoutDBSessionRunnable;
import programranje.yatospace.server.picture.center.PictureCacheCenter;

/**
 * Корисничке реакције при одјави корисника. 
 * @author MV
 * @version 1.0
 */
public class UserPictureCacheLogoutReactor extends LogoutDBSessionRunnable{
	public final boolean ERROR_REMIX = false; 
	private static final long serialVersionUID = 1413796349342794141L;
	private transient UserInfoClient serviceAdapter;  
	
	public UserPictureCacheLogoutReactor(UserInfoClient serviceAdapter) {
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
		try {
			
			String sessionId = getSession().getId();
			String userPicture = getUserPicture(); 
			String profilePicture = getProfilePicture();
		
			if(sessionId==null) return; 
		
			if(profilePicture!=null) PictureCacheCenter.profilePictureCacheManager.unregister(sessionId);
			if(userPicture!=null)	 PictureCacheCenter.userPictureCacheManager.unregister(sessionId);
		}catch(Exception ex) {
			if(ERROR_REMIX) ex.printStackTrace();
		}
	}
}
