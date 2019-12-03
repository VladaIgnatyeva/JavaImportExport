package Server.Handlers.BuyerHandler;

import Models.Buyers.DeleteBuyer.DeleteBuyersRequest;
import Models.Buyers.DeleteBuyer.DeleteBuyersResponse;
import Server.Handlers.Handler;
import Server.Repository.BuyersRepository;
import Server.Repository.BuyersRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DeleteBuyerHandler implements Handler {
    private BuyersRepository buyersRepository;

    public DeleteBuyerHandler(BuyersRepository buyersRepository){
        this.buyersRepository = buyersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        DeleteBuyersRequest buyers = objectMapper.readValue(data, DeleteBuyersRequest.class);
        DeleteBuyersResponse deleteProductResponse = new DeleteBuyersResponse();
        deleteProductResponse.setResult(buyersRepository.deleteBuyerByIdAndName(buyers.getBuyer()));
        return deleteProductResponse;
    }
}
