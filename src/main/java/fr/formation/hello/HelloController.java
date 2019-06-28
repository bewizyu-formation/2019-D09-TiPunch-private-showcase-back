package fr.formation.hello;

import fr.formation.security.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The type Hello controller.
 */
@RestController
@RequestMapping("/hello")
@Secured(SecurityConstants.ROLE_USER)
public class HelloController {

	/**
	 * The Logger.
	 */
	Logger logger = LoggerFactory.getLogger(HelloController.class);

	/**
	 * Say hello admin hello.
	 *
	 * @return the hello
	 */
	@GetMapping("/admin")
	@Secured(SecurityConstants.ROLE_ADMIN)
	Hello sayHelloAdmin() {
		return new Hello("Hello Admin!");
	}

	/**
	 * Say hello user hello.
	 *
	 * @return the hello
	 */
	@GetMapping("/user")
	Hello sayHelloUser() {
		return new Hello("Hello User!");
	}

	/**
	 * Handle form upload string.
	 *
	 * @param name the name
	 * @param file the file
	 *
	 * @return the string
	 *
	 * @throws IOException the io exception
	 */
	@PostMapping("/upload")
	public String handleFormUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file)
			throws IOException {

		if (!file.isEmpty()) {

			// Vous pouvez trouvez dans ce post un exemple pour sauvegarder un fichier sur le filesystem
			// https://grokonez.com/frontend/angular/angular-4-uploadget-multipartfile-tofrom-spring-boot-server
			logger.info("File Name : " + name);
			logger.info("File Type : " + file.getContentType());

			byte[] bytes = file.getBytes();

			// For the test, we gave txt files that we can display
			// Remove all the code below for production !

			return new String(bytes, "UTF-8");
		}

		return "redirect:uploadFailure";
	}

	/**
	 * Upload failure string.
	 *
	 * @return the string
	 */
	@GetMapping("/uploadFailure")
	public String uploadFailure() {
		return "Upload Failure !";

	}

}
