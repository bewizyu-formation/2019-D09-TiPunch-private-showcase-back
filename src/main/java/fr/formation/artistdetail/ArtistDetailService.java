package fr.formation.artistdetail;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import fr.formation.artist.Artist;
import fr.formation.department.Department;
import fr.formation.department.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ArtistDetailService {

    @Autowired
    private ArtistDetailRepository artistDetailRepository;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

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


    public void findAllByLocalization(String latitude, String longitude) {

        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(GeoGoogleApiConstants.VALUE_KEY);

        JOpenCageReverseRequest request = new JOpenCageReverseRequest(Double.parseDouble(latitude), Double.parseDouble(longitude));
        request.setLanguage("fr"); // prioritize results in a specific language using an IETF format language code
        request.setNoAnnotations(true); // exclude additional info such as calling code, timezone, and currency

        JOpenCageResponse response = jOpenCageGeocoder.reverse(request);

        // get the formatted address of the first result:
        String formattedAddress = response.getResults().get(0).getFormatted();

        System.out.println(formattedAddress);




    }
}
