package Models.Buyers.UpdateBuyer;

import Models.Buyer;

public class UpdateBuyerRequest {
    private Buyer buyer;

    public UpdateBuyerRequest(){  }

    public UpdateBuyerRequest(Buyer buyer){
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
