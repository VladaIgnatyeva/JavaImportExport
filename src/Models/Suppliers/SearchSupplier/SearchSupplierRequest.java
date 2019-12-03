package Models.Suppliers.SearchSupplier;

import Models.Supplier;

public class SearchSupplierRequest {
    private Supplier supplier;

    public SearchSupplierRequest(){  }

    public SearchSupplierRequest(Supplier supplier){
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
