package Models.Suppliers.DeleteSupplier;

import Models.Supplier;

public class DeleteSupplierRequest {
    private Supplier supplier;

    public DeleteSupplierRequest(){  }

    public DeleteSupplierRequest(Supplier supplier){
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
