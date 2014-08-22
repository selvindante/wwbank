package bank.storage;

import bank.BankException;
import bank.model.Client;
import sql.Sql;
import sql.SqlExecutor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Selvin
 * on 22.08.2014.
 */
public class SqlStorage implements IStorage {
    @Override
    public void save(final Client c) {
        Sql.execute("INSERT INTO \"Clients\" (id, name, age) VALUES(?,?,?)",
                new SqlExecutor<Void>() {
                    @Override
                    public Void execute(PreparedStatement st) throws SQLException {
                        st.setString(1, c.getClientId());
                        st.setString(2, c.getName());
                        st.setInt(3, c.getAge());
                        st.execute();
                        return null;
                    }
                }
        );
    }

    @Override
    public void update(final Client c) {
        int cnt = Sql.execute("UPDATE \"Clients\" SET name=?, age=? WHERE id=?",
                new SqlExecutor<Integer>() {
                    @Override
                    public Integer execute(PreparedStatement st) throws SQLException {
                        st.setString(1, c.getName());
                        st.setInt(2, c.getAge());
                        st.setString(3, c.getClientId());
                        return st.executeUpdate();
                    }
                });
        if (cnt == 0) {
            throw new BankException("Resume " + c.getClientId() + "not exist", c);
        }
    }

    @Override
    public Client load(final String id) {
        return Sql.execute("SELECT c.id, c.name, c.age FROM \"Clients\" AS c WHERE c.id=?",
                new SqlExecutor<Client>() {
                    @Override
                    public Client execute(PreparedStatement st) throws SQLException {
                        st.setString(1, id);
                        ResultSet rs = st.executeQuery();
                        if (rs.next()) {
                            return new Client(id, rs.getString("name"), rs.getInt("age"));
                        }
                        throw new BankException("Client " + id + " is not found.");
                    }
                });
    }

    @Override
    public void delete(final String id) {
        // Strategy
        int cnt = Sql.execute("DELETE FROM \"Clients\" WHERE id=?", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
                return ps.executeUpdate();
            }
        });
        if (cnt == 0) {
            throw new BankException("Client " + id + "not exist", id);
        }
    }

    @Override
    public Collection<Client> getAll() {
        return Sql.execute("SELECT c.id, c.name, c.age  FROM \"Clients\" AS c order by c.name, c.id",
                new SqlExecutor<Collection<Client>>() {
                    @Override
                    public Collection<Client> execute(PreparedStatement st) throws SQLException {
                        List<Client> res = new LinkedList<>();
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {
                            String id = rs.getString("id");
                            res.add(new Client(id, rs.getString("name"), rs.getInt("age")));
                        }
                        return res;
                    }
                });
    }

    @Override
    public void clear() {
        Sql.execute("DELETE FROM \"Clients\"", new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement ps) throws SQLException {
                ps.execute();
                return null;
            }
        });
    }
}
