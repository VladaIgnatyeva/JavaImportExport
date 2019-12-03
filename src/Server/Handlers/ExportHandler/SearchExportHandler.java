package Server.Handlers.ExportHandler;

import Models.ExportModels.SearchExport.SearchExportRequest;
import Models.ExportModels.SearchExport.SearchExportResponse;
import Server.Handlers.Handler;
import Server.Repository.ExportRepository;
import Server.Repository.ExportRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SearchExportHandler implements Handler {
    private ExportRepository importRepository;

    public SearchExportHandler(ExportRepository importRepository){
        this.importRepository = importRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        SearchExportRequest export = objectMapper.readValue(data, SearchExportRequest.class);
        SearchExportResponse searchExportResponse = new SearchExportResponse();
        searchExportResponse.setResult(importRepository.getExportByID(export.getExport()));
        return searchExportResponse;
    }
}
