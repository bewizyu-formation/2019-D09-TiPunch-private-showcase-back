package fr.formation.security;

/**
 * The type Security constants.
 */
public class SecurityConstants {

	/**
	 * The constant EXPIRATIONTIME.
	 */
	public static final long EXPIRATIONTIME = 864_000_000; // 10 days

	/**
	 * The constant SECRET.
	 */
	public static final String SECRET = "ThisIsASecret"; // todo : replace with env token

	/**
	 * The constant TOKEN_PREFIX.
	 */
	public static final String TOKEN_PREFIX = "Bearer";

	/**
	 * The constant HEADER_STRING.
	 */
	public static final String HEADER_STRING = "Authorization";

	/**
	 * The constant CLAIM_ROLES.
	 */
	public static final String CLAIM_ROLES = "roles";

	/**
	 * The constant ROLE_PREFIX.
	 */
	public static final String ROLE_PREFIX = "ROLE_";

	/**
	 * The constant ROLE_USER_SUFFIX.
	 */
	public static final String ROLE_USER_SUFFIX = "USER";

	/**
	 * The constant ROLE_ADMIN_SUFFIX.
	 */
	public static final String ROLE_ADMIN_SUFFIX = "ADMIN";

	/**
	 * The constant ROLE_USER.
	 */
	public static final String ROLE_USER = ROLE_PREFIX + ROLE_USER_SUFFIX;

	/**
	 * The constant ROLE_ADMIN.
	 */
	public static final String ROLE_ADMIN = ROLE_PREFIX + ROLE_ADMIN_SUFFIX;

}
