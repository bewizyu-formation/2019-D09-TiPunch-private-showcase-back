package fr.formation.artist;

import fr.formation.user.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The type Artist.
 */
@Entity
@DiscriminatorValue("ARTIST")
public class Artist extends User {

    @Column(name="name")
    @NotNull
    @Size(min=ArtistConstants.MIN_LENGTH, max=ArtistConstants.MAX_LENGTH)
    private String name;

    @Column(name="description")
    @NotNull
    @Size(min=ArtistConstants.MIN_DESC)
    private String description;

    /**
     * Instantiates a new Artist
     */
    public Artist(){
        super();
    }

    /**
     * Instantiates a new Artist
     * @param username      the username
     * @param password      the password
     * @param email         the email
     * @param city          the city
     * @param name          the artist name
     * @param description   the description
     */
    public Artist(@NotNull @Size(min = 2, max = 150) String username,
                  @NotNull String password,
                  @NotNull @Email String email,
                  @NotNull @Size(min = 2, max = 200) String city,
                  @NotNull @Size(min=2,max=50) String name,
                  @NotNull @Size(min=20) String description){
        super(username, password, email, city);
        this.name = name;
        this.description = description;
    }

    /**
     * Get artist name
     * @return the artist name
     */
    public String getName() {
        return name;
    }

    /**
     * Set artist name
     * @param name the artist name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get artist description
     * @return artist description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set artist description
     * @param description the artist description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
