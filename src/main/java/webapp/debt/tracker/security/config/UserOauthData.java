package webapp.debt.tracker.security.config;

import java.io.Serializable;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;

public abstract class UserOauthData {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> dataAttributes;
	public abstract String getId();
	public abstract String getName();
	public abstract String getEmail();
	public abstract String getImageURL();
	
	public UserOauthData(Map<String, Object> dataAttributes) {
		this.dataAttributes = dataAttributes;
	}
	
	 public Map<String, Object> getDataAttributes() {
	        return dataAttributes;
	    }
	
	
	
	

}
