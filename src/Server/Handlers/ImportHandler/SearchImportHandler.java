package Server.Handlers.ImportHandler;

import Models.ImportModels.SearchImport.SearchImportRequest;
import Models.ImportModels.SearchImport.SearchImportResponse;
import Server.Handlers.Handler;
import Server.Repository.ImportRepository;
import Server.Repository.ImportRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SearchImportHandler implements Handler {
    private ImportRepository importRepository;

    public SearchImportHandler(ImportRepository importRepository){
        this.importRepository = importRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        SearchImportRequest import_ = objectMapper.readValue(data, SearchImportRequest.class);
        SearchImportResponse searchImportResponse = new SearchImportResponse();
        searchImportResponse.setResult(importRepository.getImportByID(import_.getImport()));
        return searchImportResponse;
    }
}
