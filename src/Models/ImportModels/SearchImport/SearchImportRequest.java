package Models.ImportModels.SearchImport;

import Models.Import;

public class SearchImportRequest {
    private Import import_;

    public SearchImportRequest(){  }

    public SearchImportRequest(Import import_){
        this.import_ = import_;
    }

    public Import getImport() {
        return import_;
    }

    public void setImport(Import import_) {
        this.import_ = import_;
    }
}
