package Models;

import java.io.Serializable;

public class TopProduct implements Serializable {
    private int ID_product;
    private String name_product;
    private String unit;
    private String price;
    private String note;
    private int amount;

    public TopProduct(){}

    public TopProduct(int id, String name_product, String unit, String price, String note, int amount) {
        this.ID_product = id;
        this.name_product = name_product;
        this.unit = unit;
        this.price = price;
        this.note = note;
        this.amount = amount;
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


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
