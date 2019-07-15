package fr.formation.geo.model;

import java.io.Serializable;
import java.util.Set;

/**
 * The type Commune.
 */
public class Commune implements Serializable {

	private String nom;

	private String code;

	private String codeDepartement;

	private Set<String> codesPostaux;

	private String codeRegion;

	private Integer population;

	private Double _score;

	/**
	 * Instantiates a new Commune.
	 *
	 * @param nom             the nom
	 * @param code            the code
	 * @param codeDepartement the code departement
	 * @param codesPostaux    the codes postaux
	 */
	public Commune(String nom, String code, Set<String> codesPostaux, String codeDepartement, String codeRegion, Integer population, Double _score ) {
		this.nom = nom;
		this.code = code;
		this.codeDepartement = codeDepartement;
		this.codesPostaux = codesPostaux;
		this.codeRegion = codeRegion;
		this._score = _score;
		this.population = population;
	}

	/**
	 * Instantiates a new Commune.
	 */
	public Commune() {
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
	 * Gets code departement.
	 *
	 * @return the code departement
	 */
	public String getCodeDepartement() {
		return codeDepartement;
	}

	/**
	 * Sets code departement.
	 *
	 * @param codeDepartement the code departement
	 */
	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	/**
	 * Gets codes postaux.
	 *
	 * @return the codes postaux
	 */
	public Set<String> getCodesPostaux() {
		return codesPostaux;
	}

	/**
	 * Sets codes postaux.
	 *
	 * @param codesPostaux the codes postaux
	 */
	public void setCodesPostaux(Set<String> codesPostaux) {
		this.codesPostaux = codesPostaux;
	}

	public String getCodeRegion() {
		return codeRegion;
	}

	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	public Double get_score() {
		return _score;
	}

	public void set_score(Double _score) {
		this._score = _score;
	}
}


