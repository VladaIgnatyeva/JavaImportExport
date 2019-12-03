package Models.ExportModels;

import Models.TopProduct;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowTopExportResponse implements Serializable {
    private ArrayList<TopProduct> products;

    public ShowTopExportResponse(){}

    public ShowTopExportResponse(ArrayList<TopProduct> products){
        this.products = products;
    }

    public ArrayList<TopProduct> getExport() {
        return products;
    }

    public void setExport(ArrayList<TopProduct> products) {
        this.products = products;
    }
}
