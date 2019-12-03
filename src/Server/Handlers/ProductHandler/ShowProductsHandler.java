package Server.Handlers.ProductHandler;

import Models.Product;
import Models.Products.ShowProductsResponse;
import Server.Handlers.Handler;
import Server.Repository.ProductRepository;
import Server.Repository.ProductsRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;

public class ShowProductsHandler implements Handler {
    private ProductRepository productsRepository;

    public ShowProductsHandler(ProductRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ArrayList<Product> products = productsRepository.getProducts();
        return new ShowProductsResponse(products);
    }
}
