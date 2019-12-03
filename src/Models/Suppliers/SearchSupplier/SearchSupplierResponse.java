package Models.Suppliers.SearchSupplier;

import Models.Supplier;

public class SearchSupplierResponse {
    private Supplier supplier;

    public SearchSupplierResponse(){  }

    public SearchSupplierResponse(Supplier supplier){
        this.supplier = supplier;
    }

    public Supplier getResult() {
        return supplier;
    }

    public void setResult(Supplier supplier) {
        this.supplier = supplier;
    }
}
