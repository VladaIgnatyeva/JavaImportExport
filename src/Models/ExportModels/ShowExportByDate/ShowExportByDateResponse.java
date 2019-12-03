package Models.ExportModels.ShowExportByDate;

import Models.ExportForTable;

import java.util.ArrayList;

public class ShowExportByDateResponse {
    private ArrayList<ExportForTable> export;

    public ShowExportByDateResponse(){}

    public ShowExportByDateResponse(ArrayList<ExportForTable> export){
        this.export = export;
    }

    public ArrayList<ExportForTable> getExport() {
        return export;
    }

    public void setExport(ArrayList<ExportForTable> export) {
        this.export = export;
    }
}
