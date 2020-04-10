package webapp.debt.tracker.service;

import java.util.List;

import webapp.debt.tracker.model.DebtInfo;
import webapp.debt.tracker.model.DebtPaymentDetail;
import webapp.debt.tracker.model.DebtorInfo;

public interface DebtPaymentDetailsService {
	List<DebtPaymentDetail> getDebtPaymentDetailsByDebtInfoId(Integer debtInfoId);

	DebtPaymentDetail saveDebtPaymentDetail(DebtPaymentDetail debtPaymentDetail, DebtInfo debtInfo,
			DebtorInfo debtorInfo);

	DebtPaymentDetail updateDebtPaymentDetail(DebtPaymentDetail debtPaymentDetail);

	List<DebtPaymentDetail> getDebtPaymentPassPaymentDateAndPending(Integer appUserId);

	List<DebtPaymentDetail> getDebtPaymentPassPaymentDateAndPaid(Integer appUserId);
}