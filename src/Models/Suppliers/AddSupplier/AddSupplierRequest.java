package Models.Suppliers.AddSupplier;


import Models.Supplier;

public class AddSupplierRequest {
    private Supplier supplier;

    public AddSupplierRequest(){  }

    public AddSupplierRequest(Supplier Supplier){
        this.supplier = Supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
