package programranje.yatospace.server.picture.controller;

import programranje.yatospace.server.basic.bean.ProfileBean;
import programranje.yatospace.server.config.controller.GeneralConfigController;

/**
 * Контрола корисничке слике. 
 * @author mirko
 * @version 1.0
 */
public class ProfileImageController {
	public void initEmptyProfileImage(ProfileBean bean) {
		bean.setProfilePictureAddress("");
	}
	
	public void initRegularProfileImage(ProfileBean bean) {
		bean.setProfilePictureAddress(GeneralConfigController.mainAppConfigEngine.services.getProfilePictureURL());
	}
}
