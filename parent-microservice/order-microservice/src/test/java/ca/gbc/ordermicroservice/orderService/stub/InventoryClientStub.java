package ca.gbc.ordermicroservice.orderService.stub;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class InventoryClientStub {
    public static void stubInventoryCall(String skuNumber, Integer quantity){
        stubFor(get(urlEqualTo("/api/inventory?skuNumber="+skuNumber+"&quantity="+quantity))
                .willReturn(aResponse()
                        .withStatus(200)    // The status code to expect from inventory
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")
                )
        );
    }
}
