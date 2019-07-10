package fr.formation.artist;

import fr.formation.user.User;
import fr.formation.user.UserRole;
import fr.formation.user.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * Return a list of all artists.
     *
     * @return artists list
     */
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    /**
     * Add a new artist with the artist repository
     *
     * @param username
     * @param password
     * @param email
     * @param city
     * @param name
     * @param description
     * @param roles
     */
    public void addNewUserArtist(String username, String password, String email, String city, String name, String description, String... roles) {
        Artist artist = new Artist();
        artist.setUsername(username);
        artist.setPassword(password);
        artist.setEmail(email);
        artist.setCity(city);
        artist.setName(name);
        artist.setDescription(description);
        artist = artistRepository.save(artist);

        for (String role : roles) {

            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUserId(artist.getId());

            userRoleRepository.save(userRole);
        }
    }

    /**
     * Find an artist with his artist name.
     *
     * @param artistName
     * @return the artist or null
     */
    public Artist findByName(String artistName) {
        return artistRepository.findByName(artistName);
    }

    /**
     * Find an artist with his username
     *
     * @param username
     * @return the artist or null
     */
    public Artist findByUsername(String username) {
        return artistRepository.findByUsername(username);
    }

}
