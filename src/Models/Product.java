package Models;

import java.io.Serializable;

public class Product implements Serializable {
    private int ID_product;
    private String name_product;
    private String unit;
    private String price;
    private String note;

    public Product(){}

    public Product(int id, String name_product, String unit, String price, String note) {
        this.ID_product = id;
        this.name_product = name_product;
        this.unit = unit;
        this.price = price;
        this.note = note;
    }

    public int getID_product() {
        return ID_product;
    }

    public void setID_product(int ID_product) {
        this.ID_product = ID_product;
    }

    public String getName() {
        return name_product;
    }

    public void setName(String name_product) {
        this.name_product = name_product;
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


}
