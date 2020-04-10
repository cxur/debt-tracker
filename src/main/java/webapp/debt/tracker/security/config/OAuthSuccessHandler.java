//package webapp.debt.tracker.security.config;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.UriComponentsBuilder;
//
//@Component
//public class OAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {
//	
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		
//		System.out.println();
//	
////		while (request.getSession().getAttributeNames().hasMoreElements()) 
////            System.out.println("Value is: " + request.getSession().getAttributeNames().nextElement()); 
//		System.out.println(request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST").toString());
//		/*
//		 * Cookie[] cookiesRequestData = request.getCookies(); for (Cookie cookie :
//		 * cookiesRequestData) { System.out.println(cookie.getName()); if
//		 * (cookie.getName().equals("redirect_uri")) { redirectURI =
//		 * UriComponentsBuilder.fromUriString(cookie.getValue()) .build().toUriString();
//		 * 
//		 * 
//		 * } }
//		 */
//		
//		String referrer = request.getHeader("Referer");
//		String redirectURI = UriComponentsBuilder.fromUriString(request.getRequestURI()) .build().toUriString();
//		System.out.println(referrer);
////		clearAuthenticationAttributes(request, response);
//	getRedirectStrategy().sendRedirect(request, response, request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST").toString());
//	}
//	
//	 protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
//	        super.clearAuthenticationAttributes(request);
//	        
//	    }
//
//}
