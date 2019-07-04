package fr.formation.user;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    private User userTest;

    /**
     * Initialization of a user used in the tests.
     */
    @Before
    public void init() {
        this.userTest = new User("Utilisateur test", "pass", "email@email.fr", "Paris");
    }

    /**
     * Should save new user in the database.
     */
    @Test
    public void createTest() {
        Assertions.assertThat(userRepository.findAll()).hasSize(5);
        userRepository.save(this.userTest);
        Assertions.assertThat(userRepository.findAll()).hasSize(6);
    }

    /**
     * Should throw exception when username is null.
     */
    @Test
    public void createShouldThrowExceptionWhenUsernameIsNull() {
        Assertions.assertThatThrownBy(() -> {
            this.userTest.setUsername(null);
            userRepository.save(userTest);
            entityManager.flush();
        })
                .isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne peut pas être nul");
    }

    /**
     * Should throw exception when password is null.
     */
    @Test
    public void createShouldThrowExceptionWhenPasswordIsNull() {
        Assertions.assertThatThrownBy(() -> {
            this.userTest.setPassword(null);
            userRepository.save(userTest);
            entityManager.flush();
        })
                .isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne peut pas être nul");
    }

    /**
     * Should throw exception when email is null.
     */
    @Test
    public void createShouldThrowExceptionWhenEmailIsNull() {
        Assertions.assertThatThrownBy(() -> {
            this.userTest.setEmail(null);
            userRepository.save(userTest);
            entityManager.flush();
        })
                .isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne peut pas être nul");
    }

    /**
     * Should throw exception when city is null.
     */
    @Test
    public void createShouldThrowExceptionWhenCityIsNull() {
        Assertions.assertThatThrownBy(() -> {
            this.userTest.setCity(null);
            userRepository.save(userTest);
            entityManager.flush();
        })
                .isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("ne peut pas être nul");
    }

    /**
     * Should throw exception when username is less than 2 characters.
     */
    @Test
    public void createShouldThrowExceptionWhenUsernameIsLessThan2() {
        Assertions.assertThatThrownBy(() -> {
            this.userTest.setUsername("U");
            userRepository.save(userTest);
            entityManager.flush();
        })
                .isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("la taille doit être comprise entre 2 et 150");
    }

    /**
     * Should throw exception when email is malformed.
     */
    @Test
    public void createShouldThrowExceptionWhenEmailIsNotAnEmail() {
        Assertions.assertThatThrownBy(() -> {
            this.userTest.setEmail("email@");
            userRepository.save(userTest);
            entityManager.flush();
        })
                .isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining("doit être une adresse email bien formée");
    }

    /**
     * Should find the user with his username.
     */
    @Test
    public void findByUsername() {
        userRepository.save(this.userTest);
        entityManager.flush();
        User user = userRepository.findByUsername(this.userTest.getUsername());
        Assertions.assertThat(user).extracting("username").containsExactly(this.userTest.getUsername());
        Assertions.assertThat(user).extracting("email").containsExactly(this.userTest.getEmail());
        Assertions.assertThat(user).extracting("city").containsExactly(this.userTest.getCity());
    }

    /**
     * Should return null when the user is not found with a bad username.
     */
    @Test
    public void findByUsernameWhenUnknownUsername() {
        userRepository.save(this.userTest);
        entityManager.flush();
        User user = userRepository.findByUsername("Unknown username");
        Assertions.assertThat(user).isNull();
    }

    /**
     * Should return null when username is null.
     */
    @Test
    public void findByUsernameWhenUsernameIsNull() {
        userRepository.save(this.userTest);
        entityManager.flush();
        User user = userRepository.findByUsername(null);
        Assertions.assertThat(user).isNull();
    }

    /**
     * Should find all users list in the database.
     */
    @Test
    public void findAll() {
        userRepository.save(this.userTest);
        Assertions.assertThat(userRepository.findAll())
                .hasSize(6)
                .extracting("username", "email", "city")
                .contains(Tuple.tuple(this.userTest.getUsername(), this.userTest.getEmail(), this.userTest.getCity()));
    }

    /**
     * Should delete the user.
     */
    @Test
    public void delete() {
        userRepository.save(this.userTest);
        entityManager.flush();
        Assertions.assertThat(userRepository.findAll()).hasSize(6);
        userRepository.delete(this.userTest);
        entityManager.flush();
        Assertions.assertThat(userRepository.findAll()).hasSize(5);
    }

    /**
     * Should not delete the user that is not in database.
     */
    @Test
    public void deleteWithBadUser() {
        Assertions.assertThat(userRepository.findAll()).hasSize(5);
        userRepository.delete(this.userTest);
        entityManager.flush();
        Assertions.assertThat(userRepository.findAll()).hasSize(5);
    }

    /**
     * Should update the user.
     */
    @Test
    public void update() {
        userRepository.save(this.userTest);
        entityManager.flush();
        Assertions.assertThat(userRepository.findAll()).hasSize(6);

        this.userTest.setUsername("Modified username");
        this.userTest.setCity("Modified city");

        userRepository.save(this.userTest);
        entityManager.flush();
        Assertions.assertThat(userRepository.findAll()).hasSize(6);
        Assertions.assertThat((userRepository.findByUsername(this.userTest.getUsername())))
                .extracting("username", "email", "city")
                .contains(this.userTest.getUsername(), this.userTest.getEmail(), this.userTest.getCity());

    }




}