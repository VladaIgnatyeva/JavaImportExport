package Server.Repository;

import Models.Buyer;
import Models.ImportForTable;
import Models.TopProduct;
import Server.DbConnection;
import Server.DbConnectionFactory;
import Models.Import;

import java.sql.*;
import java.util.ArrayList;

public class ImportRepositoryImpl implements ImportRepository{
    private Statement statement;
    private Connection connection;

    public ImportRepositoryImpl(DbConnectionFactory dbConnectionFactory) {
        DbConnection dbConnection = dbConnectionFactory.create();
        connection = dbConnection.connect();

    }

    @Override
    public ArrayList<ImportForTable> getImport() {
        final String selectQuery = "SELECT Import.*, Products.[Название товара], Suppliers.[Название фирмы]  FROM Import INNER JOIN Products ON Import.[Код товара] = Products.[Код товара] INNER JOIN Suppliers ON Import.[Код поставщика] = Suppliers.[Код поставщика]";
        //final String selectQuery = "SELECT * FROM Import";

        ArrayList<ImportForTable> result = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String type_operation = resultSet.getString(2);
                String date = resultSet.getString(3);
                int id_user = resultSet.getInt(4);
                int id_product = resultSet.getInt(5);
                int id_buyer = resultSet.getInt(6);
                String waybill = resultSet.getString(7);
                String unit = resultSet.getString(8);
                String price = resultSet.getString(9);
                String note = resultSet.getString(10);
                String name_product = resultSet.getString(11);
                String name_buyer = resultSet.getString(12);

                ImportForTable import_ = new ImportForTable(id, type_operation, date, id_user, id_product,name_product, id_buyer,name_buyer, waybill, unit, price, note);
                //Import import_ = new Import(1, type_operation, date, id_user, id_product, id_buyer, waybill, unit, price, note );
                result.add(import_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean addImport(Import import_) {
        final String selectQuery = "INSERT INTO Import (Дата, [Код товара], [Код поставщика], [Номер накладной], [Количество товара], Цена, Примечание, [ID-пользователя]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, import_.getDate());
            preparedStatement.setInt(2, import_.getID_product());
            preparedStatement.setInt(3, import_.getID_buyer());
            preparedStatement.setString(4, import_.getWaybill());
            preparedStatement.setString(5, import_.getUnit());
            preparedStatement.setString(6, import_.getPrice());
            preparedStatement.setString(7, import_.getNote());
            preparedStatement.setInt(8, import_.getID_user());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<TopProduct> getPopularImport() {
        final String selectQuery = "SELECT count(*) as Amount, Products.[Название товара], Import.[Код товара], Products.[Ед. измерения], Products.[Цена за ед.], Products.[Примечание] FROM Import INNER JOIN Products ON Import.[Код товара] = Products.[Код товара] GROUP BY Import.[Код товара], Products.[Название товара], Products.[Ед. измерения],Products.[Цена за ед.], Products.[Примечание] ORDER BY Amount DESC;";
        ArrayList<TopProduct> result = new ArrayList<TopProduct>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){

                int amount = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int id = resultSet.getInt(3);
                String unit = resultSet.getString(4);
                String price = resultSet.getString(5);
                String note = resultSet.getString(6);

                TopProduct product = new TopProduct(id, name, unit, price, note, amount);

                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteImportById(Import import_) {
        final String selectQuery = "DELETE FROM Import WHERE [Код операции импорта] = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setInt(1, import_.getID_import());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Import getImportByID(Import import_) {
        final String selectQuery = "SELECT * FROM Import WHERE [Код операции импорта] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, import_.getID_import());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id_ = resultSet.getInt(1);
                String typeOperation = resultSet.getString(2);
                String date = resultSet.getString(3);
                int idUser = resultSet.getInt(4);
                int idProduct = resultSet.getInt(5);
                int idBuyer = resultSet.getInt(6);
                String waybill = resultSet.getString(7);
                String unit = resultSet.getString(8);
                String price = resultSet.getString(9);
                String note = resultSet.getString(10);
                Import import_new = new Import(id_, typeOperation, date, idUser, idProduct, idBuyer, waybill, unit, price, note);
                return  import_new;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Import(0, null, null, 0, 0, 0, null, null, null, null);
    }

    @Override
    public ArrayList<ImportForTable> getImportByDate(Import import_) {
        final String selectQuery = "SELECT Import.*, Products.[Название товара], Suppliers.[Название фирмы]  FROM Import INNER JOIN Products ON Import.[Код товара] = Products.[Код товара] INNER JOIN Suppliers ON Import.[Код поставщика] = Suppliers.[Код поставщика] WHERE Дата = ?";

        ArrayList<ImportForTable> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, import_.getDate());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String type_operation = resultSet.getString(2);
                String date = resultSet.getString(3);
                int id_user = resultSet.getInt(4);
                int id_product = resultSet.getInt(5);
                int id_buyer = resultSet.getInt(6);
                String waybill = resultSet.getString(7);
                String unit = resultSet.getString(8);
                String price = resultSet.getString(9);
                String note = resultSet.getString(10);
                String name_product = resultSet.getString(11);
                String name_buyer = resultSet.getString(12);

                ImportForTable importCollection = new ImportForTable(id, type_operation, date, id_user, id_product,name_product, id_buyer,name_buyer, waybill, unit, price, note);
                //Import import_ = new Import(1, type_operation, date, id_user, id_product, id_buyer, waybill, unit, price, note );
                result.add(importCollection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<ImportForTable> getImportByIDSupplier(Import import_) {
        final String selectQuery = "SELECT Import.*, Products.[Название товара], Suppliers.[Название фирмы]  FROM Import INNER JOIN Products ON Import.[Код товара] = Products.[Код товара] INNER JOIN Suppliers ON Import.[Код поставщика] = Suppliers.[Код поставщика] WHERE Import.[Код поставщика] = ?";

        ArrayList<ImportForTable> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, import_.getID_buyer());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String type_operation = resultSet.getString(2);
                String date = resultSet.getString(3);
                int id_user = resultSet.getInt(4);
                int id_product = resultSet.getInt(5);
                int id_buyer = resultSet.getInt(6);
                String waybill = resultSet.getString(7);
                String unit = resultSet.getString(8);
                String price = resultSet.getString(9);
                String note = resultSet.getString(10);
                String name_product = resultSet.getString(11);
                String name_buyer = resultSet.getString(12);

                ImportForTable importCollection = new ImportForTable(id, type_operation, date, id_user, id_product,name_product, id_buyer,name_buyer, waybill, unit, price, note);
                result.add(importCollection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<ImportForTable> getImportByIDUser(Import import_) {
        final String selectQuery = "SELECT Import.*, Products.[Название товара], Suppliers.[Название фирмы]  FROM Import INNER JOIN Products ON Import.[Код товара] = Products.[Код товара] INNER JOIN Suppliers ON Import.[Код поставщика] = Suppliers.[Код поставщика] WHERE Import.[ID-пользователя] = ?";

        ArrayList<ImportForTable> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, import_.getID_user());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String type_operation = resultSet.getString(2);
                String date = resultSet.getString(3);
                int id_user = resultSet.getInt(4);
                int id_product = resultSet.getInt(5);
                int id_buyer = resultSet.getInt(6);
                String waybill = resultSet.getString(7);
                String unit = resultSet.getString(8);
                String price = resultSet.getString(9);
                String note = resultSet.getString(10);
                String name_product = resultSet.getString(11);
                String name_buyer = resultSet.getString(12);

                ImportForTable importCollection = new ImportForTable(id, type_operation, date, id_user, id_product,name_product, id_buyer,name_buyer, waybill, unit, price, note);
                result.add(importCollection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateImport(Import import_) {
        final String selectQuery = "UPDATE Import SET Дата = ?, [Код товара] = ?, [Код поставщика] = ?, [Номер накладной] = ?, [Количество товара] = ?, Цена = ?,Примечание = ?, [ID-пользователя] = ?  WHERE [Код операции импорта] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, import_.getDate());
            preparedStatement.setInt(2, import_.getID_product());
            preparedStatement.setInt(3, import_.getID_buyer());
            preparedStatement.setString(4, import_.getWaybill());
            preparedStatement.setString(5, import_.getUnit());
            preparedStatement.setString(6, import_.getPrice());
            preparedStatement.setString(7, import_.getNote());
            preparedStatement.setInt(8, import_.getID_user());
            preparedStatement.setInt(9, import_.getID_import());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateImportDateByID(int id, String date) {
        final String selectQuery = "UPDATE Import SET Дата = ? WHERE [Код операции импорта] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, date);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateImportIdProductByID(int id, int idProduct) {
        final String selectQuery = "UPDATE Import SET [Код товара] = ? WHERE [Код операции импорта] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateImportIdBuyerByID(int id, int idBuyer) {
        final String selectQuery = "UPDATE Import SET [Код поставщика] = ? WHERE [Код операции импорта] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setInt(1, idBuyer);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateImportWaybillByID(int id, String waybill) {
        final String selectQuery = "UPDATE Import SET [Номер накладной] = ? WHERE [Код операции импорта] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, waybill);
            preparedStatement.setInt(2, id);

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateImportUnitByID(int id, String unit) {
        final String selectQuery = "UPDATE Import SET [Количество товара]= ? WHERE [Код операции импорта] = ?";
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
    public boolean updateImportPriceByID(int id, String price) {
        final String selectQuery = "UPDATE Import SET Цена = ? WHERE [Код операции импорта] = ?";
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
    public boolean updateImportNoteByID(int id, String note) {
        final String selectQuery = "UPDATE Import SET Примечание = ? WHERE [Код операции импорта] = ?";
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
