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
		if(user == null) {
			new NotFoundException("Username " + username + " not found in the database");
		}
		return user;
	}

	@GetMapping("/")
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/checkUsernameNotTaken/{username}")
	public CheckUniqueDto checkUsername(@PathVariable String username) {
		CheckUniqueDto check = new CheckUniqueDto();
		if(userService.findByUsername(username) == null) {
			check.setUsernameNotTaken(true);
		}
		return check;
	}

	@GetMapping("/checkArtistNameNotTaken/{artistName}")
	public CheckUniqueDto checkArtistName(@PathVariable String artistName) {
		CheckUniqueDto check = new CheckUniqueDto();
		/*if(findByArtistName(artistName) == null) {
			check.setArtistNameNotTaken(true);
		}*/
		return check;
	}


	private class CheckUniqueDto {
		private boolean usernameNotTaken = false;
		private boolean artistNameNotTaken = false;

		public CheckUniqueDto() {
		}

		public CheckUniqueDto(boolean usernameNotTaken, boolean artistnameNotTaken) {
			this.usernameNotTaken = usernameNotTaken;
			this.artistNameNotTaken = artistnameNotTaken;
		}

		public boolean isUsernameNotTaken() {
			return usernameNotTaken;
		}

		public void setUsernameNotTaken(boolean usernameNotTaken) {
			this.usernameNotTaken = usernameNotTaken;
		}

		public boolean isArtistNameNotTaken() {
			return artistNameNotTaken;
		}

		public void setArtistNameNotTaken(boolean artistnameNotTaken) {
			this.artistNameNotTaken = artistnameNotTaken;
		}
	}

}
