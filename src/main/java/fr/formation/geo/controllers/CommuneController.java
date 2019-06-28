package fr.formation.geo.controllers;

import fr.formation.geo.model.Commune;
import fr.formation.geo.services.CommuneService;
import fr.formation.security.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Commune controller.
 */
@RestController
@Secured(SecurityConstants.ROLE_USER)
@RequestMapping("/communes")
public class CommuneController {

	private static final Logger log = LoggerFactory.getLogger(CommuneController.class);

	private CommuneService communeService;

	/**
	 * Instantiates a new Commune controller.
	 *
	 * @param communeService the commune service
	 */
	@Autowired
	public CommuneController(CommuneService communeService) {
		this.communeService = communeService;
	}

	/**
	 * Gets communes.
	 *
	 * @param nom the nom
	 *
	 * @return the communes
	 */
	@GetMapping("/")
	public ResponseEntity<List<Commune>> getCommunes(@RequestParam final String nom) {
		final List<Commune> communes = this.communeService.getCommunes(nom);
		return new ResponseEntity<>(communes, HttpStatus.OK);
	}

}
