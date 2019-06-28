package fr.formation.geo.services.impl;

import fr.formation.geo.GeoApiConstants;
import fr.formation.geo.model.Commune;
import fr.formation.geo.services.CommuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The type Commune service.
 */
@Service
@Transactional
public class CommuneServiceImpl implements CommuneService {

	private static final Logger log = LoggerFactory.getLogger(CommuneServiceImpl.class);

	private RestTemplate restTemplate;

	/**
	 * Instantiates a new Commune service.
	 *
	 * @param restTemplate the rest template
	 */
	@Autowired
	public CommuneServiceImpl(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Commune> getCommunes(String nom) {

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(GeoApiConstants.GEO_API_BASE_URL + GeoApiConstants.RESOURCE_COMMUNE)
				.queryParam(GeoApiConstants.PARAMS_NOM, nom)
				.queryParam(GeoApiConstants.PARAMS_FIELDS, GeoApiConstants.COMMUNE_FIELDS_VALUES);

		return this.restTemplate.getForObject(
				builder.toUriString(),
				List.class
		);
	}

}
