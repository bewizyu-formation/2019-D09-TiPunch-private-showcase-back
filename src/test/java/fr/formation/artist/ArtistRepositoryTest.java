package fr.formation.artist;

import fr.formation.user.UserConstants;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class ArtistRepositoryTest {

    public final String[] ENGLISH_ERROR_MSGS = {"must not be null", "must be a well-formed email address", "size must be between " + ArtistConstants.MIN_LENGTH + " and " + ArtistConstants.MAX_LENGTH, "size must be between " + ArtistConstants.MIN_DESC + " and " + ArtistConstants.MAX_DESC};
    public final String[] FRENCH_ERROR_MSGS = {"ne peut pas être nul", "doit être une adresse email bien formée", "la taille doit être comprise entre " + ArtistConstants.MIN_LENGTH + " et " + ArtistConstants.MAX_LENGTH, "la taille doit être comprise entre " + ArtistConstants.MIN_DESC + " et " + ArtistConstants.MAX_DESC};

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private EntityManager entityManager;

    private Artist artistTest;
    private String[] errorsMsgs = ENGLISH_ERROR_MSGS;

    /**
     * Initialization of an artist used in the tests
     */
    @Before
    public void init(){
        this.artistTest = new Artist("username","Password1","email@email.fr","Paris","Artist Test","Je suis un Artiste Test et je m'éclate dans tout ce que je fais");
    }

    /**
     * Should save new artist in the Database
     */
    @Test
    public void createTest(){
        Assertions.assertThat(artistRepository.findAll()).hasSize(0);
        artistRepository.save(this.artistTest);
        Assertions.assertThat(artistRepository.findAll()).hasSize(1);
    }


    /**
     * Should throw exception when name artist is null
     */
    @Test
    public void createShouldThrowAnExceptionWhenNameIsNull(){
        Assertions.assertThatThrownBy(()->{
            this.artistTest.setName(null);
            artistRepository.save(artistTest);
            entityManager.flush();
        }).isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining(errorsMsgs[0]);
    }

    /**
     * Should throw an exception when artist description is null
     */
    @Test
    public void createShouldThrowAnExceptionWhenDescriptionIsNull(){
        Assertions.assertThatThrownBy(()->{
            this.artistTest.setDescription(null);
            artistRepository.save(artistTest);
            entityManager.flush();
        }).isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
                .hasMessageContaining(errorsMsgs[0]);
    }

    /**
     * Should throw an exception when artist name length is less than 2
     */
    @Test
    public void createShouldThrowExceptionWhenNameLengthIsLessThan2(){
        Assertions.assertThatThrownBy(()->{
            this.artistTest.setName("U");
            artistRepository.save(artistTest);
            entityManager.flush();
        }).isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
            .hasMessageContaining(errorsMsgs[2]);
    }

    /**
     * Should throw an exception when artist description length is less than 20
     */
    @Test
    public void createShouldThrowExceptionWhenDescriptionIsLessThan20(){
        Assertions.assertThatThrownBy(()->{
            this.artistTest.setDescription("description");
            artistRepository.save(artistTest);
            entityManager.flush();
        }).isExactlyInstanceOf(javax.validation.ConstraintViolationException.class)
           .hasMessageContaining(errorsMsgs[3]);
    }

    /**
     * Should find all artists list in the database
     */
    @Test
    public void findAll(){
        artistRepository.save(this.artistTest);
        Assertions.assertThat(artistRepository.findAll())
                .hasSize(1)
                .extracting("username","password","email","city","name","description")
                .contains(Tuple.tuple(
                        this.artistTest.getUsername(),
                        this.artistTest.getPassword(),
                        this.artistTest.getEmail(),
                        this.artistTest.getCity(),
                        this.artistTest.getName(),
                        this.artistTest.getDescription()
                ));
    }

    /**
     * Should delete the artist in the database
     */
    @Test
    public void delete(){
        artistRepository.save(this.artistTest);
        entityManager.flush();
        Assertions.assertThat(artistRepository.findAll()).hasSize(1);
        artistRepository.delete(this.artistTest);
        entityManager.flush();
        Assertions.assertThat(artistRepository.findAll()).hasSize(0);
    }

    /**
     * Should not delete the artist that is not in the database
     */
    @Test
    public void deleteWithBadArtist(){
        Assertions.assertThat(artistRepository.findAll()).hasSize(0);
        artistRepository.delete(this.artistTest);
        entityManager.flush();
        Assertions.assertThat(artistRepository.findAll()).hasSize(0);
    }

    /**
     * Should update the artist
     */
    @Test
    public void update(){
        artistRepository.save(this.artistTest);
        entityManager.flush();
        Assertions.assertThat(artistRepository.findAll()).hasSize(1);

        this.artistTest.setName("Modified Name");

        artistRepository.save(this.artistTest);
        entityManager.flush();
        Assertions.assertThat(artistRepository.findAll()).hasSize(1);
        Assertions.assertThat(artistRepository.findByUsername(this.artistTest.getUsername()))
                .extracting("username")
                .contains(this.artistTest.getUsername());
    }
}
