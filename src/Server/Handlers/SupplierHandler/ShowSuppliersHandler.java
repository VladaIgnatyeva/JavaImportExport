package Server.Handlers.SupplierHandler;

import Models.Supplier;
import Models.Suppliers.ShowSuppliersResponse;
import Server.Handlers.Handler;
import Server.Repository.SupplierRepositoryImpl;
import Server.Repository.SuppliersRepository;

import java.io.IOException;
import java.util.ArrayList;

public class ShowSuppliersHandler implements Handler {
    private SuppliersRepository suppliersRepository;

    public ShowSuppliersHandler(SuppliersRepository suppliersRepository){
        this.suppliersRepository = suppliersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ArrayList<Supplier> suppliers = suppliersRepository.getSuppliers();
        return new ShowSuppliersResponse(suppliers);
    }
}
