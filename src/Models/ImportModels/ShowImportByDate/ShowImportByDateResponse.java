package Models.ImportModels.ShowImportByDate;

import Models.ImportForTable;

import java.util.ArrayList;

public class ShowImportByDateResponse {
    private ArrayList<ImportForTable> import_;

    public ShowImportByDateResponse(){}

    public ShowImportByDateResponse(ArrayList<ImportForTable> import_){
        this.import_ = import_;
    }

    public ArrayList<ImportForTable> getImport() {
        return import_;
    }

    public void setImport(ArrayList<ImportForTable> import_) {
        this.import_ = import_;
    }
}
