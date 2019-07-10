package fr.formation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.formation.artist.ArtistController;
import fr.formation.user.UserController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CheckNotTakenControlerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    CheckNotTakenControler.CheckUniqueDto check;

    @Before
    public void init() {
        this.check = new CheckNotTakenControler().new CheckUniqueDto();
    }
    /**
     * Should return CheckUniqueDTO with usernameNotTaken = false if the username exists.
     * @throws Exception
     */
    @Test
    public void checkUsernameWithUsernameThatExist() throws Exception {
        //By default, the value of usernameNotTaken is false when CheckUniqueDto is instantiate
        mvc.perform(get("/users/checkUsernameNotTaken/" + "user"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(check)));
    }

    /**
     * Should return CheckUniqueDTO with usernameNotTaken = true if the username exists.
     * @throws Exception
     */
    @Test
    public void checkUsernameWithUsernameThatNotExist() throws Exception {
        check.setUsernameNotTaken(true);
        mvc.perform(get("/users/checkUsernameNotTaken/" + "New user"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(check)));
    }


    /**
     * Should return CheckUniqueDTO with usernameNotTaken = false if the username exists.
     * @throws Exception
     */
    @Test
    public void checkArtistNameWhenExist() throws Exception {
        //By default, the value of artistNameNotTaken is false when CheckUniqueDto is instantiate
        mvc.perform(get("/artists/checkArtistNameNotTaken/" + "artist1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(check)));
    }

    /**
     * Should return CheckUniqueDTO with usernameNotTaken = true if the username exists.
     * @throws Exception
     */
    @Test
    public void checkArtistNameWithUsernameThatNotExist() throws Exception {
        check.setArtistNameNotTaken(true);
        mvc.perform(get("/artists/checkArtistNameNotTaken/" + "New artist"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(check)));
    }

}