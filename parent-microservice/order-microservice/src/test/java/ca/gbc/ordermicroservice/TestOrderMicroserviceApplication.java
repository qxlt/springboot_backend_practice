package ca.gbc.ordermicroservice;

import org.springframework.boot.SpringApplication;

public class TestOrderMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderMicroserviceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
