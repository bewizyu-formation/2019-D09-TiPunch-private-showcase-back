package fr.formation.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * This filter intercepts request for login and if username/password are correct, will generate a token with :
 * username,password,roles
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	/**
	 * Instantiates a new Jwt login filter.
	 *
	 * @param url         the url
	 * @param authManager the auth manager
	 */
	public JwtLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	/**
	 * Perform authentication of the user from request values
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {

		Credentials creds = null;

		if (!StringUtils.isEmpty(req.getParameter("username")) && !StringUtils.isEmpty(req.getParameter("password"))) {
			creds = new Credentials();
			creds.setUsername(req.getParameter("username"));
			creds.setPassword(req.getParameter("password"));
		} else {

			creds = new ObjectMapper().readValue(req.getInputStream(), Credentials.class);
		}
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
				creds.getPassword(), Collections.emptyList()));
	}

	/**
	 * When a successfulAuthentication is made, this method can add the authentication to the response
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
																					Authentication auth) throws IOException, ServletException {
		AuthenticationService.addAuthentication(res, auth.getName(), auth.getAuthorities());
	}

}
