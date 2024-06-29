package model;
import java.sql.*;
import oracle.jdbc.pool.*;
public class DataSource
{
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String JDBC_USERNAME = "c##EventManager";
    private static final String JDBC_PASSWORD = "advjava";

    private static OracleConnectionPoolDataSource dataSource;
    static {
        try {
            dataSource = new OracleConnectionPoolDataSource();
            dataSource.setURL(JDBC_URL);
            dataSource.setUser(JDBC_USERNAME);
            dataSource.setPassword(JDBC_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
}

