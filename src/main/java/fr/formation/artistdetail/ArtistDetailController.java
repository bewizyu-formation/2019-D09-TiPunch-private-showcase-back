package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistService;
import fr.formation.department.Department;
import fr.formation.department.DepartmentServiceImpl;
import fr.formation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            artistDetailService.addNewArtistDetail(artist, artistDetailDto);
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
}
