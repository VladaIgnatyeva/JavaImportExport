package Server.Handlers.BuyerHandler;

import Models.Buyers.SearchBuyer.SearchBuyerRequest;
import Models.Buyers.SearchBuyer.SearchBuyerResponse;
import Server.Handlers.Handler;
import Server.Repository.BuyersRepository;
import Server.Repository.BuyersRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SearchBuyerHandler implements Handler {
    private BuyersRepository buyersRepository;

    public SearchBuyerHandler(BuyersRepository buyersRepository){
        this.buyersRepository = buyersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        SearchBuyerRequest product = objectMapper.readValue(data, SearchBuyerRequest.class);
        SearchBuyerResponse searchBuyerResponse = new SearchBuyerResponse();
        searchBuyerResponse.setResult(buyersRepository.getBuyerByID(product.getBuyer()));
        return searchBuyerResponse;
    }
}
