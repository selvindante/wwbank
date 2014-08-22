package sql;

import bank.BankException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: gkislin
 * Date: 06.11.13
 */
public class DirectConnection implements ConnectionFactory {

    public DirectConnection()  {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (Exception e) {
            throw new BankException("Driver initialization exception", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/bankClients",
                "postgres", "123456");
    }
}
