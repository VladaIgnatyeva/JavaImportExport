package Models.Suppliers;

import Models.Supplier;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowSuppliersResponse implements Serializable {
    private ArrayList<Supplier> supplier;

    public ShowSuppliersResponse(){}

    public ShowSuppliersResponse(ArrayList<Supplier> supplier){
        this.supplier = supplier;
    }

    public ArrayList<Supplier> getSupplier() {
        return supplier;
    }

    public void setSupplier(ArrayList<Supplier> supplier) {
        this.supplier = supplier;
    }
}
