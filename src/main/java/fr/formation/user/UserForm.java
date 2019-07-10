package fr.formation.user;

/**
 * Object that collect users infos from form.
 */
public class UserForm {

    private String username;

    private String password;

    private String email;

    private String city;

    private String[] roles;

    public UserForm(String username, String password, String email, String city, String[] roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
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

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
