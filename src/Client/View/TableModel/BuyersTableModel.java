package Client.View.TableModel;

import Models.Buyer;
import net.minidev.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BuyersTableModel extends AbstractTableModel {
    private int  columnCount = 5;
    private ArrayList<Buyer> buyersArrayList;

    public BuyersTableModel() {
        buyersArrayList = new ArrayList<Buyer>();
    }

    public void addData(JSONObject buyer) {

        Buyer buyersTable = new Buyer(0, null, null, null, null);
        buyersTable.setID_buyer((Integer)buyer.get("ID_buyer"));
        buyersTable.setName_firm(buyer.get("name_firm").toString());
        buyersTable.setAddress(buyer.get("address").toString());
        buyersTable.setPhone(buyer.get("phone").toString());
        buyersTable.setNote((String)buyer.get("note"));

        buyersArrayList.add(buyersTable);
    }

    public void clearArrayList(){
        buyersArrayList.clear();
    }

    public void setSource(ArrayList<Buyer> buyers){
        buyersArrayList.clear();
        buyersArrayList.addAll(buyers);
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
        return buyersArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return buyersArrayList.get(rowIndex).getID_buyer();
            case 1:
                return buyersArrayList.get(rowIndex).getName_firm();
            case 2:
                return buyersArrayList.get(rowIndex).getAddress();
            case 3:
                return buyersArrayList.get(rowIndex).getPhone();
            case 4:
                return buyersArrayList.get(rowIndex).getNote();
            default:
                return "";
        }
    }
}
