package fr.formation;

import fr.formation.security.SecurityConstants;
import fr.formation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class configure the dataset at application start
 */
@Component
public class BoostrapData {

	private UserService userService;

	private PasswordEncoder passwordEncoder;

	/**
	 * Instantiates a new Boostrap data.
	 *
	 * @param userService     the user service
	 * @param passwordEncoder the password encoder
	 */
	@Autowired
	public BoostrapData(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * On start.
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {

		userService.addNewUser(
				"admin",
				passwordEncoder.encode("admin"),
				SecurityConstants.ROLE_ADMIN
		);
		userService.addNewUser(
				"user",
				passwordEncoder.encode("user"),
				SecurityConstants.ROLE_USER
		);
	}

}
