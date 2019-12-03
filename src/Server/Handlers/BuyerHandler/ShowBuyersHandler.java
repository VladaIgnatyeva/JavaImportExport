package Server.Handlers.BuyerHandler;

import Models.Buyer;
import Models.Buyers.ShowBuyersResponse;
import Server.Handlers.Handler;
import Server.Repository.BuyersRepository;
import Server.Repository.BuyersRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;

public class ShowBuyersHandler implements Handler {
    private BuyersRepository buyersRepository;

    public ShowBuyersHandler(BuyersRepository buyersRepository){
        this.buyersRepository = buyersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ArrayList<Buyer> buyers = buyersRepository.getBuyers();
        return new ShowBuyersResponse(buyers);
    }
}
