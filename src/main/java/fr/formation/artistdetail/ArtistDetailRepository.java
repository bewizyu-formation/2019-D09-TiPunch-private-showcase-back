package fr.formation.artistdetail;

import fr.formation.artistdepartment.ArtistDepartment;
import fr.formation.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ArtistDetailRepository extends JpaRepository<ArtistDetail,Long> {


    //public List<ArtistDetail> findAllByArtistDepartments(ArtistDepartment artistDepartment);

    public List<ArtistDetail> findAllByDepartments(Department department);

    @Query("select ad from ArtistDetail ad where ad.departments ")
    public List<ArtistDetail> findAllByDepartment(Department department);

}
