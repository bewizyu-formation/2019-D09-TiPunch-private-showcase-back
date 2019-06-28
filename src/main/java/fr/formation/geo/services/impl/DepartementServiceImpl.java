package fr.formation.geo.services.impl;

import fr.formation.geo.GeoApiConstants;
import fr.formation.geo.model.Departement;
import fr.formation.geo.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The type Departement service.
 */
@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {

	private RestTemplate restTemplate;

	/**
	 * Instantiates a new Departement service.
	 *
	 * @param restTemplate the rest template
	 */
	@Autowired
	public DepartementServiceImpl(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Departement> getDepartement(String nom) {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(GeoApiConstants.GEO_API_BASE_URL + GeoApiConstants.RESOURCE_DEPARTEMENT)
				.queryParam(GeoApiConstants.PARAMS_NOM, nom)
				.queryParam(GeoApiConstants.PARAMS_FIELDS, GeoApiConstants.COMMUNE_FIELDS_VALUES);

		return this.restTemplate.getForObject(
				builder.toUriString(),
				List.class
		);
	}

	@Override
	public List<Departement> getDepartementByCode(String code) {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(GeoApiConstants.GEO_API_BASE_URL + GeoApiConstants.RESOURCE_DEPARTEMENT)
				.queryParam(GeoApiConstants.PARAMS_CODE, code)
				.queryParam(GeoApiConstants.PARAMS_FIELDS, GeoApiConstants.DEPARTEMENT_FIELDS_VALUES);

		return this.restTemplate.getForObject(
				builder.toUriString(),
				List.class
		);
	}

}
