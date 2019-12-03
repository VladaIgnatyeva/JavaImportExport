package Server.Handlers.UsersHandler;

import Models.Users.DeleteUser.DeleteUserRequest;
import Models.Users.DeleteUser.DeleteUserResponse;
import Server.Handlers.Handler;
import Server.Repository.UsersRepository;
import Server.Repository.UsersRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class DeleteUserHandler implements Handler {
    private UsersRepository usersRepository;

    public DeleteUserHandler(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        DeleteUserRequest users = objectMapper.readValue(data, DeleteUserRequest.class);
        DeleteUserResponse deleteProductResponse = new DeleteUserResponse();
        deleteProductResponse.setResult(usersRepository.deleteUserByIdAndName(users.getUser()));
        return deleteProductResponse;
    }
}
