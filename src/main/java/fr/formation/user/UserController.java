package fr.formation.user;

import fr.formation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Signup.
	 *
	 * @param username the username
	 * @param password the password
	 * @param roles    the roles
	 */
	@PutMapping("/")
	public void signup(@RequestParam String username, @RequestParam String password,
										 @RequestParam String... roles) {

		userService.addNewUser(username, password, roles);

	}

	@GetMapping("/{username}")
	public User findByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);
		return user;
	}

	@GetMapping("/")
	public List<User> findAll() {
		return userService.findAll();
	}

}
