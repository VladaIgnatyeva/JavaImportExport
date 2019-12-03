package Client.View.TableModel;

import Models.Buyer;
import Models.Import;
import Models.ImportForTable;
import net.minidev.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ImportTableModel extends AbstractTableModel {
    private int  columnCount = 11;
    private ArrayList<ImportForTable> importArrayList;


    public ImportTableModel() {
        importArrayList = new ArrayList<ImportForTable>();
    }


    public void setSource(ArrayList<ImportForTable> import_){
        importArrayList.clear();
        importArrayList.addAll(import_);
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex) {
            case 0:
                return "Код операции импорта";
            case 1:
                return "Дата";
            case 2:
                return "ID-пользователя";
            case 3:
                return "Код товара";
            case 4:
                return "Название товара";
            case 5:
                return "Код поставщика";
            case 6:
                return "Название фирмы";
            case 7:
                return "Номер накладной";
            case 8:
                return "Количество товара";
            case 9:
                return "Цена";
            case 10:
                return "Примечание";
            default:
                return "Другое";
        }
    }

    @Override
    public int getRowCount() {
        return importArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return importArrayList.get(rowIndex).getID_import();
            case 1:
                return importArrayList.get(rowIndex).getDate();
            case 2:
                return importArrayList.get(rowIndex).getID_user();
            case 3:
                return importArrayList.get(rowIndex).getID_product();
            case 4:
                return importArrayList.get(rowIndex).getName_product();
            case 5:
                return importArrayList.get(rowIndex).getID_buyer();
            case 6:
                return importArrayList.get(rowIndex).getName_buyer();
            case 7:
                return importArrayList.get(rowIndex).getWaybill();
            case 8:
                return importArrayList.get(rowIndex).getUnit();
            case 9:
                return importArrayList.get(rowIndex).getPrice();
            case 10:
                return importArrayList.get(rowIndex).getNote();
            default:
                return "";
        }
    }
}
