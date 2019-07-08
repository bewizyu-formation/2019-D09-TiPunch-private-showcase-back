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
	 * User signup.
	 *
	 * @param username the username
	 * @param password the password
	 * @param roles    the roles
	 *
	 * @throws AlreadyExistsException if the username already exists.
	 */
	@PutMapping("/")
	public void signup(@RequestParam String username, @RequestParam String password,
										 @RequestParam String... roles) {
		if(userService.findByUsername(username) == null) {
			userService.addNewUser(username, password, roles);
		} else {
			throw new AlreadyExistsException("The username " + username + " already exist.");
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

	/**
	 * Check if the username is free.
	 * @param username
	 * @return CheckUniqueDTO that contains the boolean usernameNotTaken
	 */
	@GetMapping("/checkUsernameNotTaken/{username}")
	public CheckUniqueDto checkUsername(@PathVariable String username) {
		CheckUniqueDto check = new CheckUniqueDto();
		if(userService.findByUsername(username) == null) {
			check.setUsernameNotTaken(true);
		}
		return check;
	}

	/**
	 * Check if the artist name is free.
	 * @param artistName
	 * @return CheckUniqueDTO that contains the boolean artistNameNotTaken
	 */
	@GetMapping("/checkArtistNameNotTaken/{artistName}")
	public CheckUniqueDto checkArtistName(@PathVariable String artistName) {
		CheckUniqueDto check = new CheckUniqueDto();
		/*if(findByArtistName(artistName) == null) {
			check.setArtistNameNotTaken(true);
		}*/
		return check;
	}

	/**
	 * DTO class used to transmit the results of tests about uniques values
	 */
	class CheckUniqueDto {
		private boolean usernameNotTaken = false;
		private boolean artistNameNotTaken = false;

		/**
		 * Instantiates a new CheckUniqueDTO
		 */
		public CheckUniqueDto() {
		}

		/**
		 * Gets usernameNotTaken
		 * @return the boolean usernameNotTaken
		 */
		public boolean isUsernameNotTaken() {
			return usernameNotTaken;
		}

		/**
		 * Sets usernameNotTaken
		 * @param usernameNotTaken
		 */
		public void setUsernameNotTaken(boolean usernameNotTaken) {
			this.usernameNotTaken = usernameNotTaken;
		}

		/**
		 * Gets artistNameNotTaken
		 * @return the boolean artistNameNotTaken
		 */
		public boolean isArtistNameNotTaken() {
			return artistNameNotTaken;
		}

		/**
		 * Sets artistNameNotTaken
		 * @param artistNameNotTaken
		 */
		public void setArtistNameNotTaken(boolean artistNameNotTaken) {
			this.artistNameNotTaken = artistNameNotTaken;
		}
	}

}
