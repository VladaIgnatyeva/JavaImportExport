package Server;

import java.sql.Statement;

public interface DbConnectionFactory {

    DbConnection create();

}
