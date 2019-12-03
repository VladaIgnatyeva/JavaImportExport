package Server.Handlers.SupplierHandler;

import Models.Suppliers.AddSupplier.AddSupplierRequest;
import Models.Suppliers.AddSupplier.AddSupplierResponse;
import Server.Handlers.Handler;
import Server.Repository.SupplierRepositoryImpl;
import Server.Repository.SuppliersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AddSupplierHandler implements Handler{
    private SuppliersRepository suppliersRepository;

    public AddSupplierHandler(SuppliersRepository suppliersRepository){
        this.suppliersRepository = suppliersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        AddSupplierRequest supplier = objectMapper.readValue(data, AddSupplierRequest.class);
        AddSupplierResponse addSupplierResponse = new AddSupplierResponse();
        addSupplierResponse.setResult(suppliersRepository.addSupplier(supplier.getSupplier()));
        return addSupplierResponse;
    }
}
