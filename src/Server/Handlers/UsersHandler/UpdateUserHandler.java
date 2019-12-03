package Server.Handlers.UsersHandler;

import Models.Users.UpdateUser.UpdateUserRequest;
import Models.Users.UpdateUser.UpdateUserResponse;
import Server.Handlers.Handler;
import Server.Repository.UsersRepository;
import Server.Repository.UsersRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class UpdateUserHandler implements Handler {
    private UsersRepository userRepository;

    public UpdateUserHandler(UsersRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        UpdateUserRequest user = objectMapper.readValue(data, UpdateUserRequest.class);
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setResult(userRepository.updateUser(user.getUser()));
        return updateUserResponse;
    }
}
