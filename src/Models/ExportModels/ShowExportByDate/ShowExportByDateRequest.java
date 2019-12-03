package Models.ExportModels.ShowExportByDate;

import Models.Export;

public class ShowExportByDateRequest {
    private Export export;

    public ShowExportByDateRequest(){  }

    public ShowExportByDateRequest(Export export){
        this.export = export;
    }

    public Export getExport() {
        return export;
    }

    public void setExport(Export export) {
        this.export = export;
    }
}
