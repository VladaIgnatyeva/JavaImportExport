package Server.Handlers.ImportHandler;

import Models.ImportModels.UpdateImport.UpdateImportRequest;
import Models.ImportModels.UpdateImport.UpdateImportResponse;
import Server.Handlers.Handler;
import Server.Repository.ImportRepository;
import Server.Repository.ImportRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UpdateImportHandler implements Handler {
    private ImportRepository import_sRepository;

    public UpdateImportHandler(ImportRepository import_sRepository){
        this.import_sRepository = import_sRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        UpdateImportRequest import_ = objectMapper.readValue(data, UpdateImportRequest.class);
        UpdateImportResponse updateImportResponse = new UpdateImportResponse();
        updateImportResponse.setResult(import_sRepository.updateImport(import_.getImport()));
        return updateImportResponse;
    }
}
