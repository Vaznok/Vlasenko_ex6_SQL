package tasks.jdbc;


import java.sql.*;

public class ConnectionJDBC {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/foxminded_db?" +
            "autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qwerty";

    public static Connection getConnection() throws SQLException {
        Connection connection;
        Driver driver;

        try   {
            driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
        }
        catch (SQLException e1) {
            System.out.println("Драйвер не зарегистрировался");
            throw new SQLException();
        }
        try {
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            if (!connection.isClosed())
                System.out.println("Соединение установлено");
            connection.setAutoCommit(false);
        }catch (SQLException ex){
            System.err.println("Соединение не установлено");
            throw new SQLException();
        }
        return connection;
    }
}