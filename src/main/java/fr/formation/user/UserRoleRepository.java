package fr.formation.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface User role repository.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	/**
	 * Find role by user name list.
	 *
	 * @param username the username
	 *
	 * @return the list
	 */
	@Query("select a.role from UserRole a, User b where b.username=?1 and a.userId=b.id")
	public List<String> findRoleByUserName(String username);

}
