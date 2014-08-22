package bank.storage;

import bank.BankException;
import bank.model.Account;
import bank.model.Client;
import bank.model.transactions.Transaction;
import sql.Sql;
import sql.SqlExecutor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Selvin
 * on 22.08.2014.
 */
public class SqlStorage implements IStorage {
    @Override
    public void save(final Client c) {
        Sql.execute("INSERT INTO clients (id, name, age) VALUES(?,?,?)",
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
        Map<String, Transaction> transactions = new HashMap<>();
        for(final Account acc: c.getAccounts().values()) if(acc != null) {
            Sql.execute("INSERT INTO accounts (account_id, client_id, amount) VALUES(?,?,?)",
                    new SqlExecutor<Void>() {
                        @Override
                        public Void execute(PreparedStatement st) throws SQLException {
                            st.setString(1, acc.getAccountId());
                            st.setString(2, acc.getClientId());
                            st.setInt(3, acc.getAmount());
                            st.execute();
                            return null;
                        }
                    }
            );
            for(Transaction tr: acc.getTransactions().values()) {
                transactions.put(tr.getTransactionId(), tr);
            }
        }
        for(final Transaction tr: transactions.values()) if (tr != null) {
            Sql.execute("INSERT INTO transactions VALUES(?,?,?,?,?,?,?,?)",
                    new SqlExecutor<Void>() {
                        @Override
                        public Void execute(PreparedStatement st) throws SQLException {
                            st.setString(1, tr.getTransactionId());
                            st.setString(2, tr.getType());
                            st.setString(3, tr.getDate());
                            st.setInt(4, tr.getAmount());
                            st.setString(5, tr.getSenderClientId());
                            st.setString(6, tr.getSenderAccountId());
                            st.setString(7, tr.getReceiverClientId());
                            st.setString(8, tr.getReceiverAccountId());
                            st.execute();
                            return null;
                        }
                    }
            );
        }
    }

    @Override
    public void update(final Client c) {
        int cnt = Sql.execute("UPDATE clients SET name=?, age=? WHERE id=?",
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
        return Sql.execute("SELECT c.id, c.name, c.age FROM clients AS c WHERE c.id=?",
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
        int cnt = Sql.execute("DELETE FROM clients WHERE id=?", new SqlExecutor<Integer>() {
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
        return Sql.execute("SELECT c.id, c.name, c.age  FROM clients AS c order by c.name, c.id",
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
        Sql.execute("DELETE FROM transactions", new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement ps) throws SQLException {
                ps.execute();
                return null;
            }
        });
        Sql.execute("DELETE FROM accounts", new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement ps) throws SQLException {
                ps.execute();
                return null;
            }
        });
        Sql.execute("DELETE FROM clients", new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement ps) throws SQLException {
                ps.execute();
                return null;
            }
        });
    }
}
