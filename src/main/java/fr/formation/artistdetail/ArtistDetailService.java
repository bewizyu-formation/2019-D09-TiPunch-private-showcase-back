package fr.formation.artistdetail;

import fr.formation.artist.ArtistRepository;
import fr.formation.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ArtistDetailService {

    @Autowired
    private ArtistRepository artistRepository;

    /**
     * Get all artists details from departement that correspond to authenticated user location
     * @return
     */
    public List<ArtistDetail> findAllArtistDetailsByUserLocation(){
        List<ArtistDetail> artistsDetails = null;
        return artistsDetails;
    }
}
