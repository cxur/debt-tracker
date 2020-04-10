package webapp.debt.tracker.service;

import java.util.List;

import webapp.debt.tracker.model.DebtorInfo;

public interface DebtorInfoService {
	
	DebtorInfo saveDebtorInfo(DebtorInfo debtorInfo);
	DebtorInfo updateDebtorInfo(DebtorInfo debtorInfo);
	DebtorInfo getDebtorInfoById(Integer debtorInfoId);
//	List<DebtorInfo> getAllDebtorsByAppUserId(Integer appUserId);
}