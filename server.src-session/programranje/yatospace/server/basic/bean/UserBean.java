package programranje.yatospace.server.basic.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import programiranje.yi.user.app.services.model.UserInformationModel;
import programranje.yatospace.server.basic.controller.SessionController;
import programranje.yatospace.server.basic.util.MessageType;
import programranje.yatospace.server.basic.util.WebBeanGenerator;
import programranje.yatospace.server.basic.web.listener.WebSessionListener;
import programranje.yatospace.server.picture.controller.UserImageController;

/**
 * Зрно које се користи за реазмјену информација 
 * о корисницима по страницама. 
 * @author mirko
 * @version 1.0
 */
public class UserBean implements Serializable{
	private static final long serialVersionUID = 2913014529161598083L;
	private String userPictureAddress = "";
	
	
	public String getUserPictureAddress() {
		return userPictureAddress;
	}
	public void setUserPictureAddress(String userPictureAddress) {
		this.userPictureAddress = userPictureAddress;
	} 
	
	public void init(HttpSession session) {
		SessionController controller = WebSessionListener.getSessionController(session);
		try {
			if(controller.getUserAdapter().getSessionClient()==null) {
				new UserImageController().initEmptyUserImage(this);
			}else if(controller.getUserAdapter().getSessionClient().getHttpClient()==null) {
				new UserImageController().initEmptyUserImage(this);
			}else {
				UserInformationModel user = controller.getUserAdapter().get(); 
				if(user.getUserImage()==null || user.getUserImage().length()==0)
					new UserImageController().initEmptyUserImage(this);
				else {
					new UserImageController().initRegularUserImage(this); 
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
		new UserImageController().initEmptyUserImage(this); 
	}
	
	public void regular() {
		new UserImageController().initRegularUserImage(this); 
	}
}
