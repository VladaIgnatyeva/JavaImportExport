package Models.Products;
import Models.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowProductsResponse implements Serializable {
    private ArrayList<Product> products;

    public ShowProductsResponse(){}

    public ShowProductsResponse(ArrayList<Product> products){
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
