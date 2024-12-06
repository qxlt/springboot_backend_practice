package ca.gbc.ordermicroservice.orderClient;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(value = "inventory", url = "${inventory.service.url}")
@Slf4j
public interface InventoryClient {
//    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
    Logger log = LoggerFactory.getLogger(InventoryClient.class);
    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skuNumber, @RequestParam Integer quantity);

    default boolean fallbackMethod(String skuNumber, Integer quantity, Throwable throwable){
        log.info("Cannot get inventory for SKU {}, failure reason: {}", skuNumber, quantity,
                throwable.getMessage());
        return false;
    }


}
