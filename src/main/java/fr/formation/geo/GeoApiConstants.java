package fr.formation.geo;

/**
 * The type Geo api constants.
 */
public class GeoApiConstants {

	/**
	 * The constant GEO_API_BASE_URL.
	 */
	public static final String GEO_API_BASE_URL = "https://geo.api.gouv.fr";

	/**
	 * The constant RESOURCE_COMMUNE.
	 */
	public static final String RESOURCE_COMMUNE = "/communes";

	/**
	 * The constant RESOURCE_DEPARTEMENT.
	 */
	public static final String RESOURCE_DEPARTEMENT = "/departements";

	/**
	 * The constant PARAMS_FIELDS.
	 */
	public static final String PARAMS_FIELDS = "fields";

	/**
	 * The constant PARAMS_NOM.
	 */
	public static final String PARAMS_NOM = "nom";

	/**
	 * The constant PARAMS_CODE.
	 */
	public static final String PARAMS_CODE = "code";

	/**
	 * The constant COMMUNE_FIELDS_VALUES.
	 */
	public static final String COMMUNE_FIELDS_VALUES = "nom,code,codesPostaux,codeDepartement,codeRegion";

	/**
	 * The constant DEPARTEMENT_FIELDS_VALUES.
	 */
	public static final String DEPARTEMENT_FIELDS_VALUES = "nom,code,codeRegion";

}
