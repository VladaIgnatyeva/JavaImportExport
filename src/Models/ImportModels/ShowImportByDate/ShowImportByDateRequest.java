package Models.ImportModels.ShowImportByDate;

import Models.Import;

public class ShowImportByDateRequest {
    private Import import_;

    public ShowImportByDateRequest(){  }

    public ShowImportByDateRequest(Import import_){
        this.import_ = import_;
    }

    public Import getImport() {
        return import_;
    }

    public void setImport(Import import_) {
        this.import_ = import_;
    }
}
