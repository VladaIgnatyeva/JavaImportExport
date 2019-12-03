package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    private  String url ;
    private  String user ;
    private  String password;

    DbConnection(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection connect(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password); //Установить соединение с БД, используя загруженный драйвер
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
