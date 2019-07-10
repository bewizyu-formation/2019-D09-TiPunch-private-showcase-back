package fr.formation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.formation.artist.ArtistController;
import fr.formation.user.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CheckNotTakenControlerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CheckNotTakenControler checkControler;

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
        mvc.perform(get("/checknottaken/username/" + "user"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(this.check)));
    }

    /**
     * Should return CheckUniqueDTO with usernameNotTaken = true if the username exists.
     * @throws Exception
     */
    @Test
    public void checkUsernameWithUsernameThatNotExist() throws Exception {
        this.check.setUsernameNotTaken(true);
        mvc.perform(get("/checknottaken/username/" + "New_user"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(this.check)));
    }


    /**
     * Should return CheckUniqueDTO with usernameNotTaken = false if the username exists.
     * @throws Exception
     */
    @Test
    public void checkArtistNameWhenExist() throws Exception {
        //By default, the value of artistNameNotTaken is false when CheckUniqueDto is instantiate
        mvc.perform(get("/checknottaken/artistname/" + "Artist_1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(this.check)));
    }

    /**
     * Should return CheckUniqueDTO with usernameNotTaken = true if the username exists.
     * @throws Exception
     */
    @Test
    public void checkArtistNameWithUsernameThatNotExist() throws Exception {
        this.check.setArtistNameNotTaken(true);
        mvc.perform(get("/checknottaken/artistname/" + "New_artist"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(this.check)));
    }

}