package fr.formation.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that check the application status
 */
@RestController
@RequestMapping("/health")
public class HealthController {

	/**
	 * Check string.
	 *
	 * @return the string
	 */
	@GetMapping("/")
	public String check() {
		return "OK";
	}

}
