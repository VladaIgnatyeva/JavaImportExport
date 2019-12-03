package Server.Repository;

import Models.Product;

import java.util.ArrayList;


public interface ProductRepository {

    ArrayList<Product> getProducts ();
    boolean addProduct(Product product);
    boolean deleteProductByIdAndName(Product product);
    Product getProductByID(Product product);
    boolean updateProduct(Product product);
    boolean updateProductNameByID(int id, String name);
    boolean updateProductUnitByID(int id, String Unit);
    boolean updateProductPriceByID(int id, String price);
    boolean updateProductNoteByID(int id, String note);
}


