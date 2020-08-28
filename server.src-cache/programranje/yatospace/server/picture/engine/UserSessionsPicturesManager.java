package programranje.yatospace.server.picture.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import programranje.yatospace.server.picture.cache.PictureManager;

/**
 * Управљање опслуживањем за једну слику по кориснику и везу за сваку 
 * сесију, а која се налази у привременом директоријуму, односно кеш 
 * меморији за  потребе кеширања слика које се на сервер прибаљају 
 * са сервиса. 
 * @author MV
 * @version 1.0
 */
public class UserSessionsPicturesManager implements Serializable, AutoCloseable{
	private static final long serialVersionUID = -3107929924268929675L;
	private File directory; 
	private HashMap<String, PictureManager> userPictureMap = new HashMap<>(); 
	private HashMap<String, String> sessionUserMap = new HashMap<>(); 
	private long currentTotalSize = 0;
	private long limitTotalSize = 1024*1024*1024*50;  
	
	public UserSessionsPicturesManager(File directory) {
		if(directory==null)     throw new RuntimeException("zero");
		if(!directory.exists()) throw new RuntimeException("blank");
		this.directory = directory;
	}
	
	public synchronized List<String> users(){
		return new ArrayList<>(userPictureMap.keySet()); 
	}
	
	public synchronized List<PictureManager> pictures(){
		return new ArrayList<>(userPictureMap.values());
	}
	
	public synchronized List<String> sessions(){
		return new ArrayList<>(sessionUserMap.keySet()); 
	}
	
	public synchronized List<String> sessionUsers(){
		return new ArrayList<>(sessionUserMap.values()); 
	}
	
	public synchronized PictureManager userPicture(String user) {
		return userPictureMap.get(user); 
	}
	
	public synchronized String sessionUser(String sessionId) {
		return sessionUserMap.get(sessionId); 
	}
	
	public synchronized PictureManager sessionPicture(String sessionId) {
		String user = sessionUser(sessionId);
		if(user==null) return null;
		else return(userPicture(user));
	}
	
	public long currentTotalSize() {
		return currentTotalSize; 
	}
	
	public long limitTotalSize() {
		return limitTotalSize; 
	}
	
	public synchronized List<String> userSessions(String user){
		ArrayList<String> sessions = new ArrayList<>(); 
		if(user==null) return sessions;
		for(Map.Entry<String, String> me: sessionUserMap.entrySet())
			if(user.contentEquals(me.getValue())) sessions.add(me.getKey());
		return sessions; 
	}
	
	public synchronized List<String> pictureUsers(PictureManager pict){
		ArrayList<String> users = new ArrayList<>(); 
		for(Map.Entry<String, PictureManager> me: userPictureMap.entrySet())
			if(pict==me.getValue()) users.add(me.getKey());
		return users; 
	}
	
	public synchronized List<String> pictureSessions(PictureManager pict){
		ArrayList<String> sessions = new ArrayList<>(); 
		for(String user: pictureUsers(pict)) 
			sessions.addAll(userSessions(user));
		return sessions; 
	}
	
	public void deleteAll() {
		for(File file : directory.listFiles()) 
			file.delete();
		currentTotalSize = 0;
	}
	
	public synchronized void clear() {
		sessionUserMap.clear();
		userPictureMap.clear();
	}
	
	public synchronized void clean() {
		deleteAll(); 
		clear();
	}
	
	public File directory() {
		return directory;
	}

	
	public synchronized void register(String session, String username, String pictureName) {		
		String user = sessionUser(session);
		PictureManager sessionPictureManager = sessionPicture(session); 
		
		if(user!=null && !username.contentEquals(user)) return; 
		if(user!=null && sessionPictureManager!=null) return;
		
		
			PictureManager image = new PictureManager(new File(directory, username+"_"+pictureName));
			
			image.setName(pictureName);
			image.setSessionId(session);
			image.setUser(username);
			
			if(sessionPictureManager!=null && !pictureName.contentEquals(sessionPictureManager.getName())){
				if(sessionPictureManager.getFile().exists()) {
					currentTotalSize -= sessionPictureManager.getFile().length();
					sessionPictureManager.deleteRecord();
				}
			}
			
			sessionUserMap.put(session, username); 
			userPictureMap.put(user, image);	
	}
	
	
	public synchronized void unregister(String session) {
		String user = sessionUser(session);
		PictureManager picture = sessionPicture(session); 
		
		if(user==null) return; 
		sessionUserMap.remove(session); 
		if(picture==null) return; 
	}
	
	public void store(String sessionId, byte[] image) {
		PictureManager sessionPictureManager = sessionPicture(sessionId);
		if(sessionPictureManager == null) return; 
		if(image==null) return; 
		if(currentTotalSize+image.length>limitTotalSize) return; 		
		delete(sessionId); 
		sessionPictureManager.addPicture(image);
		currentTotalSize+=image.length;
	}
	
	public void delete(String sessionId) {
		PictureManager sessionPictureManager = sessionPicture(sessionId);
		if(sessionPictureManager == null) return; 
		if(sessionPictureManager.getFile().exists()) {
			currentTotalSize-=sessionPictureManager.getFile().length();
			sessionPictureManager.getFile().delete(); 
		}
	}
	
	public byte[] load(String sessionId) {
		PictureManager sessionPictureManager = sessionPicture(sessionId);
		if(sessionPictureManager==null) return null;
		if(!sessionPictureManager.foundRecord()) return null;
		try(FileInputStream fis = new FileInputStream(sessionPictureManager.getFile())) {
			return IOUtils.toByteArray(fis);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void load(String sessionId, OutputStream output) {
		PictureManager sessionPictureManager = sessionPicture(sessionId);
		if(sessionPictureManager==null) return;
		if(!sessionPictureManager.foundRecord()) return;
		try(FileInputStream fis = new FileInputStream(sessionPictureManager.getFile())) {
			while(true) {
				int by = fis.read();
				if(by==-1) return;
				output.write(by);
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public boolean exists(String sessionId) {
		PictureManager sessionPictureManager = sessionPicture(sessionId);
		if(sessionPictureManager == null) return false; 
		return sessionPictureManager.getFile().exists(); 
	}
	
	@Override
	public void close() throws Exception {
		clean();
	}

	
	public void synchronizeWithService(String sessionId, String serviceImage) {
		PictureManager sessionPictureManager = sessionPicture(sessionId);
		String user = sessionUser(sessionId);
		String userImage = serviceImage; 
		if(user==null) return; 
		if(userImage!=null && userImage.trim().length()!=0) {
			if(sessionPictureManager==null) register(sessionId, user, userImage);
			else if(sessionPictureManager!=null && !sessionPictureManager.getName().contentEquals(userImage)) 
				register(sessionId, user, userImage);
			
		}
	}
}
