package Models.ExportModels.AddExport;

import Models.Export;

public class AddExportRequest {
    private Export export;

    public AddExportRequest(){  }

    public AddExportRequest(Export export){
        this.export = export;
    }

    public Export getExport() {
        return export;
    }

    public void setExport(Export export) {
        this.export = export;
    }
}
