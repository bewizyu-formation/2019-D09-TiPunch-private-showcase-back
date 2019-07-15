package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistService;
import fr.formation.department.Department;
import fr.formation.department.DepartmentServiceImpl;
import fr.formation.exception.LocalizationException;
import fr.formation.exception.NotFoundException;
import fr.formation.user.User;
import fr.formation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/artistdetails")
public class ArtistDetailController {

    @Autowired
    private ArtistDetailService artistDetailService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    /**
     * Artist detail creation
     * @param artistDetailDto
     */
    @PutMapping("/")
    public void putArtistDetail(@RequestBody ArtistDetailDto artistDetailDto) {
        Artist artist = artistService.findByUsername(artistDetailDto.getUsername());
        if(artist != null) {
            artistDetailService.addNewArtistDetail(artist, artistDetailDto.getPhoto(), artistDetailDto.getLongDescription(), artistDetailDto.getSite(), artistDetailDto.getPhoneNumber(), artistDetailDto.getDepartmentNames());
        } else {
            throw new NotFoundException("Artist not found with username : " + artistDetailDto.getUsername());
        }
    }

    /**
     * Find artist details by artist name
     * @param artistName
     */
    @GetMapping("/artist/{artistName}")
    public ArtistDetail findByArtistName(@PathVariable String artistName) {
        Artist artist = artistService.findByName(artistName);
        if(artist == null) {
            throw new NotFoundException("Artist " + artistName + " not found.");
        }

        return artistDetailService.findByArtist(artist);
    }

    /**
     * Find all artist details
     * @return
     */
    @GetMapping("/")
    public List<ArtistDetail> findAllArtistDetails() {
        return artistDetailService.findAll();
    }

    /**
     * Find the list of department in the database
     * @return list of artistDetail
     */
    @GetMapping("/departments")
    public List<Department> findAllDepartments() {
        return departmentServiceImpl.findAll();
    }

    /**
     * Find the list of artist details including the department with department name
     * @param departmentName
     * @return list of artistDetail
     */
    @GetMapping("/{departmentName}")
    public List<ArtistDetail> findAllArtistDetailsByDepartmentName(@PathVariable String departmentName){
        Department department = departmentServiceImpl.findByName(departmentName);
        
        List<ArtistDetail> artistDetails;
        if(department != null) {
            artistDetails = artistDetailService.findAllByDepartment(department);
        } else {
            throw new NotFoundException("Department " + departmentName + " not found in the departments list");
        }
        return artistDetails;
    }

    /**
     * Find the list of artist details including the department with localization
     * @param latitude
     * @param longitude
     * @return list of artistDetail
     */
    @GetMapping("/localization")
    public List<ArtistDetail> findAllArtistDetailsByLocalization(@RequestParam String latitude, @RequestParam String longitude){
        List<ArtistDetail> artistDetails = new ArrayList<>();

        if(latitude.equals("-100") || longitude.equals("-200")) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByUsername(username);
            if(user == null) {
                throw new NotFoundException("Username " + username + " not found.");
            }
            artistDetails = artistDetailService.findAllByLocalization(user.getCity());

        }else {
            try {
                artistDetails = artistDetailService.findAllByLocalization(latitude, longitude);
            } catch (NotFoundException e) {
                throw new NotFoundException(e.getMessage());
            } catch (LocalizationException e) {
                throw new LocalizationException(e.getMessage());
            }
        }

        return artistDetails;

    }


}
