package programranje.yatospace.server.basic.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import programiranje.yi.user.app.services.model.UserInformationModel;
import programranje.yatospace.server.basic.controller.SessionController;
import programranje.yatospace.server.basic.util.MessageType;
import programranje.yatospace.server.basic.util.WebBeanGenerator;
import programranje.yatospace.server.basic.web.listener.WebSessionListener;
import programranje.yatospace.server.picture.controller.ProfileImageController;

/**
 * Општепрофилни подаци. 
 * @author mirko
 * @version 1.0
 */
public class ProfileBean implements Serializable{
	private static final long serialVersionUID = 6789628367802776832L;
	private String profilePictureAddress = "";
	
	public String getProfilePictureAddress() {
		return profilePictureAddress;
	}
	public void setProfilePictureAddress(String profilePictureAddress) {
		this.profilePictureAddress = profilePictureAddress;
	}
	
	public void init(HttpSession session) {
		SessionController controller = WebSessionListener.getSessionController(session);
		try {
			if(controller.getProfileImageAdapter().getSessionClient()==null) {
				new ProfileImageController().initEmptyProfileImage(this); 
			}else if(controller.getProfileImageAdapter().getSessionClient().getHttpClient()==null) {
				new ProfileImageController().initEmptyProfileImage(this); 
			}else {
				UserInformationModel user = controller.getUserAdapter().get(); 
				if(user.getProfileImage()==null || user.getProfileImage().length()==0) {
					new ProfileImageController().initEmptyProfileImage(this);
				} else {
					new ProfileImageController().initRegularProfileImage(this); 
				}
			}
		}catch(Exception ex) {
			controller.logout(session);
			WebBeanGenerator.generateOrGetMessageBean(session).setException(null);
			WebBeanGenerator.generateOrGetMessageBean(session).setMessage("Сесија је пукла.");
			WebBeanGenerator.generateOrGetMessageBean(session).setType(MessageType.INFO);
		}
	}
	
	public void empty() {
		new ProfileImageController().initEmptyProfileImage(this); 
	}
	
	public void regular() {
		new ProfileImageController().initRegularProfileImage(this); 
	}
}
