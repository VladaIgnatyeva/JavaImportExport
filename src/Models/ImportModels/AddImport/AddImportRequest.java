package Models.ImportModels.AddImport;

import Models.Import;

public class AddImportRequest {
    private Import import_;

    public AddImportRequest(){  }

    public AddImportRequest(Import import_){
        this.import_ = import_;
    }

    public Import getImport() {
        return import_;
    }

    public void setImport(Import import_) {
        this.import_ = import_;
    }
}
