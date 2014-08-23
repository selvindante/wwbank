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

    void saveClient(Client c);

    void addAccount(Account acc);

    void addTransaction(Transaction tr);

    void updateClient(Client c);

    Client loadClient(String id);

    void deleteClient(String id);

    Collection<Client> getAll();

    void clearDataBase();
}
