package Server.Handlers.ExportHandler;

import Models.ExportModels.DeleteExport.DeleteExportRequest;
import Models.ExportModels.DeleteExport.DeleteExportResponse;
import Server.Handlers.Handler;
import Server.Repository.ExportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DeleteExportHandler implements Handler {
    private ExportRepository exportRepository;

    public DeleteExportHandler(ExportRepository exportRepository)
    {
        this.exportRepository = exportRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        DeleteExportRequest export = objectMapper.readValue(data, DeleteExportRequest.class);
        DeleteExportResponse deleteExportResponse = new DeleteExportResponse();
        deleteExportResponse.setResult(exportRepository.deleteExportById(export.getExport()));
        return deleteExportResponse;
    }
}
