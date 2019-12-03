package Models.Buyers.SearchBuyer;

import Models.Buyer;

public class SearchBuyerResponse {
    private Buyer buyer;

    public SearchBuyerResponse(){  }

    public SearchBuyerResponse(Buyer buyer){
        this.buyer = buyer;
    }

    public Buyer getResult() {
        return buyer;
    }

    public void setResult(Buyer buyer) {
        this.buyer = buyer;
    }
}
