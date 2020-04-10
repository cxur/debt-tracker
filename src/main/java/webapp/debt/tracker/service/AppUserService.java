package webapp.debt.tracker.service;

import java.util.Optional;

import webapp.debt.tracker.model.AppUser;

public interface AppUserService {

	AppUser getAppUserById(Integer appUserId);
	AppUser saveAppUser(AppUser appUserData);
	Optional<AppUser> findByUserName(String userName);
	Optional<AppUser> findByEmailAddress(String userEmail);

}