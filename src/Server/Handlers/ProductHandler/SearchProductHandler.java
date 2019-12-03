package Server.Handlers.ProductHandler;

import Models.Products.SearchProduct.SearchProductRequest;
import Models.Products.SearchProduct.SearchProductResponse;
import Server.Handlers.Handler;
import Server.Repository.ProductRepository;
import Server.Repository.ProductsRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SearchProductHandler implements Handler {
    private ProductRepository productsRepository;

    public SearchProductHandler(ProductRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        SearchProductRequest product = objectMapper.readValue(data, SearchProductRequest.class);
        SearchProductResponse searchProductResponse = new SearchProductResponse();
        searchProductResponse.setResult(productsRepository.getProductByID(product.getProduct()));
        return searchProductResponse;
    }
}
