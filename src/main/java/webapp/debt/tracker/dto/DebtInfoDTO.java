package webapp.debt.tracker.dto;

public class DebtInfoDTO extends BaseDTO implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer debtInfoId;
	private Integer appUserId;
	private Integer debtorId;
	private Double debtAmount;
	private String debtCurrency;
	private Integer numberOfPayments;
	private String paymentFrequencyUnit;
	private String debtConcept;
	private String debtComments;
	
	public DebtInfoDTO() {
	}

	public DebtInfoDTO(Integer debtInfoId, Integer appUserId, Integer debtorId, Double debtAmount, String debtCurrency,
			Integer numberOfPayments, String paymentFrequencyUnit) {
		this.debtInfoId = debtInfoId;
		this.appUserId = appUserId;
		this.debtorId = debtorId;
		this.debtAmount = debtAmount;
		this.debtCurrency = debtCurrency;
		this.numberOfPayments = numberOfPayments;
		this.paymentFrequencyUnit = paymentFrequencyUnit;
	}

	public DebtInfoDTO(Integer debtInfoId, Integer appUserId, Integer debtorId, Double debtAmount, String debtCurrency,
			Integer numberOfPayments, String paymentFrequencyUnit, String debtConcept, String debtComments) {
		this.debtInfoId = debtInfoId;
		this.appUserId = appUserId;
		this.debtorId = debtorId;
		this.debtAmount = debtAmount;
		this.debtCurrency = debtCurrency;
		this.numberOfPayments = numberOfPayments;
		this.paymentFrequencyUnit = paymentFrequencyUnit;
		this.debtConcept = debtConcept;
		this.debtComments = debtComments;
	}

	public Integer getDebtInfoId() {
		return this.debtInfoId;
	}

	public void setDebtInfoId(Integer debtInfoId) {
		this.debtInfoId = debtInfoId;
	}

	public Integer getAppUser() {
		return this.appUserId;
	}

	public void setAppUser(Integer appUserId) {
		this.appUserId = appUserId;
	}

	public Integer getDebtorInfo() {
		return this.debtorId;
	}

	public void setDebtorInfo(Integer debtorId) {
		this.debtorId = debtorId;
	}

	public Double getDebtAmount() {
		return this.debtAmount;
	}

	public void setDebtAmount(Double debtAmount) {
		this.debtAmount = debtAmount;
	}

	public String getDebtCurrency() {
		return this.debtCurrency;
	}

	public void setDebtCurrency(String debtCurrency) {
		this.debtCurrency = debtCurrency;
	}

	public Integer getNumberOfPayments() {
		return this.numberOfPayments;
	}

	public void setNumberOfPayments(Integer numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}

	public String getPaymentFrequencyUnit() {
		return this.paymentFrequencyUnit;
	}

	public void setPaymentFrequencyUnit(String paymentFrequencyUnit) {
		this.paymentFrequencyUnit = paymentFrequencyUnit;
	}

	public String getDebtConcept() {
		return debtConcept;
	}

	public void setDebtConcept(String debtConcept) {
		this.debtConcept = debtConcept;
	}

	public String getDebtComments() {
		return debtComments;
	}

	public void setDebtComments(String debtComments) {
		this.debtComments = debtComments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((debtInfoId == null) ? 0 : debtInfoId.hashCode());
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
		DebtInfoDTO other = (DebtInfoDTO) obj;
		if (debtInfoId == null) {
			if (other.debtInfoId != null)
				return false;
		} else if (!debtInfoId.equals(other.debtInfoId))
			return false;
		return true;
	}

	
	

}
