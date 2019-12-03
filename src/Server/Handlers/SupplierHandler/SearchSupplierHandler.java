package Server.Handlers.SupplierHandler;

import Models.Suppliers.SearchSupplier.SearchSupplierRequest;
import Models.Suppliers.SearchSupplier.SearchSupplierResponse;
import Server.Handlers.Handler;
import Server.Repository.SupplierRepositoryImpl;
import Server.Repository.SuppliersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SearchSupplierHandler implements Handler {
    private SuppliersRepository suppliersRepository;

    public SearchSupplierHandler(SuppliersRepository suppliersRepository){
        this.suppliersRepository = suppliersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        SearchSupplierRequest product = objectMapper.readValue(data, SearchSupplierRequest.class);
        SearchSupplierResponse searchSupplierResponse = new SearchSupplierResponse();
        searchSupplierResponse.setResult(suppliersRepository.getSupplierByID(product.getSupplier()));
        return searchSupplierResponse;
    }
}
