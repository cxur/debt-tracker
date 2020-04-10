package webapp.debt.tracker.dto;
// default package

import java.util.Arrays;

// Generated Mar 10, 2020 7:37:35 PM by Hibernate Tools 5.4.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


public class AppUserDTO extends BaseDTO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Integer appUserId;
	private String userName;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String country;
	private String password;
	private byte[] profilePic;
	
	public AppUserDTO() {
	}

	public AppUserDTO(Integer appUserId, String userName, String firstName, String lastName, String emailAddress,
			String country) {
		this.appUserId = appUserId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.country = country;
	}

	public AppUserDTO(Integer appUserId, String userName, String firstName, String lastName, String emailAddress,
			String country, byte[] profilePic) {
		this.appUserId = appUserId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.country = country;
		this.profilePic = profilePic;
	}

	public Integer getAppUserId() {
		return this.appUserId;
	}

	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getProfilePic() {
		return this.profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appUserId == null) ? 0 : appUserId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUserDTO other = (AppUserDTO) obj;
		if (appUserId == null) {
			if (other.appUserId != null)
				return false;
		} else if (!appUserId.equals(other.appUserId))
			return false;
		return true;
	}



}
