package fr.formation.artistdetail;

import fr.formation.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ArtistDetailRepository extends JpaRepository<ArtistDetail,Long> {

    public Set<ArtistDetail> findAllByDepartments(Department department);

}
