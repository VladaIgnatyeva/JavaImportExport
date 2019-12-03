package Models.ImportModels.UpdateImport;

import Models.Import;

public class UpdateImportRequest {
    private Import import_;

    public UpdateImportRequest(){  }

    public UpdateImportRequest(Import import_){
        this.import_ = import_;
    }

    public Import getImport() {
        return import_;
    }

    public void setImport(Import import_) {
        this.import_ = import_;
    }
}
