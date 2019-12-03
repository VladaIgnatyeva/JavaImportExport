package Server.Handlers.ExportHandler;

import Models.ExportForTable;
import Models.ExportModels.ShowExportResponse;
import Server.Handlers.Handler;
import Server.Repository.ExportRepository;
import Server.Repository.ExportRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;

public class ShowExportHandler implements Handler {
    private ExportRepository exportsRepository;

    public ShowExportHandler(ExportRepository exportsRepository){
        this.exportsRepository = exportsRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ArrayList<ExportForTable> exports = exportsRepository.getExport();
        return new ShowExportResponse(exports);
    }
}
