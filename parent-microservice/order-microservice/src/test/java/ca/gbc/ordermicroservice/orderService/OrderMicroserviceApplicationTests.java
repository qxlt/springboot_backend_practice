package ca.gbc.ordermicroservice.orderService;

import ca.gbc.ordermicroservice.orderClient.InventoryClient;
import ca.gbc.ordermicroservice.orderService.stub.InventoryClientStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;



import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(InventoryClientStub.class)
@AutoConfigureWireMock(port = 0)
class OrderMicroserviceApplicationTests {

	@ServiceConnection
	static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest");

	@LocalServerPort
	private Integer port;

	//http://localhost:port/api/order

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		postgreSQLContainer.start();
	}


	@Test
	void placeOrderTest(){
		String requestBody = """
    			{
    				"skuNumber": "SKU001",
    				"price": "100.00",
    				"quantity": "10"
    			}
				""";

		// Mocking stub call
		InventoryClientStub.stubInventoryCall("SKU001", 10);

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/order")
				.then()
				.log().all()
				.statusCode(201)
				.body(equalTo("Order Placed Successfully"));
	}


	@Test
	void contextLoads() {
	}

}
