package programranje.yatospace.server.basic.bean;

import java.io.Serializable;

/**
 * Зрно које се користи за потребе, размјене информација о страницама, 
 * односно за повратне информације о страници. 
 * @author mirko
 * @version 1.0
 */
public class WebPageBean implements Serializable{
	private static final long serialVersionUID = -5205598329434965401L;
	
	private String backPage = "";

	public String getBackPage() {
		return backPage;
	}
	
	public void setBackPage(String backPage) {	
		if(backPage==null) backPage=""; 
		this.backPage = backPage;
	}
}
