package Models.Buyers;

import Models.Buyer;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowBuyersResponse implements Serializable {
    private ArrayList<Buyer> buyer;

    public ShowBuyersResponse(){}

    public ShowBuyersResponse(ArrayList<Buyer> buyer){
        this.buyer = buyer;
    }

    public ArrayList<Buyer> getBuyers() {
        return buyer;
    }

    public void setBuyers(ArrayList<Buyer> buyer) {
        this.buyer = buyer;
    }
}
