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
    public void addClient(final Client c) {
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
    }

    @Override
    public void addAccount(final Account acc) {
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
    }

    @Override
    public void addTransaction(final Transaction tr) {
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

    @Override
    public void updateClient(final Client c) {
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
            throw new BankException("Resume " + c.getClientId() + " not exist", c);
        }
        if(c.getAccounts() != null) {
            for (final Account acc : c.getAccounts().values())
                if (acc != null) {
                    updateAccount(acc);
                }
        }
    }

    @Override
    public void updateAccount(final Account acc) {
        Sql.execute("UPDATE accounts SET amount=? WHERE account_id=?",
                new SqlExecutor<Integer>() {
                    @Override
                    public Integer execute(PreparedStatement st) throws SQLException {
                        st.setInt(1, acc.getAmount());
                        st.setString(2, acc.getAccountId());
                        st.executeUpdate();
                        return null;
                    }
                });
    }

    @Override
    public Client loadClient(final String id) {
        Client cl = Sql.execute("SELECT c.id, c.name, c.age FROM clients AS c WHERE c.id=?",
            new SqlExecutor<Client>() {
                @Override
                public Client execute(PreparedStatement st) throws SQLException {
                    st.setString(1, id);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        return new Client(rs.getString("name"), id,  rs.getInt("age"), null);
                    }
                    throw new BankException("Client " + id + " is not found.");
                }
            });
        cl.setAccounts(loadAccounts(cl));
        return cl;
    }

    @Override
    public Account loadAccount(final String id) {
        Account a = Sql.execute("SELECT a.client_id, a.amount FROM accounts AS a WHERE a.account_id=?",
                new SqlExecutor<Account>() {
                    @Override
                    public Account execute(PreparedStatement st) throws SQLException {
                        st.setString(1, id);
                        ResultSet rs = st.executeQuery();
                        if (rs.next()) {
                            return new Account(id, rs.getString("client_id"),  rs.getInt("amount"));
                        }
                        throw new BankException("Account " + id + " is not found.");
                    }
                });
        a.setTransactions(loadTransactions(a));
        return a;
    }

    @Override
    public void deleteClient(final String id) {
        // Strategy
        Sql.execute("DELETE FROM transactions WHERE sender_client_id=? OR receiver_client_id=?", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
                ps.setString(2, id);
                ps.executeUpdate();
                return null;
            }
        });
        Sql.execute("DELETE FROM accounts WHERE client_id=?", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
                ps.executeUpdate();
                return null;
            }
        });
        int cntCl = Sql.execute("DELETE FROM clients WHERE id=?", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
                return ps.executeUpdate();
            }
        });
        if (cntCl == 0) {
            throw new BankException("Client " + id + " not exist", id);
        }
    }

    @Override
    public void deleteAccount(final String accountId) {
        Sql.execute("DELETE FROM transactions WHERE sender_account_id=? OR receiver_account_id=?", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, accountId);
                ps.setString(2, accountId);
                ps.executeUpdate();
                return null;
            }
        });
        int cntAc = Sql.execute("DELETE FROM accounts WHERE account_id=?", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, accountId);
                return ps.executeUpdate();
            }
        });
        if (cntAc == 0) {
            throw new BankException("Transaction " + accountId + " not exist", accountId);
        }
    }

    @Override
    public void deleteTransaction(final String transactionId) {
        int cntTr = Sql.execute("DELETE FROM transactions WHERE transaction_id=?", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, transactionId);
                return ps.executeUpdate();
            }
        });
        if (cntTr == 0) {
            throw new BankException("Transaction " + transactionId + " not exist", transactionId);
        }
    }

    @Override
    public Collection<Client> getAll() {
        Collection<Client> clients = Sql.execute("SELECT c.id, c.name, c.age  FROM clients AS c order by c.name, c.id",
                new SqlExecutor<Collection<Client>>() {
                    @Override
                    public Collection<Client> execute(PreparedStatement st) throws SQLException {
                        List<Client> res = new LinkedList<>();
                        ResultSet rs = st.executeQuery();
                        while (rs.next()) {
                            String id = rs.getString("id");
                            res.add(new Client(rs.getString("name"), id, rs.getInt("age"), null));
                        }
                        return res;
                    }
                });
        for(Client cl: clients) {
            cl.setAccounts(loadAccounts(cl));
        }
        return clients;
    }

    @Override
    public void clearDataBase() {
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

    @Override
    public String getClientId(final String accountId) {
        String id = Sql.execute("SELECT a.client_id FROM accounts AS a WHERE a.account_id=?",
                new SqlExecutor<String>() {
                    @Override
                    public String execute(PreparedStatement st) throws SQLException {
                        st.setString(1, accountId);
                        ResultSet rs = st.executeQuery();
                        if (rs.next()) {
                            return rs.getString("client_id");
                        }
                        throw new BankException("Account ID " + accountId + " is not found.");
                    }
                });
        return id;
    }

    private Map<String, Account> loadAccounts(Client cl) {
        final String id = cl.getClientId();
        Map<String, Account> accounts = Sql.execute("SELECT a.account_id, a.client_id, a.amount FROM accounts AS a WHERE a.client_id=?",
                new SqlExecutor<Map<String, Account>>() {
                    @Override
                    public Map<String, Account> execute(PreparedStatement st) throws SQLException {
                        st.setString(1, id);
                        ResultSet rs = st.executeQuery();
                        Map<String, Account> res = new HashMap<>();
                        while (rs.next()) {
                            Account acc = new Account(rs.getString("account_id"), id, rs.getInt("amount"));
                            res.put(acc.getAccountId(), acc);
                        }
                        return res;
                    }
                });
        cl.setAccounts(accounts);
        for(Account acc: cl.getAccounts().values()) if(acc != null) {
            acc.setTransactions(loadTransactions(acc));
        }
        return  accounts;
    }

    private Map<String, Transaction> loadTransactions(Account acc) {
        final String accId = acc.getAccountId();
        Map<String, Transaction> transactions = Sql.execute("SELECT t.transaction_id, t.type, t.date, t.amount, t.sender_client_id, t.sender_account_id, t.receiver_client_id, t.receiver_account_id FROM transactions AS t WHERE t.sender_account_id=? OR t.receiver_account_id=?",
                new SqlExecutor<Map<String, Transaction>>() {
                    @Override
                    public Map<String, Transaction> execute(PreparedStatement st) throws SQLException {
                        st.setString(1, accId);
                        st.setString(2, accId);
                        ResultSet rs = st.executeQuery();
                        Map<String, Transaction> res = new HashMap<>();
                        while (rs.next()) {
                            Transaction tr = new Transaction(rs.getString("transaction_id"), rs.getString("type"), rs.getString("date"), rs.getInt("amount"),
                                    rs.getString("sender_client_id"), rs.getString("sender_account_id"),
                                    rs.getString("receiver_client_id"), rs.getString("receiver_account_id"));
                            res.put(tr.getTransactionId(), tr);
                        }
                        return res;
                    }
                });
        return transactions;
    }
}
