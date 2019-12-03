package Models.ExportModels.UpdateExport;

import Models.Export;

public class UpdateExportRequest {
    private Export export;

    public UpdateExportRequest(){  }

    public UpdateExportRequest(Export export){
        this.export = export;
    }

    public Export getExport() {
        return export;
    }

    public void setExport(Export export) {
        this.export = export;
    }
}
