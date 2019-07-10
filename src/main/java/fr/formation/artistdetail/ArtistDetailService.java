package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistRepository;
import fr.formation.artist.ArtistService;
import fr.formation.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Service
public class ArtistDetailService {

    @Autowired
    private ArtistDetailRepository artistDetailRepository;


    public void addNewArtistDetail(Artist artist, Byte[] photo, String longDescription, String site, int phoneNumber, Set<Department> departments) {
        ArtistDetail artistDetail = new ArtistDetail();
        artistDetail.setArtiste(artist);
        artistDetail.setPhoto(photo);
        artistDetail.setLongDescription(longDescription);
        artistDetail.setSite(site);
        artistDetail.setPhoneNumber(phoneNumber);
        artistDetail.setDepartments(departments);
        artistDetail.setNbVotes(0);
        artistDetail.setTotalVotes(0);

        System.out.println("Sauvegarde de artistDetail de l'artiste : " + artist.getName());

        artistDetail = artistDetailRepository.save(artistDetail);

    }

    /**
     * Get all artists details from departement that correspond to authenticated user location
     * @return
     */
    public Set<ArtistDetail> findAllByDepartment(Department department){
        return artistDetailRepository.findAllByDepartments(department);
    }
}
