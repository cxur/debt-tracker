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
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class DebtorInfoDTO extends BaseDTO implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Integer debtorId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String country;
	private String phoneNumber;
	private String company;
	private byte[] debtorPicture;
	/*
	 * This field does not belong to the DB is use to store temporarily the image
	 * data coming from the frontend requesst
	 */
	private MultipartFile pictureUpload;
	/*
	 * This field does not belong to the DB is use to store the Decoded info of the
	 * image coming from the DB in order to be display in the frontend
	 */
	private String pictureBase64ToString;

	public DebtorInfoDTO() {
	}

	public DebtorInfoDTO(Integer debtorId, String firstName, String lastName, String emailAddress, String country) {
		this.debtorId = debtorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.country = country;
	}

	public DebtorInfoDTO(Integer debtorId, String firstName, String lastName, String emailAddress, String country,
			String phoneNumber, String company, byte[] debtorPicture, MultipartFile pictureUpload) {
		this.debtorId = debtorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.company = company;
		this.debtorPicture = debtorPicture;
	}

	public Integer getDebtorId() {
		return this.debtorId;
	}

	public void setDebtorId(Integer debtorId) {
		this.debtorId = debtorId;
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

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public byte[] getDebtorPicture() {
		return this.debtorPicture;
	}

	public void setDebtorPicture(byte[] debtorPicture) {
		this.debtorPicture = debtorPicture;
	}

	public MultipartFile getPictureUpload() {
		return pictureUpload;
	}

	public void setPictureUpload(MultipartFile pictureUpload) {
		this.pictureUpload = pictureUpload;
	}

	public String getPictureBase64ToString() {
		return pictureBase64ToString;
	}

	public void setPictureBase64ToString(String pictureBase64ToString) {
		this.pictureBase64ToString = pictureBase64ToString;
	}

	
	
	

	@Override
	public String toString() {
		return "DebtorInfoDTO [debtorId=" + debtorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", country=" + country + ", phoneNumber=" + phoneNumber
				+ ", company=" + company + ", debtorPicture=" + Arrays.toString(debtorPicture) + ", pictureUpload="
				+ pictureUpload + ", pictureBase64ToString=" + pictureBase64ToString + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((debtorId == null) ? 0 : debtorId.hashCode());
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
		DebtorInfoDTO other = (DebtorInfoDTO) obj;
		if (debtorId == null) {
			if (other.debtorId != null)
				return false;
		} else if (!debtorId.equals(other.debtorId))
			return false;
		return true;
	}

	

}
