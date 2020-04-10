package webapp.debt.tracker;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import webapp.debt.tracker.controller.ViewController;
import webapp.debt.tracker.model.AppUser;
import webapp.debt.tracker.model.DebtInfo;
import webapp.debt.tracker.model.DebtorInfo;
import webapp.debt.tracker.security.config.CustomOAuthService;
import webapp.debt.tracker.security.config.CustomUserDetails;
import webapp.debt.tracker.security.config.UserDetailsSpring;
import webapp.debt.tracker.service.AppUserService;
import webapp.debt.tracker.service.DebtInfoService;
import webapp.debt.tracker.service.DebtPaymentDetailsService;
import webapp.debt.tracker.service.DebtorInfoService;
import webapp.debt.tracker.utils.UtilityMethods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@WebAppConfiguration
@WebMvcTest(ViewController.class)
public class ViewControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	DebtInfoService debtInfoService;
	@MockBean
	DebtPaymentDetailsService debtPaymentDetailsService;
	@MockBean
	DebtorInfoService debtorInfoService;
	@MockBean
	AppUserService appUserService;
	@MockBean
	UtilityMethods utilityMethods;
	@MockBean
	UserDetailsSpring userDetailsSpring;
	@MockBean
	CustomOAuthService customOAuthService;
	@MockBean
	ClientRegistrationRepository clientRegistrationRepository;
//	
	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void shouldCallLoginFormTest() throws Exception {

		this.mockMvc.perform(get("/loginForm")).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("signInSignUp")).andExpect(model().attributeExists("appUserData"));
	}

	@Test
	public void shouldRedirectToLoginUnAuthenticated() throws Exception {
		this.mockMvc.perform(get("/getTable")).andDo(print()).andExpect(status().isFound())
				.andExpect(redirectedUrl("http://localhost/loginForm"));
	}

	@Test
	@WithMockUser(username = "user1", password = "pwd", roles = "USER")
	public void shouldAccessGetTableAuthenticated() throws Exception {
		this.mockMvc.perform(get("/getTable")).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("debtInfoTable")).andExpect(model().attributeExists("debtorInfo"))
				.andExpect(model().attributeExists("debtInfo")).andExpect(model().attributeExists("countryList"))
				.andExpect(model().attributeExists("currencyList"));
	}

	@Test
	
	public void shouldGetDebtorsData() throws Exception {
		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		CustomUserDetails customUser = new CustomUserDetails(1, "userTest@test.com", "12345", authorities, null);
		AppUser appUserData = new AppUser(1, "user1", "user", "test", "userTest@test.com", "NI");
		DebtorInfo debtorData = new DebtorInfo(1, "John", "Doe", "john@doe.com", "USA");
		DebtInfo mockDebtInfo = new DebtInfo(1, appUserData, debtorData, 10.0, "USD", 1, "Monthly");
		DebtInfo mockDebtInfo2 = new DebtInfo(2, appUserData, debtorData, 100.0, "USD", 1, "Weekly");

		List<DebtInfo> debtInfoList = new ArrayList<DebtInfo>();
		debtInfoList.add(mockDebtInfo);
		debtInfoList.add(mockDebtInfo2);

		when(debtInfoService.getDebtInfoByAppUserId(1)).thenReturn(debtInfoList);

		this.mockMvc.perform(get("/getDebtorsData").with(user(customUser)).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(1)));
		
		verify(debtInfoService, times(1)).getDebtInfoByAppUserId(1);
		verifyNoMoreInteractions(debtInfoService);

	}

}
