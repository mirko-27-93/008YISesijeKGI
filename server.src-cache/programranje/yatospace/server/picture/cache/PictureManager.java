package programranje.yatospace.server.picture.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import programranje.yatospace.server.picture.model.PictureInfoData;

/**
 * Управљање архивом слике. 
 */
public class PictureManager implements Serializable{
	private static final long serialVersionUID = -5812324153359267104L;
	private String name = "";
	private String user = ""; 
	private String sessionId = ""; 
	private File file; 
	private PictureInfoData image; 
	
	public PictureManager(File file) {
		if(file==null) throw new NullPointerException();
		this.file = file; 
	}

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

	public File getFile() {
		return file;
	}

	public PictureInfoData getImage() {
		return image;
	}

	public void setImage(PictureInfoData image) {
		this.image = image;
	}
	
	public void addPicture(byte[] picture) {
		image = new PictureInfoData();
		image.setImage(picture);
		image.setName(name);
		image.setSessionId(sessionId);
		image.setUser(user);
	}
	
	public void removePicture() {
		image = null; 
	}
	
	public void synchronizeWithObject() {
		if(image==null) return;
		this.name = image.getName(); 
		this.sessionId = image.getSessionId();
		this.user = image.getUser(); 
	}
	
	public boolean found() {
		return image!=null; 
	}
	
	public void serialize() throws FileNotFoundException, IOException {
		if(!found()) return; 
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(image);
		}
	}
	
	public void marshallize() throws FileNotFoundException, IOException {
		if(!found()) return; 
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(image);
		}
	}
	
	public boolean foundRecord() {
		return file.exists(); 
	}
	
	public void deserialize() {
		if(!foundRecord()) return;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){ 
			image = (PictureInfoData) ois.readObject(); 
			synchronizeWithObject();
		}catch(Exception ex) {
			return; 
		} 
	}
	
	public void unmarshall() {
		if(!foundRecord()) return;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){ 
			image = (PictureInfoData) ois.readObject(); 
			synchronizeWithObject();
		}catch(Exception ex) {
			return; 
		} 
	}
	
	public void deleteRecord() {
		if(foundRecord())
			file.delete(); 
	}
}
