package Client.View.TableModel;

import Models.Supplier;
import net.minidev.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SuppliersTableModel extends AbstractTableModel {
    private int  columnCount = 5;
    private ArrayList<Supplier> suppliersArrayList;

    public SuppliersTableModel() {
        suppliersArrayList = new ArrayList<Supplier>();
    }

    public void addData(JSONObject supplier) {

        Supplier suppliersTable = new Supplier(0, null, null, null, null);
        suppliersTable.setID_supplier((Integer)supplier.get("ID_supplier"));
        suppliersTable.setName_firm(supplier.get("name_firm").toString());
        suppliersTable.setAddress(supplier.get("address").toString());
        suppliersTable.setPhone(supplier.get("phone").toString());
        suppliersTable.setNote((String)supplier.get("note"));

        suppliersArrayList.add(suppliersTable);
    }

    public void clearArrayList(){
        suppliersArrayList.clear();
    }

    public void setSource(ArrayList<Supplier> suppliers){
        suppliersArrayList.clear();
        suppliersArrayList.addAll(suppliers);
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex) {
            case 0:
                return "Код покупателя";
            case 1:
                return "Название фирмы";
            case 2:
                return "Адрес";
            case 3:
                return "Телефон";
            case 4:
                return "Примечание";
            default:
                return "Другое";
        }
    }

    @Override
    public int getRowCount() {
        return suppliersArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return suppliersArrayList.get(rowIndex).getID_supplier();
            case 1:
                return suppliersArrayList.get(rowIndex).getName_firm();
            case 2:
                return suppliersArrayList.get(rowIndex).getAddress();
            case 3:
                return suppliersArrayList.get(rowIndex).getPhone();
            case 4:
                return suppliersArrayList.get(rowIndex).getNote();
            default:
                return "";
        }
    }
}
