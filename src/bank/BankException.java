package bank;

import bank.model.Client;

/**
 * User: gkislin
 * Date: 18.04.2014
 */
public class BankException extends RuntimeException {
    private Client client = null;
    private String id = null;

    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Throwable e) {
        super(message, e);
    }

    public BankException(String message, Client client) {
        super(message);
        this.client = client;
    }

    public BankException(String message, Client client, Throwable e) {
        super(message, e);
        this.client = client;
    }

    public BankException(String message, String uuid) {
        super(message);
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public String getId() {
        return id;
    }
}
