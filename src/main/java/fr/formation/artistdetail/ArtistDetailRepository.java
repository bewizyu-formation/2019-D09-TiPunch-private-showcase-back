package fr.formation.artistdetail;

import fr.formation.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistDetailRepository extends JpaRepository<ArtistDetail,Long> {

    @Query("SELECT ad FROM ArtistDetail ad WHERE :department MEMBER OF ad.departments")
    public List<ArtistDetail> findAllByDepartment(@Param("department") Department department);

}
