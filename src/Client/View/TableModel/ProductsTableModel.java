package Client.View.TableModel;

import Models.Product;
import net.minidev.json.JSONObject;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductsTableModel extends AbstractTableModel {

   private int  columnCount = 5;
   private ArrayList<Product> productsArrayList;


    public ProductsTableModel() {
        productsArrayList = new ArrayList<Product>();
    }

    public void addData(JSONObject product) {

        Product productsTable = new Product(0, null, null, null, null);
        productsTable.setID_product((Integer)product.get("ID_product"));
        productsTable.setName((String)product.get("name_product"));
        productsTable.setPrice(product.get("price").toString());
        productsTable.setUnit(product.get("unit").toString());
        productsTable.setNote((String)product.get("note"));

        productsArrayList.add(productsTable);
    }

    public void clearArrayList(){
        productsArrayList.clear();
    }

    public void setSource(ArrayList<Product> products){
        productsArrayList.clear();
        productsArrayList.addAll(products);
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex) {
            case 0:
                return "Код товара";
            case 1:
                return "Название товара";
            case 2:
                return "Ед. измерения";
            case 3:
                return "Цена за ед.";
            case 4:
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
                return productsArrayList.get(rowIndex).getID_product();
            case 1:
                return productsArrayList.get(rowIndex).getName();
            case 2:
                return productsArrayList.get(rowIndex).getUnit();
            case 3:
                return productsArrayList.get(rowIndex).getPrice();
            case 4:
                return productsArrayList.get(rowIndex).getNote();
            default:
                return "";
        }
    }
}
