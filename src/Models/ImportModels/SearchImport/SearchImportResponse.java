package Models.ImportModels.SearchImport;

import Models.Import;

public class SearchImportResponse {
    private Import import_;

    public SearchImportResponse(){  }

    public SearchImportResponse(Import import_){
        this.import_ = import_;
    }

    public Import getResult() {
        return import_;
    }

    public void setResult(Import import_) {
        this.import_ = import_;
    }
}
