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

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

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

    public Artist findByName(String artistName) {
        return artistRepository.findByName(artistName);
    }
}
