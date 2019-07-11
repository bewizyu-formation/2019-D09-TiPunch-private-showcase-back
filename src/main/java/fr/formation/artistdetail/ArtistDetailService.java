package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.department.Department;
import fr.formation.department.DepartmentService;
import fr.formation.geo.GeoApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

    @Autowired
    RestTemplate restTemplate;


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
            departments.add(departmentService.findByName(departmentName));
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


    public List<ArtistDetail> findAllByLocalization(String longitude, String latitude) {

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(GeoGoogleApiConstants.GEO_GOOGLE_API_BASE_URL)
                .queryParam(GeoGoogleApiConstants.PARAM_LAT_LONG, longitude + "," + latitude)
                .queryParam(GeoGoogleApiConstants.PARAM_KEY, GeoGoogleApiConstants.VALUE_KEY);


        System.out.println(this.restTemplate.getForObject(
                builder.toUriString(),
                List.class)
        );




        return new ArrayList<ArtistDetail>();
    }
}
