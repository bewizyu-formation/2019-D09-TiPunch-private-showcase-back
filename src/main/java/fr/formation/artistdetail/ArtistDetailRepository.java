package fr.formation.artistdetail;

import fr.formation.artistdepartment.ArtistDepartment;
import fr.formation.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ArtistDetailRepository extends JpaRepository<ArtistDetail,Long> {

    public List<ArtistDetail> findAllByArtistDepartments(ArtistDepartment artistDepartment);

}
