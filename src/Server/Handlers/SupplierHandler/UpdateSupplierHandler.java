package Server.Handlers.SupplierHandler;

import Models.Supplier;
import Models.Suppliers.UpdateSupplier.UpdateSupplierRequest;
import Models.Suppliers.UpdateSupplier.UpdateSupplierResponse;
import Server.Handlers.Handler;
import Server.Repository.SupplierRepositoryImpl;
import Server.Repository.SuppliersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UpdateSupplierHandler implements Handler {
    private SuppliersRepository supplierRepository;

    public UpdateSupplierHandler(SuppliersRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        UpdateSupplierRequest supplier = objectMapper.readValue(data, UpdateSupplierRequest.class);
        UpdateSupplierResponse updateSupplierResponse = new UpdateSupplierResponse();
        updateSupplierResponse.setResult(supplierRepository.updateSupplier(supplier.getSupplier()));
        return updateSupplierResponse;
    }
}
