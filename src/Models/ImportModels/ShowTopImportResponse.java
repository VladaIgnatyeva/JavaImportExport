package Models.ImportModels;

import Models.ImportForTable;
import Models.TopProduct;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowTopImportResponse  implements Serializable {
    private ArrayList<TopProduct> products;

    public ShowTopImportResponse(){}

    public ShowTopImportResponse(ArrayList<TopProduct> products){
        this.products = products;
    }

    public ArrayList<TopProduct> getImport() {
        return products;
    }

    public void setImport(ArrayList<TopProduct> products) {
        this.products = products;
    }
}
