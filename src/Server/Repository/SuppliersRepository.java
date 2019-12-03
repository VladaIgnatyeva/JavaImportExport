package Server.Repository;

import Models.Supplier;

import java.util.ArrayList;

public interface SuppliersRepository {
    ArrayList<Supplier> getSuppliers();
    boolean addSupplier(Supplier supplier);
    boolean deleteSupplierByIdAndName(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    Supplier getSupplierByID(Supplier supplier);
    boolean updateSupplierNameByID(int id, String name);
    boolean updateSupplierAddressByID(int id, String address);
    boolean updateSupplierPhoneByID(int id, String phone);
    boolean updateSupplierNoteByID(int id, String note);
}
