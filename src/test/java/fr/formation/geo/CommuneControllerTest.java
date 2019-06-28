package fr.formation.geo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommuneControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldReturnValidResponse() throws Exception {

		MvcResult mvcResult = mvc.perform(formLogin("/login").user("user").password("user")).andReturn();
		String authorizationHeader = mvcResult.getResponse().getHeader("Authorization");

		mvc.perform(
				get("/communes/?nom=Amiens")
				.header("Authorization", authorizationHeader))
				.andExpect(status().isOk()
		)
		.andExpect(content().json("[\n" +
				"    {\n" +
				"        \"nom\": \"Amiens\",\n" +
				"        \"code\": \"80021\",\n" +
				"        \"codesPostaux\": [\n" +
				"            \"80000\",\n" +
				"            \"80080\",\n" +
				"            \"80090\"\n" +
				"        ],\n" +
				"        \"codeDepartement\": \"80\",\n" +
				"        \"codeRegion\": \"32\",\n" +
				"        \"_score\": 1\n" +
				"    },\n" +
				"    {\n" +
				"        \"nom\": \"Dreuil-lès-Amiens\",\n" +
				"        \"code\": \"80256\",\n" +
				"        \"codesPostaux\": [\n" +
				"            \"80470\"\n" +
				"        ],\n" +
				"        \"codeDepartement\": \"80\",\n" +
				"        \"codeRegion\": \"32\",\n" +
				"        \"_score\": 0.6809910623997033\n" +
				"    }\n" +
				"]"))
		.andExpect(authenticated().withUsername("user"));
	}

}
