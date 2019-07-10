package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistService;
import fr.formation.department.Department;
import fr.formation.department.DepartmentService;
import fr.formation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/artistsDetails")
public class ArtistDetailController {

    @Autowired
    private ArtistDetailService artistDetailService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private DepartmentService departementService;

    @PutMapping("/")
    public void putArtistDetail(@RequestBody ArtistDetailDto artistDetailDto) {
        Artist artist = artistService.findByUsername(artistDetailDto.getUsername());
        if(artist != null) {
            artistDetailService.addNewArtistDetail(artist, artistDetailDto.getPhoto(), artistDetailDto.getLongDescription(), artistDetailDto.getSite(), artistDetailDto.getPhoneNumber(), artistDetailDto.getDepartments());
        } else {
            throw new NotFoundException("Artist not found with username : " + artistDetailDto.getUsername());
        }
    }

    @GetMapping("/{departmentName}")
    public Set<ArtistDetail> getAllArtistDetailsByUserLocation(@PathVariable String departmentName){
        Department department = departementService.findByName(departmentName);
        Set<ArtistDetail> artistDetails;
        if(department != null) {
            artistDetails = artistDetailService.findAllByDepartment(department);
        } else {
            throw new NotFoundException("Department " + departmentName + " not found in the departments list");
        }
        return artistDetails;
    }
}
