package Server.Handlers.ProductHandler;

import Models.Products.DeleteProduct.DeleteProductRequest;
import Models.Products.DeleteProduct.DeleteProductResponse;
import Server.Handlers.Handler;
import Server.Repository.ProductRepository;
import Server.Repository.ProductsRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DeleteProductHandler implements Handler {
    private ProductRepository productsRepository;

    public DeleteProductHandler(ProductRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        DeleteProductRequest product = objectMapper.readValue(data, DeleteProductRequest.class);
        DeleteProductResponse deleteProductResponse = new DeleteProductResponse();
        deleteProductResponse.setResult(productsRepository.deleteProductByIdAndName(product.getProduct()));
        return deleteProductResponse;
    }
}
