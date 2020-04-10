package webapp.debt.tracker.dto;
// default package

// Generated Mar 10, 2020 7:37:35 PM by Hibernate Tools 5.4.12.Final

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class DebtPaymentDetailDTO extends BaseDTO implements java.io.Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Integer debtPaymentDetailId;
	private Integer debtInfoId;
	private Double periodicPaymentAmount;
	private LocalDate estimatedPaymentDate;
	private LocalDate actualPaymentDate;
	private String paymentStatus;

	public DebtPaymentDetailDTO() {
	}

	public DebtPaymentDetailDTO(Integer debtPaymentDetailId, Integer debtInfoId, Double periodicPaymentAmount,
			LocalDate estimatedPaymentDate, LocalDate actualPaymentDate, String paymentStatus) {
		this.debtPaymentDetailId = debtPaymentDetailId;
		this.debtInfoId =  debtInfoId;
		this.periodicPaymentAmount = periodicPaymentAmount;
		this.estimatedPaymentDate = estimatedPaymentDate;
		this.actualPaymentDate = actualPaymentDate;
		this.paymentStatus = paymentStatus;
	}


	public Integer getDebtPaymentDetailId() {
		return this.debtPaymentDetailId;
	}

	public void setDebtPaymentDetailId(Integer debtPaymentDetailId) {
		this.debtPaymentDetailId = debtPaymentDetailId;
	}

	public Integer getDebtInfoId() {
		return debtInfoId;
	}

	public void setDebtInfoId(Integer debtInfoId) {
		this.debtInfoId = debtInfoId;
	}
	
	public Double getPeriodicPaymentAmount() {
		return this.periodicPaymentAmount;
	}

	public void setPeriodicPaymentAmount(Double periodicPaymentAmount) {
		this.periodicPaymentAmount = periodicPaymentAmount;
	}

	public LocalDate getEstimatedPaymentDate() {
		return this.estimatedPaymentDate;
	}

	public void setEstimatedPaymentDate(LocalDate estimatedPaymentDate) {
		this.estimatedPaymentDate = estimatedPaymentDate;
	}

	public LocalDate getActualPaymentDate() {
		return this.actualPaymentDate;
	}

	public void setActualPaymentDate(LocalDate actualPaymentDate) {
		this.actualPaymentDate = actualPaymentDate;
	}

	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((debtPaymentDetailId == null) ? 0 : debtPaymentDetailId.hashCode());
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
		DebtPaymentDetailDTO other = (DebtPaymentDetailDTO) obj;
		if (debtPaymentDetailId == null) {
			if (other.debtPaymentDetailId != null)
				return false;
		} else if (!debtPaymentDetailId.equals(other.debtPaymentDetailId))
			return false;
		return true;
	}

	
	


	
	

}
