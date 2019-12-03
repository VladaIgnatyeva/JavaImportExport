package Server.Repository;

import Server.DbConnection;
import Server.DbConnectionFactory;
import Models.User;

import java.sql.*;
import java.util.ArrayList;

public class UsersRepositoryImpl implements UsersRepository {
    private Statement statement;
    private Connection connection;

    public UsersRepositoryImpl(DbConnectionFactory dbConnectionFactory){
        DbConnection dbConnection = dbConnectionFactory.create();
        connection = dbConnection.connect();
    }

    @Override
    public ArrayList<User> getUsers() {
        final String selectQuery = "SELECT * FROM Users";
        ArrayList<User> result = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);

                User user = new User(id, login, password, name);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addUser(User user) {
        final String selectQuery = "INSERT INTO Users (Логин , Пароль, ФИО) VALUES (?, ?, ?) ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUserByIdAndName(User user) {
        final String selectQuery = "DELETE FROM Users WHERE [ID-пользователя] = ? AND ФИО = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setInt(1, user.getIdUser());
            preparedStatement.setString(2, user.getName());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        final String selectQuery = "UPDATE Users SET Логин = ? , Пароль = ?, ФИО = ? WHERE [ID-пользователя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setInt(4, user.getIdUser());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByID(User user) {
        final String selectQuery = "SELECT * FROM Users WHERE [ID-пользователя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, user.getIdUser());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                return new User(id, login, password, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(0, null, null, null);
    }

    @Override
    public boolean getUserByLoginAndPassword(User user) {
        final String selectQuery = "SELECT * FROM Users WHERE Логин = ? AND Пароль = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserLoginByID(int id, String login) {
        final String selectQuery = "UPDATE Users SET Логин = ? WHERE [ID-пользователя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, id);
            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserPasswordByID(int id, String password) {
        final String selectQuery = "UPDATE Users SET Пароль = ? WHERE [ID-пользователя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUserPasswordByLogin(String login, String password) {
        final String selectQuery = "UPDATE Users SET Пароль = ? WHERE Логин = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, password);
            preparedStatement.setString(2, login);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
