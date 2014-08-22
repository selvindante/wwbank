package bank.model;

import bank.model.transactions.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Selvin
 * on 21.08.2014.
 */
public class Account {
    private String clientId;
    private String accountId;
    private int amount;
    Map<String, Transaction> transactions = new HashMap<>();

    public Account(Client client) {
        this.accountId = UUID.randomUUID().toString();
        this.clientId = client.getClientId();
        this.amount = 0;
        client.addAccount(this);
    }

    public Account(Client client, int amount) {
        this(client);
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addTransaction(Transaction tr) {
        transactions.put(tr.getTransactionId(), tr);
    }

    public Map<String, Transaction> getTransactions() {
        return transactions;
    }
}
