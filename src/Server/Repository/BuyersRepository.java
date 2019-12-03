package Server.Repository;

import Models.Buyer;

import java.util.ArrayList;

public interface BuyersRepository {
    ArrayList<Buyer> getBuyers();
    boolean addBuyer(Buyer buyer);
    boolean deleteBuyerByIdAndName(Buyer buyer);
    Buyer getBuyerByID(Buyer buyer);
    boolean updateBuyer(Buyer buyer);
    boolean updateBuyerNameByID(int id, String name);
    boolean updateBuyerAddressByID(int id, String address);
    boolean updateBuyerPhoneByID(int id, String phone);
    boolean updateBuyerNoteByID(int id, String note);
}
