package ca.gbc.productmicroservice.service;

import ca.gbc.productmicroservice.dto.ProductRequest;
import ca.gbc.productmicroservice.dto.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

// this can be injected

@Service
@Slf4j
public class ProductServiceimpl implements ProductService{
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return null;
    }

    @Override
    public String updateProduct(ProductRequest productRequest, String id) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {

    }
}
