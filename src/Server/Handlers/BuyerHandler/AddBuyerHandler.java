package Server.Handlers.BuyerHandler;

import Models.Buyers.AddBuyer.AddBuyerRequest;
import Models.Buyers.AddBuyer.AddBuyerResponse;
import Server.Handlers.Handler;
import Server.Repository.BuyersRepository;
import Server.Repository.BuyersRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AddBuyerHandler implements Handler{
    private BuyersRepository buyersRepository;

    public AddBuyerHandler(BuyersRepository buyersRepository){
        this.buyersRepository = buyersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        AddBuyerRequest buyer = objectMapper.readValue(data, AddBuyerRequest.class);
        AddBuyerResponse addBuyerResponse = new AddBuyerResponse();
        addBuyerResponse.setResult(buyersRepository.addBuyer(buyer.getBuyer()));
        return addBuyerResponse;
    }
}
