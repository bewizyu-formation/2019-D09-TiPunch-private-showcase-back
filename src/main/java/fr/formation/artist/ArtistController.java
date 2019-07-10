package fr.formation.artist;

import fr.formation.exception.AlreadyExistsException;
import fr.formation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Artist Controller
 */
@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    /**
     * Find all artists
     *
     * @return the list of all users in the database
     */
    @GetMapping("/")
    public List<Artist> findAllArtist(){
        return artistService.findAll();
    }

    /**
     * Artist signup
     *
     * @param artistDto
     * @throws AlreadyExistsException if username or artist name already exist
     */
    @PutMapping("/")
    public void signUp(@RequestBody ArtistDto artistDto){
        if(artistService.findByUsername(artistDto.getUsername()) == null) {
            if(artistService.findByName(artistDto.getName())== null) {
                artistService.addNewUserArtist(artistDto.getUsername(), artistDto.getPassword(), artistDto.getEmail(), artistDto.getCity(), artistDto.getName(), artistDto.getDescription(), artistDto.getRoles());
            } else {
                throw new AlreadyExistsException("The artist name " + artistDto.getName() + "already exist.");
            }
        } else {
            throw new AlreadyExistsException("The username " + artistDto.getUsername() + " already exist.");
        }
    }


}
