package fr.formation.user;

import fr.formation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;

	private UserRoleRepository userRoleRepository;

	private PasswordEncoder passwordEncoder;

	/**
	 * Instantiates a new User service.
	 *
	 * @param userRepository     the user repository
	 * @param userRoleRepository the user role repository
	 * @param passwordEncoder the password encoder
	 */
	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}


	/**
	 * transform a list of roles (as {@link String}) into a list of {@link GrantedAuthority}
	 *
	 * @param userRoles
	 *
	 * @return
	 */
	private static Collection<? extends GrantedAuthority> transformToAuthorities(List<String> userRoles) {
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		System.out.println("loadByUserName : " + user.getUsername() + " " + user.getPassword());
		if (user != null) {
			List<String> roles = userRoleRepository.findRoleByUserName(username);
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
					transformToAuthorities(roles));
		} else {
			throw new UsernameNotFoundException("No user exists with username: " + username);
		}

	}

	/**
	 * Add a new user with the user repository
	 *
	 * @param username the username
	 * @param password the password
	 * @param email the email
	 * @param city the city
	 * @param roles the roles
	 */
	public void addNewUser(String username, String password, String email, String city, String... roles) {

		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		user.setCity(city);
		user = userRepository.save(user);

		for (String role : roles) {

			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUserId(user.getId());

			userRoleRepository.save(userRole);
		}

	}

	/**
	 * Find a user with his username.
	 *
	 * @param username
	 * @return the user or null
	 */
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Return a list of all users.
	 *
	 * @return users list
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}
}