package programranje.yatospace.server.picture.controller;

import programranje.yatospace.server.basic.bean.UserBean;
import programranje.yatospace.server.config.controller.GeneralConfigController;

/**
 * Иницијализација корисничког зрна. 
 * @author mirko
 * @version 1.0
 */
public class UserImageController {
	public void initEmptyUserImage(UserBean bean) {
		bean.setUserPictureAddress("");
	}
	
	public void initRegularUserImage(UserBean bean) {
		bean.setUserPictureAddress(GeneralConfigController.mainAppConfigEngine.services.getUserPictureURL());
	}
}
