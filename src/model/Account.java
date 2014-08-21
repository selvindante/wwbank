package model;

import model.transactions.Transaction;

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

    public Account(String clientId) {
        this.accountId = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.amount = 0;
    }

    public Account(String clientId, int amount) {
        this(clientId);
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
}
