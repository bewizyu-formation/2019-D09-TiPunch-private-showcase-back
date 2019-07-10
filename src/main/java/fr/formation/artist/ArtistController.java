package fr.formation.artist;

import fr.formation.exception.NotFoundException;
import fr.formation.user.User;
import fr.formation.user.UserController;
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
    public void signUp(@RequestBody ArtistForm artistForm){
        artistService.addNewUserArtist(artistForm.getUsername(), artistForm.getPassword(), artistForm.getEmail(), artistForm.getCity(), artistForm.getName(), artistForm.getDescription(), artistForm.getRoles());
    }


}
