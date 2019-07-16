package fr.formation.user;

import fr.formation.exception.AlreadyExistsException;
import fr.formation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * User signup
	 *
	 * @param userDto
	 * @throws AlreadyExistsException if the username already exists.
	 */
	@PutMapping("/")
	public void signup(@RequestBody UserDto userDto) {
		if(userService.findByUsername(userDto.getUsername()) == null) {
			userService.addNewUser(userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), userDto.getCity(), userDto.getRoles());
		} else {
			throw new AlreadyExistsException("The username " + userDto.getUsername() + " already exist.");
		}
	}

	/**
	 * Find user by username
	 * @param username
	 * @return user
	 * @throws NotFoundException if username doesn't exist in database.
	 */
	@GetMapping("/{username}")
	public User findByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);
		if(user == null) {
			throw new NotFoundException("Username " + username + " not found in the database");
		}
		return user;
	}

	/**
	 * Find all users
	 * @return the list of all users in the database
	 */
	@GetMapping("/")
	public List<User> findAll() {
		return userService.findAll();
	}


	@GetMapping("/checkpassword")
	public PasswordDto testPassword(@RequestParam String password) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);
		if(user == null) {
			throw new NotFoundException("Username " + username + " not found in the database");
		}
		return new PasswordDto(passwordEncoder.matches(password, user.getPassword()));
	}


	@PutMapping("/update")
	public void updateUser(@RequestBody UserUpdateDto userUpdateDto) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(username);
		if(user == null) {
			throw new NotFoundException("Username " + username + " not found in the database");
		}
		user.setPassword(passwordEncoder.encode(userUpdateDto.password));
		user.setEmail(userUpdateDto.email);

		userService.update(user);
	}

	class PasswordDto {
		public boolean passwordMatch = false;

		public PasswordDto() {
		}

		public PasswordDto(boolean passwordMatch) {
			this.passwordMatch = passwordMatch;
		}
	}

}
