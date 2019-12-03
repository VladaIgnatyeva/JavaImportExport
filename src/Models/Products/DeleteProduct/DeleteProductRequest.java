package Models.Products.DeleteProduct;

import Models.Product;

public class DeleteProductRequest {
    private Product product;

    public DeleteProductRequest(){  }

    public DeleteProductRequest(Product product){
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
