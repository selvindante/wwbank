package sql;

import bank.BankException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User: gkislin
 * Date: 14.07.2014
 */
public class Sql {
    public static ConnectionFactory CONN_FACTORY =
            new DirectConnection();

    public static <T> T execute(String sql, SqlExecutor<T> executor) {
        try (Connection conn = Sql.CONN_FACTORY.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw new BankException("SQL failed", e);
        }
    }
}
