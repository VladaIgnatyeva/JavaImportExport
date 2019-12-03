package Server.Repository;

import Models.Product;
import Server.DbConnection;
import Server.DbConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class ProductsRepositoryImpl implements ProductRepository {

    private Statement statement;
    private Connection connection;

    public ProductsRepositoryImpl(DbConnectionFactory dbConnectionFactory) {
        DbConnection dbConnection = dbConnectionFactory.create();
        connection = dbConnection.connect();
    }

    @Override
    public ArrayList<Product> getProducts() {
        final String selectQuery = "SELECT * FROM Products";
        ArrayList<Product> result = new ArrayList<Product>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String unit = resultSet.getString(3);
                String price = resultSet.getString(4);
                String note = resultSet.getString(5);

                Product product = new Product(id, name, unit, price, note);

                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addProduct(Product product) {
        final String selectQuery = "INSERT INTO Products ([Название товара] , [Ед. измерения] , [Цена за ед.] , Примечание) VALUES (?, ?, ?, ?) ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getUnit());
            preparedStatement.setString(3, product.getPrice());
            preparedStatement.setString(4, product.getNote());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProductByIdAndName(Product product) {
        final String selectQuery = "DELETE FROM Products WHERE [Код товара] = ? AND [Название товара] = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setInt(1, product.getID_product());
            preparedStatement.setString(2, product.getName());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product getProductByID(Product product) {
        final String selectQuery = "SELECT * FROM Products WHERE [Код товара] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1,  product.getID_product());
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product_new = new Product();
            while(resultSet.next()) {
                product_new.setID_product(resultSet.getInt(1));
                product_new.setName(resultSet.getString(2));
                product_new.setUnit( resultSet.getString(3));
                product_new.setPrice(resultSet.getString(4));
                product_new.setNote(resultSet.getString(5));
            }
            return product_new;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Product();
    }

    @Override
    public boolean updateProduct(Product product) {
        final String selectQuery = "UPDATE Products SET [Название товара] = ?,  [Ед. измерения] = ? , [Цена за ед.] = ?, Примечание = ? WHERE [Код товара] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getUnit());
            preparedStatement.setString(3, product.getPrice());
            preparedStatement.setString(4, product.getNote());
            preparedStatement.setInt(5, product.getID_product());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProductNameByID(int id, String name) {
        final String selectQuery = "UPDATE Products SET [Название товара] = ? WHERE [Код товара] = ?";
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
    public boolean updateProductUnitByID(int id, String unit) {
        final String selectQuery = "UPDATE Products SET [Ед. измерения] = ? WHERE [Код товара] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, unit);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProductPriceByID(int id, String price) {
        final String selectQuery = "UPDATE Products SET [Цена за ед.] = ? WHERE [Код товара] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, price);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateProductNoteByID(int id, String note) {
        final String selectQuery = "UPDATE Products SET Примечание = ? WHERE [Код товара] = ?";
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
