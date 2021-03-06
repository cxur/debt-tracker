package webapp.debt.tracker.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import webapp.debt.tracker.model.AppUser;
import webapp.debt.tracker.repository.AppUserRepository;
import webapp.debt.tracker.security.config.CustomUserDetails;

/**
 * Generated by Spring Data Generator on 10/03/2020
 */
@Service
public class AppUserServiceImpl implements AppUserService {

	private AppUserRepository appUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public AppUserServiceImpl(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.appUserRepository = appUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}


	@Override
	public AppUser getAppUserById(Integer appUserId) {
		return appUserRepository.findById(appUserId).get();
	}

	@Override
	public AppUser saveAppUser(AppUser appUserData) {
		appUserData.setPassword(bCryptPasswordEncoder.encode(appUserData.getPassword()));
		return appUserRepository.save(appUserData);
	}

	@Override
	public Optional<AppUser> findByUserName(String userName) {
		// TODO Auto-generated method stub
		return appUserRepository.findByUserName(userName);
	}

	@Override
	public Optional<AppUser> findByEmailAddress(String userEmail) {
		// TODO Auto-generated method stub
		return appUserRepository.findByEmailAddress(userEmail);
	}

}
