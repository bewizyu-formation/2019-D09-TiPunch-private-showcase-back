package fr.formation;

import fr.formation.artist.ArtistService;
import fr.formation.artistdetail.ArtistDetailService;
import fr.formation.security.SecurityConstants;
import fr.formation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class configure the dataset at application start
 */
@Component
public class BoostrapData {

	private UserService userService;

	private ArtistService artistService;

	private PasswordEncoder passwordEncoder;

	private ArtistDetailService artistDetailService;

	/**
	 * Instantiates a new Boostrap data.
	 *
	 * @param userService     the user service
	 * @param passwordEncoder the password encoder
	 */
	@Autowired
	public BoostrapData(UserService userService, PasswordEncoder passwordEncoder, ArtistService artistService, ArtistDetailService artistDetailService) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.artistService = artistService;
		this.artistDetailService = artistDetailService;
	}

	/**
	 * On start.
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {

		userService.addNewUser(
				"admin",
				"Admin1234",
				"admin@email.fr",
				"Paris",
				SecurityConstants.ROLE_ADMIN
		);
		userService.addNewUser(
				"user",
				"User1234",
				"user@email.fr",
				"Lyon",
				SecurityConstants.ROLE_USER
		);

		artistService.addNewUserArtist(
				"artist1",
				"Artist12",
				"artist1@email.fr",
				"Marseille",
				"Artist 1",
				"Je suis l'artiste 1 et je m'éclate dans tout ce que je fais",
				SecurityConstants.ROLE_USER
				);

		artistService.addNewUserArtist(
				"artist2",
				"Artist23",
				"artist2@email.fr",
				"Nîmes",
				"Artiste 2",
				"Je suis l'artist 2 et je m'éclate dans tout ce que je fais",
				SecurityConstants.ROLE_USER
		);

		artistDetailService.addNewArtistDetail(
				artistService.findByUsername("artist2"),
				"photo",
				"Une description détaillée de l'artiste2 lui-même, soumise par l'artiste et mettant en valeur son talent.",
				"www.artiste2.com",
				612345678,
				new ArrayList<String>(Arrays.asList("Ain", "Rhône"))
		);



	}

}
