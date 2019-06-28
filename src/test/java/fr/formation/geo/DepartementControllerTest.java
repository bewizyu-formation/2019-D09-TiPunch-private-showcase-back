package fr.formation.geo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepartementControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getDepartement() throws Exception {

		MvcResult mvcResult = mvc.perform(formLogin("/login").user("user").password("user")).andReturn();
		String authorizationHeader = mvcResult.getResponse().getHeader("Authorization");

		mvc.perform(
				get("/departements/?nom=Ain")
						.header("Authorization", authorizationHeader))
				.andExpect(status().isOk()
				)
				.andExpect(content().json("[\n" +
						"    {\n" +
						"        \"nom\": \"Ain\",\n" +
						"        \"code\": \"01\",\n" +
						"        \"codeRegion\": \"84\",\n" +
						"        \"_score\": 1\n" +
						"    }\n" +
						"]"))
				.andExpect(authenticated().withUsername("user"));
	}

	@Test
	public void getDepartementByCode() throws Exception {

		MvcResult mvcResult = mvc.perform(formLogin("/login").user("user").password("user")).andReturn();
		String authorizationHeader = mvcResult.getResponse().getHeader("Authorization");

		mvc.perform(
				get("/departements/01")
						.header("Authorization", authorizationHeader))
				.andExpect(status().isOk()
				)
				.andExpect(content().json("[{\"nom\":\"Ain\",\"code\":\"01\",\"codeRegion\":\"84\"}]"))
				.andExpect(authenticated().withUsername("user"));
	}

}
