package model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Selvin
 * on 21.08.2014.
 */
public class Client {
    private String name = null;
    private String clientId;
    private int age;
    private Map<String, Account> accounts =  new HashMap<>();

    public Client() {
        this.clientId = UUID.randomUUID().toString();
    }

    public Client(String name, int age) {
        this.clientId = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
    }

    public String getClientId() {
        return clientId;
    }

    public void addAccount(Account account) {
        this.accounts.put(account.getAccountId(), account);
    }
}
