package Server.Handlers.ExportHandler;

import Models.ExportForTable;
import Models.ExportModels.ShowExportByDate.ShowExportByDateRequest;
import Models.ExportModels.ShowExportResponse;
import Server.Handlers.Handler;
import Server.Repository.ExportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class ShowExportByIDUserHandler implements Handler {
    private ExportRepository exportsRepository;

    public ShowExportByIDUserHandler(ExportRepository exportsRepository){
        this.exportsRepository = exportsRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        ShowExportByDateRequest export = objectMapper.readValue(data, ShowExportByDateRequest.class);
        ArrayList<ExportForTable> exports = exportsRepository.getExportByIDUser(export.getExport());
        return new ShowExportResponse(exports);
    }}
