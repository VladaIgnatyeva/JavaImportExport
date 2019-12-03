package Server.Repository;

import Models.ExportForTable;
import Models.ExportForTable;
import Models.TopProduct;
import Server.DbConnection;
import Server.DbConnectionFactory;
import Models.Export;

import java.sql.*;
import java.util.ArrayList;

public class ExportRepositoryImpl implements ExportRepository{
    private Statement statement;
    private Connection connection;

    public ExportRepositoryImpl(DbConnectionFactory dbConnectionFactory) {
        DbConnection dbConnection = dbConnectionFactory.create();
        connection = dbConnection.connect();
    }


    @Override
    public ArrayList<ExportForTable> getExport() {
        final String selectQuery = "SELECT Export.*,Products.[Название товара], Buyers.[Название фирмы]  FROM Export INNER JOIN Products ON Export.[Код товара] = Products.[Код товара] INNER JOIN Buyers ON Export.[Код Покупателя] = Buyers.[Код покупателя]";
        ArrayList<ExportForTable> result = new ArrayList<>();
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

                ExportForTable export = new ExportForTable(id, type_operation, date, id_user, id_product,name_product, id_buyer,name_buyer, waybill, unit, price, note);

                result.add(export);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<TopProduct> getPopularExport() {
        final String selectQuery = "SELECT count(*) as Amount, Products.[Название товара], Export.[Код товара], Products.[Ед. измерения], Products.[Цена за ед.], Products.[Примечание] FROM Export INNER JOIN Products ON Export.[Код товара] = Products.[Код товара] GROUP BY Export.[Код товара], Products.[Название товара], Products.[Ед. измерения],Products.[Цена за ед.], Products.[Примечание] ORDER BY Amount DESC;";
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
    public boolean addExport(Export export) {
        final String selectQuery = "INSERT INTO Export ([Дата получения], [Код товара], [Код Покупателя], [Номер накладной], [Количество товара], Цена, Примечание, [ID-пользователя]) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, export.getDate());
            preparedStatement.setInt(2, export.getID_product());
            preparedStatement.setInt(3, export.getID_buyer());
            preparedStatement.setString(4, export.getWaybill());
            preparedStatement.setString(5, export.getUnit());
            preparedStatement.setString(6, export.getPrice());
            preparedStatement.setString(7, export.getNote());
            preparedStatement.setInt(8, export.getId_user());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteExportById(Export export) {
        final String selectQuery = "DELETE FROM Export WHERE [Код операции экспорта] = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setInt(1, export.getID_export());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateExport(Export export) {
        final String selectQuery = "UPDATE Export SET [Дата получения] = ?, [Код товара] = ?, [Код Покупателя] = ?, [Номер накладной] = ?, [Количество товара] = ?, Цена = ?,Примечание = ?, [ID-пользователя] = ?  WHERE [Код операции экспорта] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            preparedStatement.setString(1, export.getDate());
            preparedStatement.setInt(2, export.getID_product());
            preparedStatement.setInt(3, export.getID_buyer());
            preparedStatement.setString(4, export.getWaybill());
            preparedStatement.setString(5, export.getUnit());
            preparedStatement.setString(6, export.getPrice());
            preparedStatement.setString(7, export.getNote());
            preparedStatement.setInt(8, export.getId_user());
            preparedStatement.setInt(9, export.getID_export());

            if(preparedStatement.executeUpdate() == 1) return true;
            else return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Export getExportByID(Export export) {
        final String selectQuery = "SELECT * FROM Export WHERE [Код операции экспорта] = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, export.getID_export());
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
                Export export_new = new Export(id_, typeOperation, date, idUser, idProduct, idBuyer, waybill, unit, price, note);
                return  export_new;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Export(0, null, null, 0, 0, 0, null, null, null, null);
    }

    @Override
    public ArrayList<ExportForTable> getExportByDate(Export export) {
        final String selectQuery = "SELECT Export.*, Products.[Название товара], Buyers.[Название фирмы]  FROM Export INNER JOIN Products ON Export.[Код товара] = Products.[Код товара] INNER JOIN Buyers ON Export.[Код Покупателя] = Buyers.[Код Покупателя] WHERE [Дата получения] = ?";

        ArrayList<ExportForTable> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, export.getDate());
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

                ExportForTable importCollection = new ExportForTable(id, type_operation, date, id_user, id_product,name_product, id_buyer,name_buyer, waybill, unit, price, note);
                //Export export = new Export(1, type_operation, date, id_user, id_product, id_buyer, waybill, unit, price, note );
                result.add(importCollection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<ExportForTable> getExportByIDBuyer(Export export) {
        final String selectQuery = "SELECT Export.*, Products.[Название товара], Buyers.[Название фирмы]  FROM Export INNER JOIN Products ON Export.[Код товара] = Products.[Код товара] INNER JOIN Buyers ON Export.[Код Покупателя] = Buyers.[Код Покупателя] WHERE Export.[Код Покупателя] = ?";

        ArrayList<ExportForTable> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, export.getID_buyer());
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

                ExportForTable importCollection = new ExportForTable(id, type_operation, date, id_user, id_product,name_product, id_buyer,name_buyer, waybill, unit, price, note);
                result.add(importCollection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<ExportForTable> getExportByIDUser(Export export) {
        final String selectQuery = "SELECT Export.*, Products.[Название товара], Buyers.[Название фирмы]  FROM Export INNER JOIN Products ON Export.[Код товара] = Products.[Код товара] INNER JOIN Buyers ON Export.[Код Покупателя] = Buyers.[Код Покупателя] WHERE Export.[ID-пользователя] = ?";

        ArrayList<ExportForTable> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, export.getId_user());
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

                ExportForTable importCollection = new ExportForTable(id, type_operation, date, id_user, id_product,name_product, id_buyer,name_buyer, waybill, unit, price, note);
                result.add(importCollection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateExportDateByID(int id, String date) {
        final String selectQuery = "UPDATE Export SET Дата = ? WHERE [Код операции экспорта] = ?";
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
    public boolean updateExportIdProductByID(int id, int idProduct) {
        final String selectQuery = "UPDATE Export SET [Код товара] = ? WHERE [Код операции экспорта] = ?";
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
    public boolean updateExportIdBuyerByID(int id, int idBuyer) {
        final String selectQuery = "UPDATE Export SET [Код поставщика] = ? WHERE [Код операции экспорта] = ?";
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
    public boolean updateExportWaybillByID(int id, String waybill) {
        final String selectQuery = "UPDATE Export SET [Номер накладной] = ? WHERE [Код операции экспорта] = ?";
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
    public boolean updateExportUnitByID(int id, String unit) {
        final String selectQuery = "UPDATE Export SET [Количество товара]= ? WHERE [Код операции экспорта] = ?";
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
    public boolean updateExportPriceByID(int id, String price) {
        final String selectQuery = "UPDATE Export SET Цена = ? WHERE [Код операции экспорта] = ?";
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
    public boolean updateExportNoteByID(int id, String note) {
        final String selectQuery = "UPDATE Export SET Примечание = ? WHERE [Код операции экспорта] = ?";
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

    @Override
    public ArrayList<Export> getExportByDate(String date) {
        return null;
    }
}
