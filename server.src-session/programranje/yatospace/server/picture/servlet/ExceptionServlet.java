package programranje.yatospace.server.picture.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import programranje.yatospace.server.basic.bean.MessageBean;
import programranje.yatospace.server.basic.lang.PreviewException;
import programranje.yatospace.server.basic.util.WebBeanGenerator;

/**
 * Сервлет који служи за преглед грешке. 
 * @author mirko
 * @version 1.0
 */
@WebServlet("/ExceptionServlet")
public class ExceptionServlet extends HttpServlet{
	private static final long serialVersionUID = -3530517272839018241L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MessageBean messageBean = WebBeanGenerator.generateOrGetMessageBean(req.getSession());

		if(messageBean.getException()==null) {
			throw new PreviewException();
		}else{
			throw new PreviewException(messageBean.getException());
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
