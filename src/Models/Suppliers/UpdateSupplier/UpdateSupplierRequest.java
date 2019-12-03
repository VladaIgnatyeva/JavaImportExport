package Models.Suppliers.UpdateSupplier;

import Models.Supplier;

public class UpdateSupplierRequest {
    private Supplier supplier;

    public UpdateSupplierRequest(){  }

    public UpdateSupplierRequest(Supplier supplier){
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
