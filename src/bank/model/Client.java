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
    private int amount = 0;
    private Map<String, Account> accounts = null;

    public Client(String name, int age) {
        this.clientId = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
    }

    public Client(String name, String clientId, int age, Map<String, Account> accounts) {
        this.name = name;
        this.clientId = clientId;
        this.age = age;
        this.accounts = accounts;
        if(accounts != null) {
            for(Account acc: accounts.values()) if(acc != null) {
                this.amount += acc.getAmount();
            }
        }
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

    public int getAmount() {
        return  amount;
    }

    public Account getAccount(String id) {
        return accounts.get(id);
    }

    public void addAccount(Account account) {
        if(this.accounts == null) this.accounts = new HashMap<>();
        this.accounts.put(account.getAccountId(), account);
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }
}
