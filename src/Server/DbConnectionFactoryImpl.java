package Server;

import java.sql.Statement;

public class DbConnectionFactoryImpl implements DbConnectionFactory{

    private static final String url = "jdbc:sqlserver://DESKTOP-0DVQIQS\\SQLEXPRESS:1433;databaseName=Import-Export;";
    private static final String user = "sa";
    private static final String password = "password";

    @Override
    public DbConnection create (){ // TODO: 19.11.2019 брать из конфигурации
        return new DbConnection(url, user, password);
    }
}
