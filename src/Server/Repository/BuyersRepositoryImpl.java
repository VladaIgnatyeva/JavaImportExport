package Server.Repository;

import Server.DbConnection;
import Server.DbConnectionFactory;
import Models.Buyer;

import java.sql.*;
import java.util.ArrayList;

public class BuyersRepositoryImpl implements BuyersRepository{

    private Statement statement;
    private Connection connection;

    public BuyersRepositoryImpl(DbConnectionFactory dbConnectionFactory) {
        DbConnection dbConnection = dbConnectionFactory.create();
        connection = dbConnection.connect();
    }

    @Override
    public ArrayList<Buyer> getBuyers() {
        final String selectQuery = "SELECT * FROM Buyers";
        ArrayList<Buyer> result = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String note = resultSet.getString(5);

                Buyer buyer = new Buyer(id, name, address, phone, note);
                result.add(buyer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addBuyer(Buyer buyer) {
        final String selectQuery = "INSERT INTO Buyers ([Название фирмы] , Адрес , Телефон , Примечание) VALUES (?, ?, ?, ?) ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, buyer.getName_firm());
            preparedStatement.setString(2, buyer.getAddress());
            preparedStatement.setString(3, buyer.getPhone());
            preparedStatement.setString(4, buyer.getNote());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBuyerByIdAndName(Buyer buyer) {
        final String selectQuery = "DELETE FROM Buyers WHERE [Код покупателя] = ? AND [Название фирмы] = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setInt(1, buyer.getID_buyer());
            preparedStatement.setString(2, buyer.getName_firm());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Buyer getBuyerByID(Buyer buyer) {
        final String selectQuery = "SELECT * FROM Buyers WHERE [Код покупателя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, buyer.getID_buyer());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id= resultSet.getInt(1);
                String nameFirm= resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String note = resultSet.getString(5);
                return new Buyer(id, nameFirm, address, phone, note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Buyer(0, null, null, null, null);
    }

    @Override
    public boolean updateBuyerNameByID(int id, String name) {
        final String selectQuery = "UPDATE Buyers SET [Название фирмы] = ? WHERE [Код покупателя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBuyerAddressByID(int id, String address) {
        final String selectQuery = "UPDATE Buyers SET Адрес = ? WHERE [Код покупателя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, address);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBuyer(Buyer buyer) {
        final String selectQuery = "UPDATE Buyers SET [Название фирмы] = ? , Адрес = ?, Телефон = ?, Примечание = ? WHERE [Код покупателя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, buyer.getName_firm());
            preparedStatement.setString(2, buyer.getAddress());
            preparedStatement.setString(3, buyer.getPhone());
            preparedStatement.setString(4, buyer.getNote());
            preparedStatement.setInt(5, buyer.getID_buyer());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBuyerPhoneByID(int id, String phone) {
        final String selectQuery = "UPDATE Buyers SET Телефон = ? WHERE [Код покупателя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, phone);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBuyerNoteByID(int id, String note) {
        final String selectQuery = "UPDATE Buyers SET Примечание = ? WHERE [Код покупателя] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, note);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
