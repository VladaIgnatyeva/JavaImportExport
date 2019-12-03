package Models.ExportModels.DeleteExport;

import Models.Export;

public class DeleteExportRequest {
    private Export export;

    public DeleteExportRequest(){  }

    public DeleteExportRequest(Export export){
        this.export = export;
    }

    public Export getExport() {
        return export;
    }

    public void setExport(Export export) {
        this.export = export;
    }
}
