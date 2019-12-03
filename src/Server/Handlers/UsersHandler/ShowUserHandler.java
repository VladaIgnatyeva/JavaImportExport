package Server.Handlers.UsersHandler;

import Models.User;
import Models.Users.ShowUsersResponse;
import Server.Handlers.Handler;
import Server.Repository.UsersRepository;
import Server.Repository.UsersRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;

public class ShowUserHandler implements Handler {
    private UsersRepository usersRepository;

    public ShowUserHandler(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public Object handle(String data) throws IOException {
        ArrayList<User> users = usersRepository.getUsers();
        return new ShowUsersResponse(users);
    }
}
