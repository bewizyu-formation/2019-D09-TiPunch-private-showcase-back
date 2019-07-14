package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistService;
import fr.formation.department.Department;
import fr.formation.department.DepartmentServiceImpl;
import fr.formation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Locale;
import java.util.Set;
>>>>>>> 7e8eb849c63bcef7357b1b435b2de7240dffa94a

@RestController
@RequestMapping("/artistdetails")
public class ArtistDetailController {

    @Autowired
    private ArtistDetailService artistDetailService;

    @Autowired
    private ArtistService artistService;

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

    public List<ArtistDetail> getAllArtistDetailsByUserLocation(@PathVariable String departmentName){
        Department department = departmentServiceImpl.findByName(departmentName);
        
        List<ArtistDetail> artistDetails;
        if(department != null) {
            artistDetails = artistDetailService.findAllByDepartment(department);
        } else {
            throw new NotFoundException("Department " + departmentName + " not found in the departments list");
        }
        return artistDetails;
    }

    @GetMapping("/locate")
    public void getAllArtistDetailsByLocalization(@RequestParam String latitude, @RequestParam String longitude){
        artistDetailService.findAllByLocalization(latitude, longitude);

        List<ArtistDetail> artistDetails = new ArrayList<>();
        /*
        if(department != null) {
            artistDetails = artistDetailService.findAllByDepartment(department);
        } else {
            throw new NotFoundException("Department " + departmentName + " not found in the departments list");
        }*/

    }





}
