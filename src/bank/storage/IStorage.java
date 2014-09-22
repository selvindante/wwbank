package bank.storage;

import bank.model.Account;
import bank.model.Client;
import bank.model.transactions.Transaction;

import java.util.Collection;

/**
 * Created by Selvin
 * on 22.08.2014.
 */
public interface IStorage {

    void addClient(Client c);

    void addAccount(Account acc);

    void addTransaction(Transaction tr);

    void updateClient(Client c);

    void updateAccount(Account acc);

    Client loadClient(String id);

    Account loadAccount(String id);

    void deleteClient(String id);

    void deleteAccount(String accountId);

    void deleteTransaction(String transactionId);

    Collection<Client> getAll();

    void clearDataBase();

    String getClientId(String accountId);
}
