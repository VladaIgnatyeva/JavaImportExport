package Server.Handlers.UsersHandler;

import Models.Users.SearchUser.SearchUserRequest;
import Models.Users.SearchUser.SearchUserResponse;
import Server.Handlers.Handler;
import Server.Repository.UsersRepository;
import Server.Repository.UsersRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SearchUserHandler implements Handler {
    private UsersRepository usersRepository;

    public SearchUserHandler(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        SearchUserRequest product = objectMapper.readValue(data, SearchUserRequest.class);
        SearchUserResponse searchUserResponse = new SearchUserResponse();
        searchUserResponse.setResult(usersRepository.getUserByID(product.getUser()));
        return searchUserResponse;
    }
}
