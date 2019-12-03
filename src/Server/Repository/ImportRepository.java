package Server.Repository;

import Models.Import;
import Models.ImportForTable;
import Models.TopProduct;

import java.util.ArrayList;

public interface ImportRepository {
    ArrayList<ImportForTable> getImport();
    boolean addImport(Import import_);
    ArrayList<TopProduct> getPopularImport();
    boolean deleteImportById(Import import_);
    Import getImportByID(Import import_);
    ArrayList<ImportForTable> getImportByDate(Import import_);
    ArrayList<ImportForTable> getImportByIDSupplier(Import import_);
    ArrayList<ImportForTable> getImportByIDUser(Import import_);
    boolean updateImport(Import import_);
    boolean updateImportDateByID(int id, String date);
    boolean updateImportIdProductByID(int id, int idProduct);
    boolean updateImportIdBuyerByID(int id, int idBuyer);
    boolean updateImportWaybillByID(int id, String waybill);
    boolean updateImportUnitByID(int id, String unit);
    boolean updateImportPriceByID(int id, String price);
    boolean updateImportNoteByID(int id, String note);
}
