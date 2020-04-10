package webapp.debt.tracker.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import webapp.debt.tracker.model.AppUser;
import webapp.debt.tracker.repository.AppUserRepository;

@Service
public class CustomOAuthService extends DefaultOAuth2UserService {

	@Autowired
	AppUserRepository appUserRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauthUser = super.loadUser(userRequest);
		
		UserOauthData userOauthData = new GitHubOAuthMapping(oauthUser.getAttributes());
		System.out.println(userOauthData.getEmail());
		
		AppUser appUserData = appUserRepository.findByEmailAddress(userOauthData.getEmail()).get();
		
		return CustomUserDetails.createOAuthAppUser(appUserData, oauthUser.getAttributes());
	}
}
