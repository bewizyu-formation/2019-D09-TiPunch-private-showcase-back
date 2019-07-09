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

    /**
     * Initialization of artist used in test and authentication for requests
     */
    @Before
    public void init() throws Exception{
        MvcResult mvcResult = mvc.perform(formLogin("/login").user("artist1").password("Artist1")).andReturn();
        this.authorizationHeader = mvcResult.getResponse().getHeader("Authorization");
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

        ResultActions str = getRequest("/artists/")
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(artistRepository.findAll())));
    }

    /**
     * Should save new artist in the database
     */
    @Test
    public void signup() throws Exception{
        Assertions.assertThat(artistRepository.findAll()).hasSize(2);
        mvc.perform(put("/artists/")
                .param("username","artist3")
                .param("password","Artiste3")
                .param("email","email@email.fr")
                .param("city","Paris")
                .param("name","artist3")
                .param("description","Je suis l'artiste 3 et je m'éclate dans tout ce que je fais")
                .param("roles", SecurityConstants.ROLE_USER))
                .andExpect(status().isOk());
        Assertions.assertThat(artistRepository.findAll()).hasSize(3);
    }



}
