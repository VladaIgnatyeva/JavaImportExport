package Server.Handlers.UsersHandler;

import Models.Users.AddUser.AddUserRequest;
import Models.Users.AddUser.AddUserResponse;
import Server.Handlers.Handler;
import Server.Repository.UsersRepository;
import Server.Repository.UsersRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AddUserHandler implements Handler{
    private UsersRepository usersRepository;

    public AddUserHandler(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        AddUserRequest user = objectMapper.readValue(data, AddUserRequest.class);
        AddUserResponse addUserResponse = new AddUserResponse();
        addUserResponse.setResult(usersRepository.addUser(user.getUser()));
        return addUserResponse;
    }
}
