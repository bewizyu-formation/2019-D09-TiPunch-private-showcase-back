package fr.formation.artistdetail;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artistsDetails")
public class ArtistDetailController {

    @GetMapping("/")
    public List<ArtistDetail> getAllArtistsDetailsByUserLocation(){
        List<ArtistDetail> artistsDetails = null;
        return artistsDetails;
    }
}
