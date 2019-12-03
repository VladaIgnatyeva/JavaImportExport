package Models.Products.UpdateProduct;

import Models.Product;

public class UpdateProductRequest {
    private Product product;

    public UpdateProductRequest(){  }

    public UpdateProductRequest(Product product){
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
