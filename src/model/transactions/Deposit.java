package model.transactions;

import model.Account;

/**
 * Created by Selvin
 * on 21.08.2014.
 */
public class Deposit extends Transaction {
    private String accountId;
    private String clientId;
    private int amount;

    public Deposit(Account account, int amount) {
        super();
        this.accountId = account.getAccountId();
        this.clientId = account.getClientId();
        this.amount = amount;
        //Transaction
        account.setAmount(account.getAmount() + amount);
    }
}
