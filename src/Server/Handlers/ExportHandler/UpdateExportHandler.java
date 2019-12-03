package Server.Handlers.ExportHandler;

import Models.ExportModels.UpdateExport.UpdateExportRequest;
import Models.ExportModels.UpdateExport.UpdateExportResponse;
import Server.Handlers.Handler;
import Server.Repository.ExportRepository;
import Server.Repository.ExportRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UpdateExportHandler implements Handler {
    private ExportRepository exportsRepository;

    public UpdateExportHandler(ExportRepository exportsRepository){
        this.exportsRepository = exportsRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        UpdateExportRequest export = objectMapper.readValue(data, UpdateExportRequest.class);
        UpdateExportResponse updateExportResponse = new UpdateExportResponse();
        updateExportResponse.setResult(exportsRepository.updateExport(export.getExport()));
        return updateExportResponse;
    }
}
