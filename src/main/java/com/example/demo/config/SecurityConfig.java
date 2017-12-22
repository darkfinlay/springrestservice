package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.repository.UserJPARepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserJPARepository.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	public UserRepositoryAuthProvider userRepoAuthProvider;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		configureUrlAuthorization(http);

		// Disable CSRF protection (it is difficult to implement with ng2)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {
		});
	}
	
	

	  protected void configureUrlAuthorization(HttpSecurity http) throws Exception {
	    http
	      .csrf().disable()
	      .authorizeRequests()
	        .antMatchers(HttpMethod.POST, "/users/**").authenticated()
	        .antMatchers(HttpMethod.PUT, "/users/**").authenticated()
	        .antMatchers(HttpMethod.DELETE, "/users/**").authenticated()
	        .antMatchers(HttpMethod.POST, "/weapon/**").authenticated()
	        .antMatchers(HttpMethod.PUT, "/weapon/**").authenticated()
	        .antMatchers(HttpMethod.DELETE, "/weapon/**").authenticated()
	        .antMatchers(HttpMethod.POST, "/table/**").authenticated()
	        .antMatchers(HttpMethod.PUT, "/table/**").authenticated()
	        .antMatchers(HttpMethod.DELETE, "/table/**").authenticated()
	        .antMatchers(HttpMethod.POST, "/team/**").authenticated()
	        .antMatchers(HttpMethod.PUT, "/team/**").authenticated()
	        .antMatchers(HttpMethod.DELETE, "/team/**").authenticated()
	        .antMatchers(HttpMethod.POST, "/session/**").authenticated()
	        .antMatchers(HttpMethod.PUT, "/session/**").authenticated()
	        .antMatchers(HttpMethod.DELETE, "/session/**").authenticated()
	        .antMatchers(HttpMethod.POST, "/room/**").authenticated()
	        .antMatchers(HttpMethod.PUT, "/room/**").authenticated()
	        .antMatchers(HttpMethod.DELETE, "/room/**").authenticated()
	        .anyRequest().permitAll();
	        
	   
	  }
	
	  
	  @Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			// Database authentication provider
			auth.authenticationProvider(userRepoAuthProvider);
		}
	 
	
}
