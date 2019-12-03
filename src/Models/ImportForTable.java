package Models;

import java.io.Serializable;

public class ImportForTable implements Serializable {
    private int ID_import;
    private String type_operation;
    private String date;
    private int id_user;
    private int id_product;
    private String name_product;
    private int id_buyer;
    private String name_buyer;
    private String waybill;
    private String unit;
    private String price;
    private String note;

    public ImportForTable(){}

    public ImportForTable(int id, String type_operation, String date, int id_user, int id_product, String name_product, int id_buyer, String name_buyer, String waybill, String unit, String price,  String note) {
        this.ID_import = id;
        this.type_operation = type_operation;
        this.date = date;
        this.id_user = id_user;
        this.id_product = id_product;
        this.id_buyer = id_buyer;
        this.waybill = waybill;
        this.unit = unit;
        this.price = price;
        this.note = note;
        this.name_buyer = name_buyer;
        this.name_product = name_product;
    }

    public int getID_import() {
        return ID_import;
    }

    public void setID_import(int ID_import) {
        this.ID_import = ID_import;
    }

    public String getType_operation() {
        return type_operation;
    }

    public void setType_operation(String type_operation) {
        this.type_operation = type_operation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getID_user() {
        return id_user;
    }

    public void setID_user(int id_user) {
        this.id_user = id_user;
    }

    public int getID_product() {
        return id_product;
    }

    public void setID_product(int id_product) {
        this.id_product = id_product;
    }

    public int getID_buyer() {
        return id_buyer;
    }

    public void setID_buyer(int id_buyer) {
        this.id_buyer = id_buyer;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getName_buyer() {
        return name_buyer;
    }

    public void setName_buyer(String name_buyer) {
        this.name_buyer = name_buyer;
    }
}
