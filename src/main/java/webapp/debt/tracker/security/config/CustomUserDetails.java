package webapp.debt.tracker.security.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import webapp.debt.tracker.model.AppUser;

public class CustomUserDetails implements OAuth2User, UserDetails {

	private static final long serialVersionUID = -1918480326770923012L;

	private Integer appUserId;
	private String emailAddress;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;

	public static CustomUserDetails createAppUser(AppUser appUser) {
		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

		return new CustomUserDetails(appUser.getAppUserId(), appUser.getEmailAddress(), appUser.getPassword(),
				authorities, null);
	}

	public static CustomUserDetails createOAuthAppUser(AppUser appUser, Map<String, Object> attributes) {
		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

		return new CustomUserDetails(appUser.getAppUserId(), appUser.getEmailAddress(), appUser.getPassword(),
				authorities, attributes);
	}

	public CustomUserDetails(Integer appUserId, String emailAddress, String password,
			Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes) {
		super();
		this.appUserId = appUserId;
		this.emailAddress = emailAddress;
		this.password = password;
		this.authorities = authorities;
		this.attributes = attributes;
	}

	public Integer getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public Map<String, Object> setAttributes(Map<String, Object> attributes) {
		// TODO Auto-generated method stub
		return this.attributes = attributes;
	}

	public Collection<? extends GrantedAuthority> setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		// TODO Auto-generated method stub
		return this.authorities = authorities;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return emailAddress;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
