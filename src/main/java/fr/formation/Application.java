package fr.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Class for configuration of SpringBoot
 *
 * @author
 */
@SpringBootApplication
public class Application {

	/**
	 * Main of the program that launch the SpringBoot application with current configuration
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

}
