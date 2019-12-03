package Models.Products.SearchProduct;

import Models.Product;

public class SearchProductRequest {
    private Product product;

    public SearchProductRequest(){  }

    public SearchProductRequest(Product product){
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
