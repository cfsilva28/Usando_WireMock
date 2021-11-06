package testeApiWireMock;

import org.apache.http.impl.conn.Wire;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import io.restassured.RestAssured;

public class TesteApiWireMock {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);
	

	@Test
	public void testWithWireMock() {
		stubFor(post("/api/teste")
				.withHeader("Content-Type", containing("json"))
				.willReturn(
				ok()
				.withHeader("Content-Type", "application/json")
				.withBody("Usuario cadastrado com sucesso!")));

		RestAssured.given()
		.header("Content-Type", "application/json")
		.when()
		.post("http://localhost:8089/api/teste") 
				.then()
				.assertThat().log().all()
				.body(Matchers.equalTo("Usuario cadastrado com sucesso!"));

}

	@Test
	public void testeWireMockDelete() {
		stubFor(delete("/api/teste").withHeader("Content-Type", containing("json")).willReturn(
				ok()
				.withHeader("Content-Type", "application/json")
				.withBody("Usuario apagado com sucesso!")));

		RestAssured.given().header("Content-Type", "application/json")
		.when()
		.delete("http://localhost:8089/api/teste")
				.then().assertThat().log().all()
				.body(Matchers.equalTo("Usuario apagado com sucesso!"));

	}
	}
