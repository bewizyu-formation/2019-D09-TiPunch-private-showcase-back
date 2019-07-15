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

	private final static String DIR_IMAGES_ARTIST = "/src/assets/";

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

		// Utilisateurs simples initiaux avec deux rôles différents
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

		// Artistes par défaut pour la démo
		artistService.addNewUserArtist(
				"coverclub",
				"User1234",
				"coverclub@gmail.com",
				"Lyon",
				"Cover Club",
				"Groupe de reprises Pop/Rock !",
				SecurityConstants.ROLE_USER
		);

		artistService.addNewUserArtist(
				"echo",
				"User1234",
				"echo@gmail.com",
				"Lyon",
				"Echo",
				"Trio Accoustique : Folk/Blues/Pop",
				SecurityConstants.ROLE_USER
		);

		artistService.addNewUserArtist(
				"barriocombo",
				"User1234",
				"barriocombo@gmail.com",
				"Lyon",
				"Barrio Combo",
				"Groupe Latino : Musique du monde/Reggae",
				SecurityConstants.ROLE_USER
		);

		artistService.addNewUserArtist(
				"peoplearestrange",
				"User1234",
				"peoplearestrange@gmail.com",
				"Marseille",
				"People Are Strange",
				"Passionnés de musique rocks 70's",
				SecurityConstants.ROLE_USER
		);

		artistService.addNewUserArtist(
				"planb",
				"User1234",
				"planb@gmail.com",
				"Marseille",
				"Plan b",
				"On a toujours besoin d'un plan b....",
				SecurityConstants.ROLE_USER
		);

		artistService.addNewUserArtist(
				"artistetest",
				"User1234",
				"test@test.fr",
				"Grenoble",
				"Artiste Test",
				"Description courte de l'artiste"
		);

		/*artistService.addNewUserArtist(
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
		);*/

		artistDetailService.addNewArtistDetail(
				artistService.findByUsername("planb"),
				DIR_IMAGES_ARTIST+"planb.jpg",
				"On a toujours besoin d'un plan B... Plan B est un groupe Marseillais de reprises pop rock années 70 et 80 formé en 2012.   \n" +
						"\n" +
						"Nous reprenons des titres que nous aimons et qui nous ont fait rêver  ou danser. Que des valeurs sures, que des classiques (Téléphone, The  Rolling Stones, ZZ TOP, The Beatles, The Doors...). \n" +
						"\n" +
						"Nous y mettons toute  l'énergie de l'esprit Rock 'n' Roll et faisons partager notre amour de  la musique et notre bonne humeur à ceux qui nous écoutent et nous font confiance. ",
				"planb.monsite.fr",
				789654125,
				new ArrayList<String>(Arrays.asList("Bouches-du-Rhône","Rhône"))
		);

		artistDetailService.addNewArtistDetail(
				artistService.findByUsername("peoplearestrange"),
				DIR_IMAGES_ARTIST+"peoplearestrange.jpg",
				"People Are Strange,  c est avant tout un couple : Laurent et Tina. Passionnés tous deux de musique rock 70's , ils y puisent leurs influences et leurs inspirations ( Led Zeppelin,  Janis Joplin,  The Doors... .) En duo, ils reprennent des morceaux phares de cette époque (Dust in the wind, my lady d'Arbanville, Me and Bobby Mc Gee, Whole lotta love... )  ainsi que des morceaux des années 50/60  avec des artistes comme Elvis Presley,  Chuck Berry, The Beatles,  The Rolling stones...  En groupe rock, le repertoire s'agremente de chansons plus modernes ( ACDC/ Red hot chili peppers / Rage against the machine/ Scorpions / Kiss... )  Enfin, passionnés de musique celtique, ils proposent un répertoire traditionnel irlandais avec des standards tels que Sally Mc Lennane, Dirty old town, Irish rover et des jigs instrumentaux.",
				"peoplearestrange.monsite.fr",
				612345678,
				new ArrayList<String>(Arrays.asList("Bouches-du-Rhône"))
		);

		artistDetailService.addNewArtistDetail(
				artistService.findByUsername("barriocombo"),
				DIR_IMAGES_ARTIST+"barrioCombo.jpg",
				"Apportez de la couleur à votre évènement grâce au groupe latino Barrio Combo. Composé de musiciens professionnels, ce groupe saura insuffler musique et bonne humeur à votre événement pour que vous en gardiez des souvenirs uniques.\n" +
						"\n" +
						"Barrio Combo, ce sont des musiciens du barrio qui se sont réunis afin de partager leur passion pour la musique latine. Ils vous offrent une animation idéale pour votre cocktail ou vin d'honneur ou un concert. Ils sauront ensoleiller votre réception.",
				"barriocombo.monsite.fr",
				612347878,
				new ArrayList<String>(Arrays.asList("Rhône","Isère"))
		);

		artistDetailService.addNewArtistDetail(
				artistService.findByUsername("echo"),
				DIR_IMAGES_ARTIST+"echo.jpg",
				"Fondé en 2014 par Charlotte Silvestri, Guillaume Plasse et Colas Hutter, le trio acoustique s'est d'abord spécialisé dans l'arrangement, jouant avec les contrastes de styles et d'atmosphères, afin d'apporter quelque chose de nouveau et de personnel aux tubes intemporels composant leur répertoire.\n" +
						"\n" +
						"Aussi calme et langoureux qu'il sait être rythmé et dynamique, le tandem basse - guitare sublime chacune de ses interprétations par la voix envoûtante de leur chanteuse, donnant aux morceaux des textures originales et inattendues",
				"echo.monsite.fr",
				612347878,
				new ArrayList<String>(Arrays.asList("Rhône"))
		);

		artistDetailService.addNewArtistDetail(
				artistService.findByUsername("coverclub"),
				DIR_IMAGES_ARTIST+"coverclub.jpg",
				"Cover Club est un groupe de reprises pop-rock basé à Lyon, disponible pour animer vos événements publics ou privés.\n" +
						"\n" +
						"Cover Club écume les scènes sans relâche et avec succès depuis 2013, à grand coup de reprises bien senties, de groove tenace et de refrains à reprendre en chœur.",
				"coverclub.monsite.fr",
				612347878,
				new ArrayList<String>(Arrays.asList("Rhône","Ain"))
		);

		/*artistDetailService.addNewArtistDetail(
				artistService.findByUsername("artist2"),
				"photo",
				"Une description détaillée de l'artiste2 lui-même, soumise par l'artiste et mettant en valeur son talent.",
				"www.artiste2.com",
				612345678,
				new ArrayList<String>(Arrays.asList("Ain", "Rhône"))
		);*/



	}

}
