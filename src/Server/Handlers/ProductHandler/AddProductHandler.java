package Server.Handlers.ProductHandler;

import Models.Products.AddProduct.AddProductRequest;
import Models.Products.AddProduct.AddProductResponse;
import Server.Handlers.Handler;
import Server.Repository.ProductRepository;
import Server.Repository.ProductsRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AddProductHandler implements Handler {
    private ProductRepository productRepository;

    public AddProductHandler(ProductRepository productsRepository){
        this.productRepository = productsRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        AddProductRequest product = objectMapper.readValue(data, AddProductRequest.class);
        AddProductResponse addProductResult = new AddProductResponse();
        addProductResult.setResult(productRepository.addProduct(product.getProduct()));
        return addProductResult;
    }
}
