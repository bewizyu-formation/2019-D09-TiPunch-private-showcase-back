package fr.formation.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This service manages the JWT token authentication and storage.
 */
public class AuthenticationService {

	/**
	 * Add a JWT to the current response as a header with name {@link SecurityConstants.HEADER_STRING} and value {@link
	 * SecurityConstants.TOKEN_PREFIX}*+ the token hashed with args : {@link SignatureAlgorithm.HS512}, {@link
	 * SecurityConstants.SECRET}* which value must be given through an env var at application building
	 *
	 * @param res      the res
	 * @param username the username
	 * @param roles    the roles
	 *
	 * @throws IOException the io exception
	 */
	static void addAuthentication(HttpServletResponse res, String username,
																Collection<? extends GrantedAuthority> roles) throws IOException {
		List<String> rolesString = roles.parallelStream().map(roleValue -> roleValue.getAuthority())
				.collect(Collectors.toList());

		String JWT = Jwts.builder().setSubject(username).claim("roles", rolesString)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET).compact();
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + " " + JWT);
	}

	/**
	 * Extract the current {@link Authentication} from the incoming request
	 *
	 * @param request the request
	 *
	 * @return authentication authentication
	 */
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {
			// parse the token.
			Claims claims = Jwts.parser()
					.setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
					.getBody();

			String user = claims.getSubject();
			Collection<String> rolesString = claims.get(SecurityConstants.CLAIM_ROLES, Collection.class);

			Collection<GrantedAuthority> roles = rolesString.parallelStream()
					.map(roleValue -> new SimpleGrantedAuthority(roleValue))
					.collect(Collectors.toList());

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, roles) : null;
		}
		return null;
	}
}
