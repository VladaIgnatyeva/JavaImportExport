package Server.Repository;

import Models.Buyer;
import Server.DbConnection;
import Server.DbConnectionFactory;
import Models.Supplier;

import java.sql.*;
import java.util.ArrayList;

public class SupplierRepositoryImpl  implements SuppliersRepository{
    private Statement statement;
    private Connection connection;

    public SupplierRepositoryImpl(DbConnectionFactory dbConnectionFactory) {
        DbConnection dbConnection = dbConnectionFactory.create();
        connection = dbConnection.connect();
    }

    @Override
    public ArrayList<Supplier> getSuppliers() {
        final String selectQuery = "SELECT * FROM Suppliers";
        ArrayList<Supplier> result = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String note = resultSet.getString(5);
                Supplier supplier = new Supplier(id, name, address, phone, note);
                result.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        final String selectQuery = "INSERT INTO Suppliers ([Название фирмы] , Адрес , Телефон , Примечание) VALUES (?, ?, ?, ?) ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, supplier.getName_firm());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setString(3, supplier.getPhone());
            preparedStatement.setString(4, supplier.getNote());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSupplierByIdAndName(Supplier supplier) {
        final String selectQuery = "DELETE FROM Suppliers WHERE [Код поставщика] = ? AND [Название фирмы] = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setInt(1, supplier.getID_supplier());
            preparedStatement.setString(2, supplier.getName_firm());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Supplier getSupplierByID(Supplier supplier) {
        final String selectQuery = "SELECT * FROM Suppliers WHERE [Код поставщика] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, supplier.getID_supplier());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id= resultSet.getInt(1);
                String nameFirm= resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                String note = resultSet.getString(5);
                return new Supplier(id, nameFirm, address, phone, note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Supplier(0, null, null, null, null);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        final String selectQuery = "UPDATE Suppliers SET [Название фирмы] = ? , Адрес = ?, Телефон = ?, Примечание = ? WHERE [Код поставщика] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, supplier.getName_firm());
            preparedStatement.setString(2, supplier.getAddress());
            preparedStatement.setString(3, supplier.getPhone());
            preparedStatement.setString(4, supplier.getNote());
            preparedStatement.setInt(5, supplier.getID_supplier());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateSupplierNameByID(int id, String name) {
        final String selectQuery = "UPDATE Suppliers SET [Название фирмы] = ? WHERE [Код поставщика] = ?";
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
    public boolean updateSupplierAddressByID(int id, String address) {
        final String selectQuery = "UPDATE Suppliers SET Адрес = ? WHERE [Код поставщика] = ?";
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
    public boolean updateSupplierPhoneByID(int id, String phone) {
        final String selectQuery = "UPDATE Suppliers SET Телефон = ? WHERE [Код поставщика] = ?";
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
    public boolean updateSupplierNoteByID(int id, String note) {
        final String selectQuery = "UPDATE Suppliers SET Примечание = ? WHERE [Код поставщика] = ?";
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
