package Server.Handlers.ExportHandler;

import Models.ExportModels.ShowTopExportResponse;
import Models.TopProduct;
import Server.Handlers.Handler;
import Server.Repository.ExportRepository;

import java.io.IOException;
import java.util.ArrayList;

public class ShowTopExportHandler implements Handler {
    private ExportRepository exportsRepository;

    public ShowTopExportHandler(ExportRepository exportsRepository){
        this.exportsRepository = exportsRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ArrayList<TopProduct> exports = exportsRepository.getPopularExport();
        return new ShowTopExportResponse(exports);
    }}
