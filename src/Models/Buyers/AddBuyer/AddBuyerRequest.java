package Models.Buyers.AddBuyer;

import Models.Buyer;

public class AddBuyerRequest {
    private Buyer buyer;

    public AddBuyerRequest(){  }

    public AddBuyerRequest(Buyer buyer){
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
