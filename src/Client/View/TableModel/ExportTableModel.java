package Client.View.TableModel;

import Models.ExportForTable;

import javax.swing.table.AbstractTableModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ExportTableModel extends AbstractTableModel {
    private int  columnCount = 11;
    private ArrayList<ExportForTable> exportArrayList;


    public ExportTableModel() {
        exportArrayList = new ArrayList<ExportForTable>();
    }


    public void setSource(ArrayList<ExportForTable> export_){
        exportArrayList.clear();
        exportArrayList.addAll(export_);
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex) {
            case 0:
                return "Код операции экспорта";
            case 1:
                return "Дата";
            case 2:
                return "ID-пользователя";
            case 3:
                return "Код товара";
            case 4:
                return "Название товара";
            case 5:
                return "Код покупателя";
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
        return exportArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return exportArrayList.get(rowIndex).getID_export();
            case 1:
                return exportArrayList.get(rowIndex).getDate();
            case 2:
                return exportArrayList.get(rowIndex).getID_user();
            case 3:
                return exportArrayList.get(rowIndex).getID_product();
            case 4:
                return exportArrayList.get(rowIndex).getName_product();
            case 5:
                return exportArrayList.get(rowIndex).getID_buyer();
            case 6:
                return exportArrayList.get(rowIndex).getName_buyer();
            case 7:
                return exportArrayList.get(rowIndex).getWaybill();
            case 8:
                return exportArrayList.get(rowIndex).getUnit();
            case 9:
                return exportArrayList.get(rowIndex).getPrice();
            case 10:
                return exportArrayList.get(rowIndex).getNote();
            default:
                return "";
        }
    }
}
