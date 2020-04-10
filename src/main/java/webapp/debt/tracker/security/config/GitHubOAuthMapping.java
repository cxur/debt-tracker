package webapp.debt.tracker.security.config;

import java.util.Map;


public class GitHubOAuthMapping extends UserOauthData {

	public GitHubOAuthMapping(Map<String, Object> dataAttributes) {
		super(dataAttributes);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return dataAttributes.get("id").toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return dataAttributes.get("name").toString();
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return dataAttributes.get("email").toString();
	}

	@Override
	public String getImageURL() {
		// TODO Auto-generated method stub
		return dataAttributes.get("avatar_url").toString();
	}

}
