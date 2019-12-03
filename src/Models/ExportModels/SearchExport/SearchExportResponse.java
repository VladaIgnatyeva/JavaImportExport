package Models.ExportModels.SearchExport;

import Models.Export;

public class SearchExportResponse {
    private Export export;

    public SearchExportResponse(){  }

    public SearchExportResponse(Export export){
        this.export = export;
    }

    public Export getResult() {
        return export;
    }

    public void setResult(Export export) {
        this.export = export;
    }
}
