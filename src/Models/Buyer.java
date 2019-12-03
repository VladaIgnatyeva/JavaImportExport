package Models;

import java.io.Serializable;

public class Buyer implements Serializable {
    private int ID_buyer;
    private String name_firm;
    private String address;
    private String phone;
    private String note;

    public Buyer(){}

    public Buyer(int id, String name_firm, String address, String phone, String note) {
        this.ID_buyer = id;
        this.name_firm = name_firm;
        this.address = address;
        this.phone = phone;
        this.note = note;
    }

    public int getID_buyer() {
        return ID_buyer;
    }

    public void setID_buyer(int ID_buyer) {
        this.ID_buyer = ID_buyer;
    }

    public String getName_firm() {
        return name_firm;
    }

    public void setName_firm(String name_firm) {
        this.name_firm = name_firm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
