package bank.storage;

import bank.model.Client;

import java.util.Collection;

/**
 * Created by Selvin
 * on 22.08.2014.
 */
public interface IStorage {

    void save(Client c);

    void update(Client c);

    Client load(String id);

    void delete(String id);

    Collection<Client> getAll();

    void clear();
}
