package programranje.yatospace.server.picture.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import programiranje.yi.user.app.services.client.ProfileImageAdapter;
import programranje.yatospace.server.basic.bean.ProfileBean;
import programranje.yatospace.server.basic.bean.SessionBean;
import programranje.yatospace.server.basic.controller.SessionController;
import programranje.yatospace.server.basic.util.WebBeanGenerator;
import programranje.yatospace.server.basic.web.listener.WebSessionListener;
import programranje.yatospace.server.picture.cache.PictureManager;
import programranje.yatospace.server.picture.center.PictureCacheCenter;

/**
 * Сервлет којим се преослеђује профилна слика. 
 * @author mirko
 * @version 1.0
 */
@WebServlet("/ProfileImageServlet")
public class ProfileImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ProfileImageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("image");
		
		SessionController controller = WebSessionListener.getSessionController(request.getSession());
		
		ProfileImageAdapter adapter = controller.getProfileImageAdapter(); 
		ProfileBean  profileBean = WebBeanGenerator.generateOrGetProfileBean(request.getSession()); 
		
		profileBean.init(request.getSession());
		
		PictureManager pictureManager = PictureCacheCenter.profilePictureCacheManager.sessionPicture(request.getSession().getId()); 
		if(pictureManager==null) {
			SessionBean bean = WebBeanGenerator.generateOrGetSessionBean(request.getSession());
			PictureCacheCenter.profilePictureCacheManager.register(request.getSession().getId(), bean.getInfo().getUserName(), controller.getUserAdapter().get().getUserImage());
			pictureManager = PictureCacheCenter.profilePictureCacheManager.sessionPicture(request.getSession().getId()); 
			byte[] image = adapter.getImage(profileBean.getProfilePictureAddress());
			PictureCacheCenter.profilePictureCacheManager.store(request.getSession().getId(), image);
			response.getOutputStream().write(image);
		}else {
			byte[] image = PictureCacheCenter.profilePictureCacheManager.load(request.getSession().getId()); 
			if(image == null) response.getOutputStream().write(adapter.getImage(profileBean.getProfilePictureAddress()));
			else response.getOutputStream().write(image);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
