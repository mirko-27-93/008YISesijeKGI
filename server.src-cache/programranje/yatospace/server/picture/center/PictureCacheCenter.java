package programranje.yatospace.server.picture.center;

import java.io.File;

import programiranje.yi.user.app.services.client.UserInfoClient;
import programiranje.yi.user.app.services.model.UserInformationModel;
import programranje.yatospace.server.picture.engine.UserSessionsPicturesManager;

/**
 * Апликациони центар за кеширање слика које се преузимају са сервиса. 
 * @author MV
 * @version 1.0
 */
public final class PictureCacheCenter {
	private PictureCacheCenter() {}
	public static final UserSessionsPicturesManager userPictureCacheManager    = new UserSessionsPicturesManager(new File(System.getProperty("java.io.tmpdir")));
	public static final UserSessionsPicturesManager profilePictureCacheManager = new UserSessionsPicturesManager(new File(System.getProperty("java.io.tmpdir")));
	
	public static final void synchronizedUserImageWithService(String sessionId, UserInfoClient userInfoClient) {
		if(userInfoClient == null) return; 
		UserInformationModel uim = userInfoClient.get();
		if(uim==null) return ;
		userPictureCacheManager.synchronizeWithService(sessionId, uim.getUserImage());
	}
	
	public static final void synchronizedProfileImageWithService(String sessionId, UserInfoClient userInfoClient) {
		if(userInfoClient == null) return;
		UserInformationModel uim = userInfoClient.get();
		if(uim==null) return ;
		profilePictureCacheManager.synchronizeWithService(sessionId, uim.getProfileImage());
	}
	
	public static final void synchronizedAllImagesWithService(String sessionId, UserInfoClient userInfoClient) {
		if(userInfoClient == null) return;
		UserInformationModel uim = userInfoClient.get();
		if(uim==null) return ;
		userPictureCacheManager.synchronizeWithService(sessionId, uim.getUserImage());
		profilePictureCacheManager.synchronizeWithService(sessionId, uim.getProfileImage());
	}
}