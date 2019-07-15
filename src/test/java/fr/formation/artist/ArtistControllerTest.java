package fr.formation.artist;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.formation.security.SecurityConstants;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArtistControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ArtistRepository artistRepository;

    /**
     * Used to parse java object to JSON
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * User to do request with identification
     */
    private String authorizationHeader;

    private Artist artistTest;

    private ArtistDto artistDto;

    /**
     * Initialization of artist used in test and authentication for requests
     */
    @Before
    public void init() throws Exception{
        MvcResult mvcResult = mvc.perform(formLogin("/login").user("admin").password("Admin1234")).andReturn();
        this.authorizationHeader = mvcResult.getResponse().getHeader("Authorization");

        this.artistDto = new ArtistDto("artist3", "Artiste34", "email@email.fr", "Paris", "artist3", "Je suis l'artiste 3 et je m'éclate dans tout ce que je fais", new String[]{SecurityConstants.ROLE_USER});
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

    /**
     * Should find all users in the database.
     * @throws Exception
     */
    @Test
    public void findAll() throws Exception {
        getRequest("/artists/")
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(artistRepository.findAll())));
    }

    /**
     * Should save new artist in the database
     */
    @Test
    public void signup() throws Exception{
        Assertions.assertThat(artistRepository.findAll()).hasSize(6);
        mvc.perform(put("/artists/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.artistDto)))
                .andExpect(status().isOk());
        Assertions.assertThat(artistRepository.findAll()).hasSize(7);
    }

    /**
     * Should throw AlreadyExistsException when username already exists.
     * @throws Exception
     */
    @Test
    public void signupWithUsernameThatAlreadyExists() throws Exception{
        Assertions.assertThat(artistRepository.findAll()).hasSize(6);
        this.artistDto.setUsername("echo");
        mvc.perform(put("/artists/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.artistDto)))
                .andExpect(status().is(401))
                .andExpect(content().json("{\"message\":\"The username echo already exist.\"}", false));
        Assertions.assertThat(artistRepository.findAll()).hasSize(6);
    }
}
