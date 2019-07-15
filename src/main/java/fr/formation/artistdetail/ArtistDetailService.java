package fr.formation.artistdetail;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.formation.artist.Artist;
import fr.formation.department.Department;
import fr.formation.department.DepartmentServiceImpl;
import fr.formation.exception.LocalizationException;
import fr.formation.exception.NotFoundException;
import fr.formation.geo.model.Commune;
import fr.formation.geo.services.CommuneService;
import fr.formation.geo.services.impl.CommuneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

/**
 * The type ArtistDetail service.
 */
@Service
@Transactional
public class ArtistDetailService {

    @Autowired
    private ArtistDetailRepository artistDetailRepository;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    @Autowired
    private CommuneServiceImpl communeServiceImpl;


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Find all artist details
     * @return artist details list
     */

    public List<ArtistDetail> findAll() {
        return artistDetailRepository.findAll();
    }

    /**
     * Add a new ArtistDetail with the artistDetail repository
     *
     * @param artist
     * @param photo
     * @param longDescription
     * @param site
     * @param phoneNumber
     * @param departmentNames
     */
    public void addNewArtistDetail(Artist artist, String photo, String longDescription, String site, int phoneNumber, List<String> departmentNames) {
        ArtistDetail artistDetail = new ArtistDetail();
        artistDetail.setArtist(artist);
        artistDetail.setPhoto(photo);
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

    public ArtistDetail findByArtist(Artist artist) {
        return artistDetailRepository.findByArtist(artist);
    }

    /**
     * Get all artists details from department that correspond to authenticated user location
     * @return list of artists details
     */
    public List<ArtistDetail> findAllByDepartment(Department department){
        return artistDetailRepository.findAllByDepartment(department);
    }

    /**
     * Find artists details from department that correspond to user localisation when connected
     * @param latitude
     * @param longitude
     * @return list of artists details
     * @throws LocalizationException
     * @throws NotFoundException
     */
    public List<ArtistDetail> findAllByLocalization(String latitude, String longitude) throws LocalizationException, NotFoundException {
        //---------------- get adress with latitude and longitude
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(GeoGoogleApiConstants.VALUE_KEY);

        JOpenCageReverseRequest request = new JOpenCageReverseRequest(Double.parseDouble(latitude), Double.parseDouble(longitude));
        request.setLanguage("fr"); // prioritize results in a specific language using an IETF format language code
        request.setNoAnnotations(true); // exclude additional info such as calling code, timezone, and currency

        JOpenCageResponse response = jOpenCageGeocoder.reverse(request);

        // get the formatted address of the first result:
        String formattedAddress = response.getResults().get(0).getFormatted();
        //------------------

        String departmentCode = "00";
        try {
            String[] cutAddress = formattedAddress.split(",");
            //get department code when adress formated like "18 Rue de la Garde, 69005 Lyon, France"
            departmentCode = cutAddress[cutAddress.length - 2].trim().substring(0, 2);
        } catch (IndexOutOfBoundsException e) {
            throw new LocalizationException("Error reading address : " + formattedAddress);
        }

        Department department = departmentServiceImpl.findByCode(departmentCode);

        if(department == null) {
            throw new NotFoundException("Code department " + departmentCode + " not found in the departments list");
        }

        System.out.println("departement : " + department);

        return findAllByDepartment(department);
    }

    /**
     * Find artists details from department that correspond to user's city
     * Used by default if the customer doesn't allow geolocation
     *
     * @param city
     * @return list of artists details
     * @throws NotFoundException
     */
    public List<ArtistDetail> findAllByLocalization(String city) throws NotFoundException {

        List<Commune> communes = objectMapper.convertValue(communeServiceImpl.getCommunes(city), new TypeReference<List<Commune>>(){});


        if(communes.size() == 0) {
            throw new NotFoundException("Commune " + city + " not found.");
        }

        Optional<Commune> commune = communes.stream().filter(c -> c.getNom().equals(city)).findFirst();

        Department department = departmentServiceImpl.findByCode(commune.get().getCodeDepartement());

        if(department == null) {
            throw new NotFoundException("Code department " + communes.get(0).getCodeDepartement() + " not found in the departments list");
        }
        return findAllByDepartment(department);

    }

}
