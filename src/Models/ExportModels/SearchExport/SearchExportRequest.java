package Models.ExportModels.SearchExport;

import Models.Export;

public class SearchExportRequest {
    private Export export;

    public SearchExportRequest(){  }

    public SearchExportRequest(Export export){
        this.export = export;
    }

    public Export getExport() {
        return export;
    }

    public void setExport(Export export) {
        this.export = export;
    }
}
