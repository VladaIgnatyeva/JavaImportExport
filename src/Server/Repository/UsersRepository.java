package Server.Repository;

import Models.User;

import java.util.ArrayList;

public interface UsersRepository {
    ArrayList<User> getUsers ();
    boolean addUser(User user);
    boolean deleteUserByIdAndName(User user);
    boolean updateUser(User user);
    User getUserByID(User user);
    boolean getUserByLoginAndPassword(User user);
    boolean updateUserLoginByID(int id, String login);
    boolean updateUserPasswordByID(int id, String password);
    boolean updateUserPasswordByLogin(String login, String password);

}
