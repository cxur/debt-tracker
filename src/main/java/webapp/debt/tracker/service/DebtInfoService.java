package webapp.debt.tracker.service;

import java.util.List;

import webapp.debt.tracker.model.DebtInfo;

public interface DebtInfoService {
	
	List<DebtInfo> getDebtInfoByAppUserId(Integer appUserId);
	List<DebtInfo> getDebtInfoByDebtorId(Integer debtorId);
	Integer getNumberOfDebtorsByAppUserId(Integer appUserId);
	DebtInfo saveDebtInfo(DebtInfo debtInfo);
	DebtInfo updateDebtInfo(DebtInfo debtInfo);
}