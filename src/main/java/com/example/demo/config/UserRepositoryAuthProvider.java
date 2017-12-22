package com.example.demo.config;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.Session;
import com.example.demo.model.Users;
import com.example.demo.repository.SessionJPARepository;
import com.example.demo.repository.UserJPARepository;

@Component
public class UserRepositoryAuthProvider implements AuthenticationProvider {

	@Autowired
	private UserJPARepository userRepository;

	@Autowired
	private SessionJPARepository sessionRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		
		System.out.println("Username:" + username);
		System.out.println("Password" + password);

	    Session session = sessionRepository.findByUserName(username);
 

		if (session  != null) {

		System.out.println("ingreso");
			List<GrantedAuthority> roles = new ArrayList<>();
			/*for (String role : user.getRoles()) {
				roles.add(new SimpleGrantedAuthority(role));
			}*/

			return new UsernamePasswordAuthenticationToken(username, password, roles);
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authenticationObject) {
		return true;
	}
}