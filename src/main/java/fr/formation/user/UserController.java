package fr.formation.user;

import fr.formation.exception.AlreadyExistsException;
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
	 * User signup
	 *
	 * @param userForm
	 * @throws AlreadyExistsException if the username already exists.
	 */
	@PutMapping("/")
	public void signup(@RequestBody UserForm userForm) {
		if(userService.findByUsername(userForm.getUsername()) == null) {
			System.out.println("Sauvegarde du user : " + userForm.getUsername());
			userService.addNewUser(userForm.getUsername(), userForm.getPassword(), userForm.getEmail(), userForm.getCity(), userForm.getRoles());
		} else {
			throw new AlreadyExistsException("The username " + userForm.getUsername() + " already exist.");
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
		System.out.println("findByUsername méthode");
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


}
