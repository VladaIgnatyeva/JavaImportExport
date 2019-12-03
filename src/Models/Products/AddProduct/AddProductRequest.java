package Models.Products.AddProduct;

import Models.Product;

public class AddProductRequest {
    private Product product;

    public AddProductRequest(){  }

    public AddProductRequest(Product product){
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
