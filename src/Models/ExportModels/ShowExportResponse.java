package Models.ExportModels;

import Models.ExportForTable;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowExportResponse implements Serializable {
    private ArrayList<ExportForTable> export;

    public ShowExportResponse(){}

    public ShowExportResponse(ArrayList<ExportForTable> export){
        this.export = export;
    }

    public ArrayList<ExportForTable> getExport() {
        return export;
    }

    public void setExport(ArrayList<ExportForTable> export) {
        this.export = export;
    }
}
