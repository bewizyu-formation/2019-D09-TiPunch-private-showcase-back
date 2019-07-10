package fr.formation.service;

import fr.formation.artist.ArtistService;
import fr.formation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checknottaken")
public class CheckNotTakenControler {

    @Autowired
    private UserService userService;

    @Autowired
    private ArtistService artistService;

    /**
     * Check if the username is free.
     * @param username
     * @return CheckUniqueDTO that contains the boolean usernameNotTaken
     */
    @GetMapping("/username/{username}")
    public CheckUniqueDto checkUsername(@PathVariable String username) {
        CheckUniqueDto check = new CheckUniqueDto();
        if(userService.findByUsername(username) == null) {
            check.setUsernameNotTaken(true);
        }
        return check;
    }


    /**
     * Check if the artist name is free.
     * @param artistName
     * @return CheckUniqueDTO that contains the boolean artistNameNotTaken
     */
    @GetMapping("/artistname/{artistName}")
    public CheckUniqueDto checkArtistName(@PathVariable String artistName) {
        CheckUniqueDto check = new CheckUniqueDto();
        if(artistService.findByName(artistName) == null) {
            check.setArtistNameNotTaken(true);
        }
        return check;
    }


    /**
     * DTO class used to transmit the results of tests about uniques values
     */
    class CheckUniqueDto {
        private boolean usernameNotTaken = false;
        private boolean artistNameNotTaken = false;

        /**
         * Instantiates a new CheckUniqueDTO
         */
        public CheckUniqueDto() {
        }

        /**
         * Gets usernameNotTaken
         * @return the boolean usernameNotTaken
         */
        public boolean isUsernameNotTaken() {
            return usernameNotTaken;
        }

        /**
         * Sets usernameNotTaken
         * @param usernameNotTaken
         */
        public void setUsernameNotTaken(boolean usernameNotTaken) {
            this.usernameNotTaken = usernameNotTaken;
        }

        /**
         * Gets artistNameNotTaken
         * @return the boolean artistNameNotTaken
         */
        public boolean isArtistNameNotTaken() {
            return artistNameNotTaken;
        }

        /**
         * Sets artistNameNotTaken
         * @param artistNameNotTaken
         */
        public void setArtistNameNotTaken(boolean artistNameNotTaken) {
            this.artistNameNotTaken = artistNameNotTaken;
        }
    }

}
