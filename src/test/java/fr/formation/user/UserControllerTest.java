package fr.formation.user;

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
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    /**
     * Used to parse java object to JSON
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Used to do request with identification
     */
    private String authorizationHeader;

    private User userTest;

    /**
     * Initialization of user used in tests and identification for the requests.
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        MvcResult mvcResult = mvc.perform(formLogin("/login").user("admin").password("Admin1234")).andReturn();
        this.authorizationHeader = mvcResult.getResponse().getHeader("Authorization");

        this.userTest = userRepository.findByUsername("user");
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
     * Should save new user in the database.
     * @throws Exception
     */
    @Test
    public void signup() throws Exception{
        Assertions.assertThat(userRepository.findAll()).hasSize(7);
        mvc.perform(put("/users/")
                .param("username", "New User")
                .param("password", "Password2")
                .param("email", "email@email.fr")
                .param("city", "Paris")
                .param("roles", SecurityConstants.ROLE_USER))
                .andExpect(status().isOk());
        Assertions.assertThat(userRepository.findAll()).hasSize(8);
    }

    /**
     * Should throw AlreadyExistsException when username already exists.
     * @throws Exception
     */
    @Test
    public void signupWithUsernameThatAlreadyExists() throws Exception{
        Assertions.assertThat(userRepository.findAll()).hasSize(7);
        mvc.perform(put("/users/")
                .param("username", "user")
                .param("password", "Password2")
                .param("email", "email@email.fr")
                .param("city", "Paris")
                .param("roles", SecurityConstants.ROLE_USER))
                .andExpect(status().is(401))
                .andExpect(content().json("{\"message\":\"The username user already exist.\"}", false));
        Assertions.assertThat(userRepository.findAll()).hasSize(7);
    }

    /**
     * Should find the user with his username.
     * @throws Exception
     */
    @Test
    public void findByUsername() throws Exception {
        getRequest("/users/" + userTest.getUsername())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(this.userTest)));

    }

    /**
     * Should throw NotFoundException if the username doesn't exist.
     * @throws Exception
     */
    @Test
    public void findByUsernameWithBadUsername() throws Exception {
        getRequest("/users/" + "unknownUser")
                .andExpect(status().is(404))
                .andExpect(content().json("{\"message\":\"Username unknownUser not found in the database\"}", false));
    }


    /**
     * Should find all users in the database.
     * @throws Exception
     */
    @Test
    public void findAll() throws Exception {

        ResultActions str = getRequest("/users/")
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userRepository.findAll())));
    }

    /**
     * Should return CheckUniqueDTO with usernameNotTaken = false if the username exists.
     * @throws Exception
     */
    @Test
    public void checkUsernameWithUsernameThatExist() throws Exception {
        UserController.CheckUniqueDto check = new UserController().new CheckUniqueDto();
        //By default, the value of usernameNotTaken is false when CheckUniqueDto is instantiate
        getRequest("/users/checkUsernameNotTaken/" + "user")
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(check)));
    }

    /**
     * Should return CheckUniqueDTO with usernameNotTaken = true if the username exists.
     * @throws Exception
     */
    @Test
    public void checkUsernameWithUsernameThatNotExist() throws Exception {
        UserController.CheckUniqueDto check = new UserController().new CheckUniqueDto();
        check.setUsernameNotTaken(true);
        getRequest("/users/checkUsernameNotTaken/" + "New user")
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(check)));
    }

    @Test
    public void checkArtistName() {
    }
}