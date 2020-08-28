package programiranje.yi.user.app.services.client;

import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

/**
 * Пријем профилне слике која се веже за корисников избор. 
 * @author mirko
 * @version 1.0
 */
public class ProfileImageAdapter {
	private SessionClient sessionClient; 
	
	public SessionClient getSessionClient() {
		return sessionClient;
	}

	public ProfileImageAdapter setSessionClient(SessionClient sessionClient) {
		this.sessionClient = sessionClient;
		return this;
	}
	
	public byte[] getImage(String profileService) {
		 try {
				URL url = new URL(profileService); 

				HttpPost httpPost = new HttpPost(url.toString());
				CloseableHttpResponse httpResponse = sessionClient.getHttpClient().execute(httpPost); 
				
				InputStream is = httpResponse.getEntity().getContent(); 
				byte[] content = IOUtils.toByteArray(is);
				is.close();
				
				return content; 
		 }catch(RuntimeException ex) {
			throw ex;  
		 }catch(Exception ex){
			 throw new RuntimeException(ex);
		 }
	}
}
