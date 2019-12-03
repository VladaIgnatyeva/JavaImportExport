package Models.ExportModels.ShowExportByIDBuyer;

import Models.ExportForTable;

import java.util.ArrayList;

public class ShowExportByIDBuyerResponse {
    private ArrayList<ExportForTable> export;

    public ShowExportByIDBuyerResponse(){}

    public ShowExportByIDBuyerResponse(ArrayList<ExportForTable> export){
        this.export = export;
    }

    public ArrayList<ExportForTable> getExport() {
        return export;
    }

    public void setExport(ArrayList<ExportForTable> export) {
        this.export = export;
    }
}
