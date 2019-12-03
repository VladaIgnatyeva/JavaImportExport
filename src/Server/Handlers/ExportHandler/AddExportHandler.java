package Server.Handlers.ExportHandler;

import Models.ExportModels.AddExport.AddExportRequest;
import Models.ExportModels.AddExport.AddExportResponse;
import Server.Handlers.Handler;
import Server.Repository.ExportRepository;
import Server.Repository.ExportRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AddExportHandler implements Handler{
    private ExportRepository exportRepository;

    public AddExportHandler(ExportRepository export_sRepositoryImpl){
        this.exportRepository = export_sRepositoryImpl;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        AddExportRequest export_ = objectMapper.readValue(data, AddExportRequest.class);
        AddExportResponse addExportResponse = new AddExportResponse();
        addExportResponse.setResult(exportRepository.addExport(export_.getExport()));
        return addExportResponse;
    }
}
