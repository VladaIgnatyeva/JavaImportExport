package Models.Buyers.SearchBuyer;

import Models.Buyer;

public class SearchBuyerRequest {
    private Buyer buyer;

    public SearchBuyerRequest(){  }

    public SearchBuyerRequest(Buyer buyer){
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
