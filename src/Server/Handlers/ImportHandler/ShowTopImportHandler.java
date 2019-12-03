package Server.Handlers.ImportHandler;

import Models.ImportForTable;
import Models.ImportModels.ShowImportResponse;
import Models.ImportModels.ShowTopImportResponse;
import Models.TopProduct;
import Server.Handlers.Handler;
import Server.Repository.ImportRepository;

import java.io.IOException;
import java.util.ArrayList;

public class ShowTopImportHandler implements Handler {
    private ImportRepository import_sRepository;

    public ShowTopImportHandler(ImportRepository import_sRepository){
        this.import_sRepository = import_sRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ArrayList<TopProduct> import_s = import_sRepository.getPopularImport();
        return new ShowTopImportResponse(import_s);
    }}
