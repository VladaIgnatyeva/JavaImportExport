package Server.Handlers.ImportHandler;

import Models.ImportModels.AddImport.AddImportRequest;
import Models.ImportModels.AddImport.AddImportResponse;
import Server.Handlers.Handler;
import Server.Repository.ImportRepository;
import Server.Repository.ImportRepositoryImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AddImportHandler implements Handler{
    private ImportRepository importRepository;

    public AddImportHandler(ImportRepository import_sRepository){
        this.importRepository = import_sRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        AddImportRequest import_ = objectMapper.readValue(data, AddImportRequest.class);
        AddImportResponse addImportResponse = new AddImportResponse();
        addImportResponse.setResult(importRepository.addImport(import_.getImport()));
        return addImportResponse;
    }
}
