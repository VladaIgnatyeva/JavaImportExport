package Server.Handlers.BuyerHandler;

import Models.Buyers.UpdateBuyer.UpdateBuyerRequest;
import Models.Buyers.UpdateBuyer.UpdateBuyerResponse;
import Server.Handlers.Handler;
import Server.Repository.BuyersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UpdateBuyerHandler implements Handler {
    private BuyersRepository buyersRepository;

    public UpdateBuyerHandler(BuyersRepository buyersRepository){
        this.buyersRepository = buyersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        UpdateBuyerRequest buyer = objectMapper.readValue(data, UpdateBuyerRequest.class);
        UpdateBuyerResponse updateBuyerResponse = new UpdateBuyerResponse();
        updateBuyerResponse.setResult(buyersRepository.updateBuyer(buyer.getBuyer()));
        return updateBuyerResponse;
    }
}
