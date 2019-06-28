package fr.formation.geo.controllers;

import fr.formation.geo.model.Departement;
import fr.formation.geo.services.DepartementService;
import fr.formation.security.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Departement controller.
 */
@RestController
@RequestMapping("/departements")
@Secured(SecurityConstants.ROLE_USER)
public class DepartementController {

	private DepartementService departementService;

	/**
	 * Instantiates a new Departement controller.
	 *
	 * @param departementService the departement service
	 */
	@Autowired
	public DepartementController(DepartementService departementService) {
		this.departementService = departementService;
	}

	/**
	 * Gets departement.
	 *
	 * @param nom the nom
	 *
	 * @return the departement
	 */
	@GetMapping("/")
	public ResponseEntity<List<Departement>> getDepartement(@RequestParam final String nom) {
		final List<Departement> departements = this.departementService.getDepartement(nom);
		return new ResponseEntity<>(departements, HttpStatus.OK);
	}

	/**
	 * Gets departement by code.
	 *
	 * @param code the code
	 *
	 * @return the departement by code
	 */
	@GetMapping("/{code}")
	public ResponseEntity<List<Departement>> getDepartementByCode(@PathVariable(value = "code") final String code) {
		final List<Departement> departements = this.departementService.getDepartementByCode(code);
		return new ResponseEntity<>(departements, HttpStatus.OK);
	}
}
