package Server.Handlers.SupplierHandler;

import Models.Suppliers.DeleteSupplier.DeleteSupplierRequest;
import Models.Suppliers.DeleteSupplier.DeleteSupplierResponse;
import Server.Handlers.Handler;
import Server.Repository.SupplierRepositoryImpl;
import Server.Repository.SuppliersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DeleteSupplierHandler implements Handler {
    private SuppliersRepository suppliersRepository;

    public DeleteSupplierHandler(SuppliersRepository suppliersRepository){
        this.suppliersRepository = suppliersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        DeleteSupplierRequest suppliers = objectMapper.readValue(data, DeleteSupplierRequest.class);
        DeleteSupplierResponse deleteProductResponse = new DeleteSupplierResponse();
        deleteProductResponse.setResult(suppliersRepository.deleteSupplierByIdAndName(suppliers.getSupplier()));
        return deleteProductResponse;
    }
}
