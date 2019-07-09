package fr.formation.artist;

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


    @GetMapping("/")
    public List<Artist> findAllArtist(){
        return artistService.findAll();
    }

    @PutMapping("/")
    public void signUp(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String email,
                       @RequestParam String city,
                       @RequestParam String name,
                       @RequestParam String description,
                       @RequestParam String... roles){

        artistService.addNewUserArtist(username, password, email, city, name, description, roles);

    }

}
