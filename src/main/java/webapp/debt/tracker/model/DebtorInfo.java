package webapp.debt.tracker.model;
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

/**
 * DebtorInfo generated by hbm2java
 */
@Entity
@Table(name = "debtor_info", schema = "debt_tracker")
public class DebtorInfo extends BaseEntity implements java.io.Serializable {

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
	private Set<DebtInfo> debtInfos = new HashSet<DebtInfo>(0);

	public DebtorInfo() {
	}

	public DebtorInfo(Integer debtorId, String firstName, String lastName, String emailAddress, String country) {
		this.debtorId = debtorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.country = country;
	}

	public DebtorInfo(Integer debtorId, String firstName, String lastName, String emailAddress, String country,
			String phoneNumber, String company, byte[] debtorPicture, MultipartFile pictureUpload,
			Set<DebtInfo> debtInfos) {
		this.debtorId = debtorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.company = company;
		this.debtorPicture = debtorPicture;
		this.debtInfos = debtInfos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debtorInfoSeq")
	@SequenceGenerator(name = "debtorInfoSeq", sequenceName = "debtor_id_seq", allocationSize = 1)
	@Column(name = "debtor_id", unique = true, nullable = false)
	public Integer getDebtorId() {
		return this.debtorId;
	}

	public void setDebtorId(Integer debtorId) {
		this.debtorId = debtorId;
	}

	@Column(name = "first_name", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 20)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email_address", nullable = false, length = 50)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name = "country", nullable = false, length = 50)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "phone_number", length = 50)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "company", length = 50)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "debtor_picture")
	public byte[] getDebtorPicture() {
		return this.debtorPicture;
	}

	public void setDebtorPicture(byte[] debtorPicture) {
		this.debtorPicture = debtorPicture;
	}

	@Transient
	public MultipartFile getPictureUpload() {
		return pictureUpload;
	}

	public void setPictureUpload(MultipartFile pictureUpload) {
		this.pictureUpload = pictureUpload;
	}

	@Transient
	public String getPictureBase64ToString() {
		return pictureBase64ToString;
	}

	public void setPictureBase64ToString(String pictureBase64ToString) {
		this.pictureBase64ToString = pictureBase64ToString;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "debtorInfo")
	 
	public Set<DebtInfo> getDebtInfos() {
		return this.debtInfos;
	}

	public void setDebtInfos(Set<DebtInfo> debtInfos) {
		this.debtInfos = debtInfos;
	}

	@Override
	public String toString() {
		return "DebtorInfo [debtorId=" + debtorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", country=" + country + ", phoneNumber=" + phoneNumber
				+ ", company=" + company + ", debtorPicture=" + Arrays.toString(debtorPicture) + ", pictureUpload="
				+ pictureUpload.getOriginalFilename() + ", pictureBase64ToString=" + pictureBase64ToString + "]";
	}
	
	
	

}
