package Client.View.TableModel;

import Models.Product;
import Models.TopProduct;
import net.minidev.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TopProductsTableModel extends AbstractTableModel {

   private int  columnCount = 6;
   private ArrayList<TopProduct> productsArrayList;


    public TopProductsTableModel() {
        productsArrayList = new ArrayList<TopProduct>();
    }

//    public void addData(JSONObject product) {
//
//        TopProduct productsTable = new TopProduct(0, null, null, null, null, 0);
//        productsTable.setID_product((Integer)product.get("ID_product"));
//        productsTable.setName((String)product.get("name_product"));
//        productsTable.setPrice(product.get("price").toString());
//        productsTable.setUnit(product.get("unit").toString());
//        productsTable.setNote((String)product.get("note"));
//        productsTable.setAmount((Integer)product.get("amount"));
//
//        productsArrayList.add(productsTable);
//    }
//
//    public void clearArrayList(){
//        productsArrayList.clear();
//    }

    public void setSource(ArrayList<TopProduct> products){
        productsArrayList.clear();
        productsArrayList.addAll(products);
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex) {
            case 0:
                return "Количество записей";
            case 1:
                return "Код товара";
            case 2:
                return "Название товара";
            case 3:
                return "Ед. измерения";
            case 4:
                return "Цена за ед.";
            case 5:
                return "Примечание";
            default:
                return "Другое";
        }
    }


    @Override
    public int getRowCount() {
        return productsArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return productsArrayList.get(rowIndex).getAmount();
            case 1:
                return productsArrayList.get(rowIndex).getID_product();
            case 2:
                return productsArrayList.get(rowIndex).getName();
            case 3:
                return productsArrayList.get(rowIndex).getUnit();
            case 4:
                return productsArrayList.get(rowIndex).getPrice();
            case 5:
                return productsArrayList.get(rowIndex).getNote();
            default:
                return "";
        }
    }
}
