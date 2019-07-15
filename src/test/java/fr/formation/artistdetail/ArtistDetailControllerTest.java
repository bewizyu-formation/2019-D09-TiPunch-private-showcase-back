package fr.formation.artistdetail;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.formation.artist.Artist;
import fr.formation.artist.ArtistRepository;
import fr.formation.department.Department;
import fr.formation.department.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArtistDetailControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ArtistDetailRepository artistDetailRepository;

    /**
     * Used to parse java object to JSON
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Used to do request with identification
     */
    private String authorizationHeader;

    private Artist artist;

    private ArtistDetail artistDetailTest;

    private ArtistDetailDto artistDetailDtoTest;

    /**
     * Initialization of artist, artistDetail, artistDetailDto used in tests and identification for the requests.
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        MvcResult mvcResult = mvc.perform(formLogin("/login").user("admin").password("Admin1234")).andReturn();
        this.authorizationHeader = mvcResult.getResponse().getHeader("Authorization");

        System.out.println("Autorization header : " + authorizationHeader);

        this.artist = artistRepository.findByName("Artist 1");

        this.artistDetailTest = new ArtistDetail();
        artistDetailTest.setArtist(artist);
        artistDetailTest.setLongDescription("Description longue de l'artiste.");
        artistDetailTest.setSite("www.monsiteartisteperso.com");
        artistDetailTest.setPhoneNumber(0605040302);
        Set<Department> departments = new HashSet<>();
        departments.add(departmentRepository.findByName("Vaucluse"));
        departments.add(departmentRepository.findByName("Isère"));
        artistDetailTest.setDepartments(departments);

        this.artistDetailDtoTest = new ArtistDetailDto();
        artistDetailDtoTest.setUsername(this.artist.getUsername());
        artistDetailDtoTest.setLongDescription("Description longue de l'artiste.");
        artistDetailDtoTest.setSite("www.monsiteartisteperso.com");
        artistDetailDtoTest.setPhoneNumber(0605040302);
        artistDetailDtoTest.setPhoto("photo");
        artistDetailDtoTest.setDepartmentNames(new ArrayList<String>(Arrays.asList("Vaucluse", "Isère")));
    }

    /**
     * Prepare the request and overload the header with the identification elements.
     * @param url in relative path
     * @return the ResultActions to test
     * @throws Exception
     */
    private ResultActions getRequest(String url) throws Exception {
        return mvc.perform(get(url).header("Authorization", this.authorizationHeader));
    }

    @Test
    public void putArtistDetail() throws Exception {
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(1);
        mvc.perform(put("/artistdetails/")
                .header("Authorization", this.authorizationHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.artistDetailDtoTest)))
                .andExpect(status().isOk());
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(2);
    }

    @Test
    public void putArtistDetailWithArtistUsernameThatNotExist() throws Exception {
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(1);
        this.artistDetailDtoTest.setUsername("Unknown username");
        mvc.perform(put("/artistdetails/")
                .header("Authorization", this.authorizationHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.artistDetailDtoTest)))
                .andExpect(status().is(404))
                .andExpect(content().json("{\"message\":\"Artist not found with username : " + this.artistDetailDtoTest.getUsername() + "\"}", false));
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(1);
    }

    @Test
    public void findAllArtistDetailsByUserLocation() throws Exception {
        artistDetailRepository.save(artistDetailTest);
        Assertions.assertThat(artistRepository.findAll()).hasSize(2);
        getRequest("/artistdetails/Isère")
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new ArtistDetail[]{artistDetailTest})));
        artistDetailRepository.delete(artistDetailTest);
    }

    @Test
    public void findAllArtistDetailsByUserLocationWithBadDeprtmentName() throws Exception {
        artistDetailRepository.save(artistDetailTest);
        Assertions.assertThat(artistRepository.findAll()).hasSize(2);
        getRequest("/artistdetails/Isre")
                .andExpect(status().is(404))
                .andExpect(content().json("{\"message\":\"Department " + "Isre" + " not found in the departments list\"}"));
        artistDetailRepository.delete(artistDetailTest);
    }

    @Test
    public void findAllArtistDetailsByLocalization() throws Exception {
        ArtistDetail response = artistDetailRepository.findByArtist(artistRepository.findByName("Artiste 2"));
        getRequest("/artistdetails/localization?latitude=45.7605259&longitude=4.7909059")
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new ArrayList<ArtistDetail>(Arrays.asList(response)))));
    }

    @Test
    public void findAllArtistDetailsByLocalizationWithDefaultLocalization() throws Exception {
        ArtistDetail response = artistDetailRepository.findByArtist(artistRepository.findByName("Artiste 2"));
        getRequest("/artistdetails/localization?latitude=-100&longitude=-200")
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void findAllArtistDetailsByLocalizationWithLocalizationOutOfFrance() throws Exception {
        ArtistDetail response = artistDetailRepository.findByArtist(artistRepository.findByName("Artiste 2"));
        getRequest("/artistdetails/localization?latitude=4.7909059&longitude=45.7605259")
                .andExpect(status().is(404))
                .andExpect(content().json("{\"message\":\"Code department Be not found in the departments list\"}", false));
    }




}