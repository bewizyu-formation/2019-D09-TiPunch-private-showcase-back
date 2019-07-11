package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistRepository;
import fr.formation.artist.ArtistService;
import fr.formation.artistdepartment.ArtistDepartment;
import fr.formation.department.Department;
import fr.formation.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArtistDetailService {

    @Autowired
    private ArtistDetailRepository artistDetailRepository;

    @Autowired
    private DepartmentService departmentService;


    public List<ArtistDetail> findAll() {
        return artistDetailRepository.findAll();
    }

    public void addNewArtistDetail(Artist artist, String photo, String longDescription, String site, int phoneNumber, List<String> departmentNames) {
        ArtistDetail artistDetail = new ArtistDetail();
        artistDetail.setArtiste(artist);
        //artistDetail.setPhoto(photo);
        artistDetail.setLongDescription(longDescription);
        artistDetail.setSite(site);
        artistDetail.setPhoneNumber(phoneNumber);
        artistDetail.setNbVotes(0);
        artistDetail.setTotalVotes(0);

        List<ArtistDepartment> artistDepartments = new ArrayList<>();
        for(String departmentName : departmentNames) {
            Department department = departmentService.findByName(departmentName);
            artistDepartments.add(new ArtistDepartment(department.getName(), department.getCode(), artistDetail));
        }
        artistDetail.setArtistDepartments(artistDepartments);

        System.out.println(artistDepartments.get(0));

        artistDetailRepository.save(artistDetail);

    }

    /**
     * Get all artists details from departement that correspond to authenticated user location
     * @return
     */
    public List<ArtistDetail> findAllByDepartment(Department department){
        ArtistDepartment artistDepartment = new ArtistDepartment(department.getName(), department.getCode());
        return artistDetailRepository.findAllByArtistDepartments(artistDepartment);
    }
}
