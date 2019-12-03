package Server.Handlers.ImportHandler;

import Models.Buyers.DeleteBuyer.DeleteBuyersRequest;
import Models.Buyers.DeleteBuyer.DeleteBuyersResponse;
import Models.ImportModels.DeleteImport.DeleteImportRequest;
import Models.ImportModels.DeleteImport.DeleteImportResponse;
import Server.Handlers.Handler;
import Server.Repository.BuyersRepositoryImpl;
import Server.Repository.ImportRepository;
import Server.Repository.ImportRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DeleteImportHandler implements Handler {
    private ImportRepository importRepository;

    public DeleteImportHandler(ImportRepository importRepository){
        this.importRepository = importRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        DeleteImportRequest import_ = objectMapper.readValue(data, DeleteImportRequest.class);
        DeleteImportResponse deleteImportResponse = new DeleteImportResponse();
        deleteImportResponse.setResult(importRepository.deleteImportById(import_.getImport()));
        return deleteImportResponse;
    }
}
