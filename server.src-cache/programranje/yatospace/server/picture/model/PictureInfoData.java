package programranje.yatospace.server.picture.model;

import java.io.Serializable;

/**
 * Општи модел податка слике и основних података, за потребе серијализације 
 * при кеширању, односно памћењу слике. 
 * @author MV
 * @version 1.0
 */
public class PictureInfoData implements Serializable{
	private static final long serialVersionUID = 7201261370966782105L;
	private String name = "";
	private String user = ""; 
	private String sessionId = "";
	
	private byte[] image = new byte[0];
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
