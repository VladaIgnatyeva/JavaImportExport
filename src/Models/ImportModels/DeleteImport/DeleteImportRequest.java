package Models.ImportModels.DeleteImport;

import Models.Import;

public class DeleteImportRequest {
    private Import import_;

    public DeleteImportRequest(){  }

    public DeleteImportRequest(Import import_){
        this.import_ = import_;
    }

    public Import getImport() {
        return import_;
    }

    public void setImport(Import import_) {
        this.import_ = import_;
    }
}
