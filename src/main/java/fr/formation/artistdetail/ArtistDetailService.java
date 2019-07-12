package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.department.Department;
import fr.formation.department.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArtistDetailService {

    @Autowired
    private ArtistDetailRepository artistDetailRepository;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;


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

        Set<Department> departments = new HashSet<>();
        for(String departmentName : departmentNames) {
            departments.add(departmentServiceImpl.findByName(departmentName));
        }

        artistDetail.setDepartments(departments);

        artistDetailRepository.save(artistDetail);

    }

    /**
     * Get all artists details from departement that correspond to authenticated user location
     * @return
     */
    public List<ArtistDetail> findAllByDepartment(Department department){
        return artistDetailRepository.findAllByDepartment(department);
    }
}
