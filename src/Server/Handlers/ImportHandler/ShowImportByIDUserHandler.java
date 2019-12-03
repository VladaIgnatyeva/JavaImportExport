package Server.Handlers.ImportHandler;

import Models.ImportForTable;
import Models.ImportModels.ShowImportByDate.ShowImportByDateRequest;
import Models.ImportModels.ShowImportResponse;
import Server.Handlers.Handler;
import Server.Repository.ImportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class ShowImportByIDUserHandler implements Handler {
    private ImportRepository import_sRepository;

    public ShowImportByIDUserHandler(ImportRepository import_sRepository){
        this.import_sRepository = import_sRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ShowImportByDateRequest import_ = objectMapper.readValue(data, ShowImportByDateRequest.class);
        ArrayList<ImportForTable> import_s = import_sRepository.getImportByIDUser(import_.getImport());
        return new ShowImportResponse(import_s);
    }}
