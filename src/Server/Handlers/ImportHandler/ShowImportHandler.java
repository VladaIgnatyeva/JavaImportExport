package Server.Handlers.ImportHandler;

import Models.ImportForTable;
import Models.ImportModels.ShowImportResponse;
import Server.Handlers.Handler;
import Server.Repository.ImportRepository;
import Server.Repository.ImportRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;

public class ShowImportHandler implements Handler {
    private ImportRepository import_sRepository;

    public ShowImportHandler(ImportRepository import_sRepository){
        this.import_sRepository = import_sRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ArrayList<ImportForTable> import_s = import_sRepository.getImport();
        return new ShowImportResponse(import_s);
    }}
