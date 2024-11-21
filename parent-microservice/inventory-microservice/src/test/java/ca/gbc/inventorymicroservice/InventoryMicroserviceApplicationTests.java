package ca.gbc.inventorymicroservice;

import ca.gbc.inventorymicroservice.service.InventoryService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryMicroserviceApplicationTests {

	@ServiceConnection
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");


	@LocalServerPort
	private Integer port;

	//http://localhost:port/api/inventory

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		postgreSQLContainer.start();
	}

	@Test
	void isInStockTest() {
		// Perform the test
		RestAssured.given()
				.queryParam("skuNumber", "SKU001")
				.queryParam("quantity", 10)
				.when()
				.get("http://localhost:"+port+"/api/inventory") // replace {port} with your server port, e.g., 8080
				.then()
				.statusCode(200)
				.body(equalTo("true"));
	}



	@Test
	void contextLoads() {
	}

}
