package fr.formation.user;

import javax.persistence.*;

/**
 * The type User role.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;

	@Column(name = "userid")
	private Long userId;

	@Column(name = "role")
	private String role;

	/**
	 * Gets role id.
	 *
	 * @return the role id
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * Sets role id.
	 *
	 * @param roleId the role id
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets user id.
	 *
	 * @param userId the user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets role.
	 *
	 * @param role the role
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
