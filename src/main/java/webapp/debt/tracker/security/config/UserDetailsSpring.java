package webapp.debt.tracker.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import webapp.debt.tracker.model.AppUser;
import webapp.debt.tracker.repository.AppUserRepository;

@Service
public class UserDetailsSpring implements UserDetailsService {

	@Autowired
	AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AppUser appUserInfo = appUserRepository.findByUserName(username).get();

		return CustomUserDetails.createAppUser(appUserInfo);
	}

}
