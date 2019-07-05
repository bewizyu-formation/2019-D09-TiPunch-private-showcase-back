package fr.formation.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The type User.
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discr")
@DiscriminatorValue("USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	@NotNull
	@Size(min=UserConstants.MIN_LENGTH, max=UserConstants.USERNAME_MAX_LENGTH)
	private String username;


	@Column(name = "password")
	@JsonIgnore
	@NotNull
	private String password;

	@Column(name="email")
	@NotNull
	@Email
	@Size(min=UserConstants.MIN_LENGTH, max=UserConstants.EMAIL_MAX_LENGTH)
	private String email;

	@Column(name="city")
	@NotNull
	@Size(min=UserConstants.MIN_LENGTH, max=UserConstants.CITY_MAX_LENGTH)
	private String city;


	/**
	 * Check if the password contains at least 8 characters, with at least one capital letter, one lowercase and one number
	 * @return the boolean is valid
	 */
	@AssertTrue(message = "password must contains at least 8 characters, one uppercase, one lowercase and one number")
	public boolean isValid() {
		if(this.password != null) {
			boolean response = this.password.matches(UserConstants.PASSWORD_PATTERN);
			System.out.println("Test isValidate : password =" + password + "/ return =" + response);
			return this.password.matches(UserConstants.PASSWORD_PATTERN);
		}
		return false;
	}


	/**
	 * Instantiates a new User.
	 */
	public User() {
	}

	/**
	 * Instantiates a new User.
	 *
	 * @param username	the username
	 * @param password	the password
	 * @param email		the email
	 * @param city		the city
	 */
	public User(@NotNull @Size(min = 2, max = 150) String username, @NotNull String password, @NotNull @Email String email, @NotNull @Size(min = 2, max = 200) String city) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.city = city;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * Sets username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets city.
	 *
	 * @param city the city
	 */
	public void setCity(String city) {
		this.city = city;
	}
}
