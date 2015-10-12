package fr.treeptik.conf;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	 
	
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	        if (roles.contains("ROLE_USER")){
	            try {
					response.sendRedirect("/ProjetPetitDejeuner/utilisateur/petitdej/list.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        if (roles.contains("ROLE_ADMIN")){
	            try {
					response.sendRedirect("/ProjetPetitDejeuner/admin/index.html");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	    }


	
}