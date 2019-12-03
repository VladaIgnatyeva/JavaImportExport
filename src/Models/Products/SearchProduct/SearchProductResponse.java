package Models.Products.SearchProduct;

import Models.Product;

public class SearchProductResponse {
    private Product product;

    public SearchProductResponse(){  }

    public SearchProductResponse(Product product){
        this.product = product;
    }

    public Product getResult() {
        return product;
    }

    public void setResult(Product product) {
        this.product = product;
    }
}
