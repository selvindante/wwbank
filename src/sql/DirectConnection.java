package sql;

import bank.BankException;
import sql.database.SqlSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: gkislin
 * Date: 06.11.13
 */
public class DirectConnection implements ConnectionFactory {
    private String url = null;
    private String user = null;
    private String password = null;

    public DirectConnection(SqlSettings stn)  {
        this.url = stn.getUrl();
        this.user = stn.getUser();
        this.password = stn.getPassword();
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (Exception e) {
            throw new BankException("Driver initialization exception", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
