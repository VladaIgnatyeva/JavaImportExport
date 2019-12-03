package Models.Buyers.DeleteBuyer;

import Models.Buyer;

public class DeleteBuyersRequest {
    private Buyer buyer;

    public DeleteBuyersRequest(){  }

    public DeleteBuyersRequest(Buyer buyer){
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
