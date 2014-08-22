package bank.model;

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

    public Client(String clientId, String name, int age) {
        this.age = age;
        this.clientId = clientId;
        this.name = name;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void addAccount(Account account) {
        this.accounts.put(account.getAccountId(), account);
    }
}
