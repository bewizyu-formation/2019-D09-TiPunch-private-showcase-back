package fr.formation.artistdetail;

import fr.formation.artist.Artist;
import fr.formation.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface ArtistDetail repository.
 */
@Repository
public interface ArtistDetailRepository extends JpaRepository<ArtistDetail,Long> {

    public ArtistDetail findByArtist(Artist artist);

    /**
     * Find list of ArtistDetail including a specific department
     * @param department
     * @return artistDetail list
     */
    @Query("SELECT ad FROM ArtistDetail ad WHERE :department MEMBER OF ad.departments")
    public List<ArtistDetail> findAllByDepartment(@Param("department") Department department);

}
