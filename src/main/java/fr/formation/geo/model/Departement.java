package fr.formation.geo.model;

import java.io.Serializable;

/**
 * The type Departement.
 */
public class Departement implements Serializable {

	private String nom;

	private String code;

	private String codeRegion;

	/**
	 * Create a Departement
	 *
	 * @param nom        the nom
	 * @param code       the code
	 * @param codeRegion the code region
	 */
	public Departement(String nom, String code, String codeRegion) {
		this.nom = nom;
		this.code = code;
		this.codeRegion = codeRegion;
	}

	/**
	 * Create a Departement
	 */
	public Departement() {
	}

	/**
	 * Gets nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Sets nom.
	 *
	 * @param nom the nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets code region.
	 *
	 * @return the code region
	 */
	public String getCodeRegion() {
		return codeRegion;
	}

	/**
	 * Sets code region.
	 *
	 * @param codeRegion the code region
	 */
	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}
}
