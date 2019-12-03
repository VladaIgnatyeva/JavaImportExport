package Models.ImportModels;

import Models.Import;
import Models.ImportForTable;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowImportResponse implements Serializable {
    private ArrayList<ImportForTable> import_;

    public ShowImportResponse(){}

    public ShowImportResponse(ArrayList<ImportForTable> import_){
        this.import_ = import_;
    }

    public ArrayList<ImportForTable> getImport() {
        return import_;
    }

    public void setImport(ArrayList<ImportForTable> import_) {
        this.import_ = import_;
    }
}
