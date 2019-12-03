package Server.Handlers.ProductHandler;

import Models.Products.UpdateProduct.UpdateProductRequest;
import Models.Products.UpdateProduct.UpdateProductResponse;
import Server.Handlers.Handler;
import Server.Repository.ProductRepository;
import Server.Repository.ProductsRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UpdateProductHandler implements Handler {
    private ProductRepository productsRepository;

    public UpdateProductHandler(ProductRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        UpdateProductRequest product = objectMapper.readValue(data, UpdateProductRequest.class);
        UpdateProductResponse updateProductResponse = new UpdateProductResponse();
        updateProductResponse.setResult(productsRepository.updateProduct(product.getProduct()));
        return updateProductResponse;
    }
}
