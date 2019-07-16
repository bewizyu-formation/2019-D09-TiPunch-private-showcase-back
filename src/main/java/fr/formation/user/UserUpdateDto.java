package fr.formation.user;

public class UserUpdateDto {

    public String password;

    public String email;

    public UserUpdateDto() {
    }

    public UserUpdateDto(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
