package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistConstants;
import fr.formation.artist.ArtistRepository;
import fr.formation.department.Department;
import fr.formation.department.DepartmentRepository;
import fr.formation.department.DepartmentService;
import fr.formation.department.DepartmentServiceImpl;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan({"fr.formation.artist", "fr.formation.artistdetail", "fr.formation.department", "fr.formation.security"})
public class ArtistDetailRepositoryTest {

    @Autowired
    private ArtistDetailRepository artistDetailRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    private Artist artist;
    private ArtistDetail artistDetailTest;

    @Before
    public void init() {
        this.artist = artistRepository.findByName("Artist 1");

        this.artistDetailTest = new ArtistDetail();
        artistDetailTest.setArtiste(artist);
        artistDetailTest.setLongDescription("Description longue de l'artiste.");
        artistDetailTest.setSite("www.monsiteartisteperso.com");
        artistDetailTest.setPhoneNumber(0605040302);
        Set<Department> departments = new HashSet<>();
        departments.add(departmentServiceImpl.findByName("Vaucluse"));
        departments.add(departmentServiceImpl.findByName("Isère"));
        artistDetailTest.setDepartments(departments);
    }

    @Test
    public void saveTest() {
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(0);
        artistDetailRepository.save(artistDetailTest);
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(1);
    }

    @Test
    public void findAll() {
        artistDetailRepository.save(artistDetailTest);
        Assertions.assertThat(artistDetailRepository.findAll())
                .hasSize(1)
                .extracting("longDescription", "phoneNumber")
                .contains(Tuple.tuple(
                        this.artistDetailTest.getLongDescription(),
                        this.artistDetailTest.getPhoneNumber()));
    }

    @Test
    public void delete() {
        artistDetailRepository.save(artistDetailTest);
        entityManager.flush();
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(1);
        artistDetailRepository.delete(artistDetailTest);
        entityManager.flush();
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(0);

    }

    @Test
    public void update(){
        artistDetailRepository.save(this.artistDetailTest);
        entityManager.flush();
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(1);

        this.artistDetailTest.setSite("www.updatedwebsite.com");

        artistDetailRepository.save(this.artistDetailTest);
        entityManager.flush();
        Assertions.assertThat(artistDetailRepository.findAll()).hasSize(1);
        Assertions.assertThat(artistDetailRepository.findById(this.artistDetailTest.getId()))
                .get()
                .extracting("site")
                .contains(this.artistDetailTest.getSite());
    }

    @Test
    public void findAllByDepartment() {
        artistDetailRepository.save(this.artistDetailTest);
        entityManager.flush();

        Assertions.assertThat(artistDetailRepository.findAllByDepartment(departmentServiceImpl.findByName("Vaucluse")))
                .hasSize(1)
                .contains(artistDetailTest);

    }
}