package Server.Repository;

import Models.Export;
import Models.ExportForTable;
import Models.TopProduct;

import java.util.ArrayList;

public interface ExportRepository {
    ArrayList<ExportForTable> getExport();
    ArrayList<TopProduct> getPopularExport();
    boolean addExport(Export export);
    boolean deleteExportById(Export export);
    Export getExportByID(Export export);
    ArrayList<ExportForTable> getExportByDate(Export export);
    ArrayList<ExportForTable> getExportByIDBuyer(Export export);
    ArrayList<ExportForTable> getExportByIDUser(Export export);
    boolean updateExport(Export export);
    boolean updateExportDateByID(int id, String date);
    boolean updateExportIdProductByID(int id, int idProduct);
    boolean updateExportIdBuyerByID(int id, int idBuyer);
    boolean updateExportWaybillByID(int id, String waybill);
    boolean updateExportUnitByID(int id, String unit);
    boolean updateExportPriceByID(int id, String price);
    boolean updateExportNoteByID(int id, String note);
    ArrayList<Export> getExportByDate(String date);

}
