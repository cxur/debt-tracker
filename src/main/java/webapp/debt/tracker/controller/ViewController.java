package webapp.debt.tracker.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import webapp.debt.tracker.dto.AppUserDTO;
import webapp.debt.tracker.dto.DebtInfoDTO;
import webapp.debt.tracker.dto.DebtPaymentDetailDTO;
import webapp.debt.tracker.dto.DebtorInfoDTO;
import webapp.debt.tracker.model.AppUser;
import webapp.debt.tracker.model.DebtInfo;
import webapp.debt.tracker.model.DebtPaymentDetail;
import webapp.debt.tracker.model.DebtorInfo;
import webapp.debt.tracker.security.config.CustomUserDetails;
import webapp.debt.tracker.service.AppUserService;
import webapp.debt.tracker.service.DebtInfoService;
import webapp.debt.tracker.service.DebtPaymentDetailsService;
import webapp.debt.tracker.service.DebtorInfoService;
import webapp.debt.tracker.utils.UtilityMethods;

@Controller
public class ViewController {

	DebtInfoService debtInfoService;
	DebtPaymentDetailsService debtPaymentDetailsService;
	DebtorInfoService debtorInfoService;
	AppUserService appUserService;
	UtilityMethods utilityMethods;

	public ViewController(DebtInfoService debtInfoService, DebtPaymentDetailsService debtPaymentDetailsService,
			DebtorInfoService debtorInfoService, AppUserService appUserService, UtilityMethods utilityMethods) {
		this.debtInfoService = debtInfoService;
		this.debtPaymentDetailsService = debtPaymentDetailsService;
		this.debtorInfoService = debtorInfoService;
		this.appUserService = appUserService;
		this.utilityMethods = utilityMethods;
	}

	@GetMapping("/loginForm")
	public String getLoginPage(Model model) {
		model.addAttribute("appUserData",new AppUserDTO());
		return "signInSignUp";
	}

	@GetMapping("/loginError")
	public String getLoginErrorPage(Model model) {
		model.addAttribute("loginError", true);
		System.out.println("entering");
		return "signInSignUp";
	}

	@GetMapping("/getTable")
	public String getTable(Model model) {
		String[] locales = Locale.getISOCountries();
		Set<Currency> currencySet = Currency.getAvailableCurrencies();

		List<String> currencyOrderList = currencySet.stream().map(action -> action.getCurrencyCode())
				.collect(Collectors.toList());

		List<String> countryList = new ArrayList<String>();

		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countryList.add(obj.getDisplayCountry());
		}

		model.addAttribute("debtorInfo", new DebtorInfoDTO());
		model.addAttribute("debtInfo", new DebtInfoDTO());
		model.addAttribute("countryList", countryList);
		model.addAttribute("currencyList", currencyOrderList);

		return "debtInfoTable";
	}

	@GetMapping(path = "/getDebtorsData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Set<DebtorInfoDTO>> getDebtorsData(Authentication authentication) {
		CustomUserDetails loggedInUserDetails = (CustomUserDetails) authentication.getPrincipal();
		List<DebtInfo> DebtsListByAppUserId = debtInfoService
				.getDebtInfoByAppUserId(loggedInUserDetails.getAppUserId());
		Set<DebtorInfo> DebtorsListByAppUserId = DebtsListByAppUserId.stream().map(action -> action.getDebtorInfo())
				.collect(Collectors.toSet());
		Set<DebtorInfoDTO> DebtorInfoDTOset = new HashSet<DebtorInfoDTO>();

		DebtorsListByAppUserId.forEach(action -> DebtorInfoDTOset.add(action.toDTO(DebtorInfoDTO.class)));

		return new ResponseEntity<>(DebtorInfoDTOset, HttpStatus.OK);
	}

	@GetMapping("/addNewDebtInfo")
	public String addDebtInfo(Model model) {
		String[] locales = Locale.getISOCountries();
		Set<Currency> currencySet = Currency.getAvailableCurrencies();

		List<String> currencyOrderList = currencySet.stream().map(action -> action.getCurrencyCode())
				.collect(Collectors.toList());

		List<String> countryList = new ArrayList<String>();

		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countryList.add(obj.getDisplayCountry());
		}

		model.addAttribute("debtorInfo", new DebtorInfoDTO());
		model.addAttribute("debtInfo", new DebtInfoDTO());
		model.addAttribute("countryList", countryList);
		model.addAttribute("currencyList", currencyOrderList);

		return "/debtInfoForm";

	}

	@PostMapping("/saveDebtInfo")
	public String addDebtInfo(@ModelAttribute("debtorInfoDTO") DebtorInfoDTO debtorInfoDTO,
			@ModelAttribute("debtInfoDTO") DebtInfoDTO debtInfoDTO, RedirectAttributes redirectAttributes,
			Authentication authentication) {

		CustomUserDetails loggedInUserDetails = (CustomUserDetails) authentication.getPrincipal();
		DebtorInfo debtorInfoEntity = new DebtorInfo();
		DebtInfo debtInfoEntity = new DebtInfo();

		if (debtorInfoDTO.getPictureUpload() != null) {
//			try {
//				debtorInfoDTO.setDebtorPicture(debtorInfoDTO.getPictureUpload().getBytes());
			debtorInfoDTO.setDebtorPicture(new String().getBytes());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

		debtorInfoEntity = debtorInfoDTO.toEntity(DebtorInfo.class);

		debtInfoEntity = debtInfoDTO.toEntity(DebtInfo.class);
		debtInfoEntity.setDebtorInfo(debtorInfoEntity);
		debtInfoEntity.setAppUser(appUserService.getAppUserById(loggedInUserDetails.getAppUserId()));

		Double periodicPaymentsAmount = debtInfoEntity.getDebtAmount() / debtInfoEntity.getNumberOfPayments();

		long daysToIncrement = 0;
		switch (debtInfoEntity.getPaymentFrequencyUnit().toLowerCase()) {

		case "weekly":
			daysToIncrement = 7;
			break;

		case "biweekly":
			daysToIncrement = 15;
			break;

		case "monthly":
			daysToIncrement = 30;
			break;

		default:
			break;
		}

		LocalDate PayDates = LocalDate.now().plusDays(daysToIncrement);
		for (int i = 0; i < debtInfoEntity.getNumberOfPayments(); i++) {
			DebtPaymentDetail debtPaymentDetail = new DebtPaymentDetail();
			debtPaymentDetail = setPaymentDetails(debtInfoEntity, periodicPaymentsAmount, PayDates);
			debtPaymentDetailsService.saveDebtPaymentDetail(debtPaymentDetail, debtInfoEntity, debtorInfoEntity);
			PayDates = PayDates.plusDays(daysToIncrement);
		}

		redirectAttributes.addFlashAttribute("message", "Success");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/addNewDebtInfo";

	}

	public DebtPaymentDetail setPaymentDetails(DebtInfo debtInfo, Double periodicPaymentsAmount, LocalDate PayDates) {
		DebtPaymentDetail debtPaymentDetail = new DebtPaymentDetail();
		debtPaymentDetail.setDebtInfo(debtInfo);
		debtPaymentDetail.setPeriodicPaymentAmount(periodicPaymentsAmount);
		debtPaymentDetail.setEstimatedPaymentDate(PayDates);
		debtPaymentDetail.setPaymentStatus("pending");

		return debtPaymentDetail;

	}

	@GetMapping("/getDebts")
	public List<DebtInfoDTO> getDebts(Authentication authentication) {

		CustomUserDetails loggedInUserDetails = (CustomUserDetails) authentication.getPrincipal();
		List<DebtInfoDTO> debtInfoList = new ArrayList<DebtInfoDTO>();
		debtInfoService.getDebtInfoByAppUserId(loggedInUserDetails.getAppUserId())
				.forEach(action -> debtInfoList.add(action.toDTO(DebtInfoDTO.class)));
		return debtInfoList;

	}

	@PostMapping("/updateDebtorInfo")
	public String updateDebtorInfo(@ModelAttribute("debtorInfoDTO") DebtorInfoDTO debtorInfoDTO) {

		if (debtorInfoDTO.getPictureUpload().getOriginalFilename() != null
				&& !debtorInfoDTO.getPictureUpload().getOriginalFilename().isEmpty()) {
			try {
				debtorInfoDTO.setDebtorPicture(debtorInfoDTO.getPictureUpload().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				debtorInfoDTO.setDebtorPicture(
						Base64.getDecoder().decode(new String(debtorInfoDTO.getDebtorPicture()).getBytes("UTF-8")));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		debtorInfoService.updateDebtorInfo(debtorInfoDTO.toEntity(DebtorInfo.class));

		return "redirect:/getTable";
	}

	@GetMapping("/debtPaymentDetails/{debtorId}")
	public String getPaymentDetailsSection(@PathVariable("debtorId") Integer debtorId, Model model) {

		List<DebtInfo> debtInfoListByDebtorId = debtInfoService.getDebtInfoByDebtorId(debtorId);
		Map<DebtInfo, List<DebtPaymentDetail>> debtPaymentDetailGroupBydebtInfoId = new HashMap<DebtInfo, List<DebtPaymentDetail>>();
		DebtorInfo debtorInfo = debtorInfoService.getDebtorInfoById(debtorId);
		debtorInfo.setPictureBase64ToString(Base64.getEncoder().encodeToString(
				(debtorInfo.getDebtorPicture() == null ? "".getBytes() : debtorInfo.getDebtorPicture())));

		for (DebtInfo debtInfo : debtInfoListByDebtorId) {
			debtPaymentDetailGroupBydebtInfoId.put(debtInfo,
					debtPaymentDetailsService.getDebtPaymentDetailsByDebtInfoId(debtInfo.getDebtInfoId()));
		}

		Map<DebtInfoDTO, List<DebtPaymentDetailDTO>> dto = new HashMap<DebtInfoDTO, List<DebtPaymentDetailDTO>>();
		debtPaymentDetailGroupBydebtInfoId.forEach((k, v) -> {
			dto.put(k.toDTO(DebtInfoDTO.class),
					v.stream().map(action -> action.toDTO(DebtPaymentDetailDTO.class)).collect(Collectors.toList()));
		});

//		String lendMoneyGroupedByCurrencyJSON = null;
//		try {
//			lendMoneyGroupedByCurrencyJSON = new ObjectMapper().writerWithDefaultPrettyPrinter()
//					.writeValueAsString(dto);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		model.addAttribute("debtorInfo", debtorInfo.toDTO(DebtorInfoDTO.class));
		model.addAttribute("debtPaymentInfo", dto);

		return "debtPaymentDetails";
	}

	@GetMapping("/dashboard")
	public String getStatistics(Model model, Authentication authentication) {
		CustomUserDetails loggedInUserDetails = (CustomUserDetails) authentication.getPrincipal();

		/** Number of debtor per user **/
		Integer numberOfDebtors = debtInfoService.getNumberOfDebtorsByAppUserId(loggedInUserDetails.getAppUserId());

		/** Number of loans given **/
		List<DebtInfo> debtInfoList = debtInfoService.getDebtInfoByAppUserId(loggedInUserDetails.getAppUserId());

		/** Number of loans given categorized by currency **/
		Map<String, Double> lendMoneyGroupedByCurrency = debtInfoList.stream().collect(Collectors
				.groupingBy(DebtInfo::getDebtCurrency, Collectors.summingDouble(mapper -> mapper.getDebtAmount())));

		String lendMoneyGroupedByCurrencyJSON = null;
		try {
			lendMoneyGroupedByCurrencyJSON = new ObjectMapper().writerWithDefaultPrettyPrinter()
					.writeValueAsString(lendMoneyGroupedByCurrency);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Map<Integer, Map<String, Long>> debtPaymentGroupByStatus = new HashMap<Integer, Map<String, Long>>();
//
//		for (DebtInfo debtInfo : debtInfoList) {
//			debtPaymentGroupByStatus.putAll(debtInfo.getDebtPaymentDetails().stream()
//					.collect(Collectors.groupingBy(e -> e.getDebtInfo().getDebtInfoId(),
//							Collectors.groupingBy(DebtPaymentDetail::getPaymentStatus, Collectors.counting()))));
//		}

		List<DebtPaymentDetail> loansPaymentPassPaymentDateAndPending = debtPaymentDetailsService
				.getDebtPaymentPassPaymentDateAndPending(loggedInUserDetails.getAppUserId());
		List<DebtPaymentDetail> loansPaymentPassPaymentDateAndPaid = debtPaymentDetailsService
				.getDebtPaymentPassPaymentDateAndPaid(loggedInUserDetails.getAppUserId());

		model.addAttribute("numberOfDebtors", numberOfDebtors);
		model.addAttribute("numberGivenLoans", debtInfoList.size());
		model.addAttribute("lendMoneyGroupedByCurrency", lendMoneyGroupedByCurrencyJSON);
		model.addAttribute("loansPaymentPassPaymentDateAndPending", loansPaymentPassPaymentDateAndPending.size());
		model.addAttribute("loansPaymentPassPaymentDateAndPaid", loansPaymentPassPaymentDateAndPaid.size());
		return "dashboard";
	}

	@PostMapping("/signUpUser")
	public String signUpUser(@ModelAttribute("appUserData") AppUserDTO appUserData,
			RedirectAttributes redirectAttributes) {

		AppUser newAppUser = new AppUser();
		appUserData.setUserName(appUserData.getEmailAddress());
		newAppUser = appUserData.toEntity(AppUser.class);
		
		appUserService.saveAppUser(newAppUser);

		redirectAttributes.addFlashAttribute("signUpMessage", "Your account has been created successfully!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/loginForm";

	}

}
