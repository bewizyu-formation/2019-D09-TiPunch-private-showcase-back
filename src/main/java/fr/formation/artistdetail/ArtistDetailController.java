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


    @PutMapping("/")
    public void putArtistDetail(@RequestBody ArtistDetailDto artistDetailDto) {
        Artist artist = artistService.findByUsername(artistDetailDto.getUsername());
        if(artist != null) {
            artistDetailService.addNewArtistDetail(artist, artistDetailDto.getPhoto(), artistDetailDto.getLongDescription(), artistDetailDto.getSite(), artistDetailDto.getPhoneNumber(), artistDetailDto.getDepartmentNames());
        } else {
            throw new NotFoundException("Artist not found with username : " + artistDetailDto.getUsername());
        }
    }

    @GetMapping("/")
    public List<ArtistDetail> findAllArtistDetails() {
        return artistDetailService.findAll();
    }

    @GetMapping("/departments")
    public List<Department> findAllDepartments() {
        return departmentServiceImpl.findAll();
    }


    @GetMapping("/{departmentName}")

    public List<ArtistDetail> getAllArtistDetailsByDepartmentName(@PathVariable String departmentName){
        Department department = departmentServiceImpl.findByName(departmentName);
        
        List<ArtistDetail> artistDetails;
        if(department != null) {
            artistDetails = artistDetailService.findAllByDepartment(department);
        } else {
            throw new NotFoundException("Department " + departmentName + " not found in the departments list");
        }
        return artistDetails;
    }

    @GetMapping("/localization")
    public List<ArtistDetail> getAllArtistDetailsByLocalization(@RequestParam String latitude, @RequestParam String longitude){
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
