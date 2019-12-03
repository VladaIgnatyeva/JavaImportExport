package Models.ExportModels.ShowExportByIDBuyer;

import Models.Export;

public class ShowExportByIDBuyerRequest {
    private Export export;

    public ShowExportByIDBuyerRequest(){  }

    public ShowExportByIDBuyerRequest(Export export){
        this.export = export;
    }

    public Export getExport() {
        return export;
    }

    public void setExport(Export export) {
        this.export = export;
    }
}
