package webapp.debt.tracker.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsSpring userDetailsSpring;

	@Autowired
	private CustomOAuthService customOAuthService;

//	@Autowired
//	private OAuthSuccessHandler oAuthSuccessHandler;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("{noop}pass") 
//				.roles("USER");
		authenticationManagerBuilder.userDetailsService(userDetailsSpring).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().formLogin().loginPage("/loginForm").defaultSuccessUrl("/dashboard")
				.loginProcessingUrl("/process-login").failureUrl("/loginError").and().httpBasic().disable()
				.authorizeRequests(a -> a.antMatchers("/loginForm", "/loginError", "/process-login","/signUpUser").permitAll()
						.antMatchers("/", "/error", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg",
								"/**/*.html", "/**/*.css", "/**/*.js")
						.permitAll().anyRequest().authenticated())
				.oauth2Login().redirectionEndpoint().baseUri("/loginForm/authorize/callback").and()
				.userInfoEndpoint().userService(customOAuthService);
	}

}
