package Server.Handlers.UsersHandler;

import Models.Users.SearchUser.SearchEntranceUserRequest;
import Models.Users.SearchUser.SearchEntranceUserResponse;
import Models.Users.SearchUser.SearchUserRequest;
import Models.Users.SearchUser.SearchUserResponse;
import Server.Handlers.Handler;
import Server.Repository.UsersRepository;
import Server.Repository.UsersRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SearchEntranceUserHandler implements Handler {
    private UsersRepository usersRepository;

    public SearchEntranceUserHandler(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        SearchEntranceUserRequest user = objectMapper.readValue(data, SearchEntranceUserRequest.class);
        SearchEntranceUserResponse searchUserResponse = new SearchEntranceUserResponse();
        searchUserResponse.setResult(usersRepository.getUserByLoginAndPassword(user.getUser()));
        return searchUserResponse;
    }
}
