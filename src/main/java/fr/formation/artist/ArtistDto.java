package fr.formation.artist;

public class ArtistDto {

    private String username;

    private String password;

    private String email;

    private String city;

    private String name;

    private String description;

    private String[] roles;

    public ArtistDto() {
    }

    public ArtistDto(String username, String password, String email, String city, String name, String description, String[] roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
        this.name = name;
        this.description = description;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
