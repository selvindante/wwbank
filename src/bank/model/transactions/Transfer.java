package bank.model.transactions;

import bank.model.Account;

/**
 * Created by Selvin
 * on 21.08.2014.
 */
public class Transfer extends Transaction{
    private String senderAccountId;
    private String receiverAccountId;
    private String senderClientId;
    private String receiverClientId;
    private int amount;

    public Transfer(Account sender, Account receiver, int amount) {
        super();
        this.senderAccountId = sender.getAccountId();
        this.senderClientId = sender.getClientId();
        this.receiverAccountId = receiver.getAccountId();
        this.receiverClientId = receiver.getClientId();
        this.amount = amount;
        //Transaction
        sender.setAmount(sender.getAmount() - amount);
        receiver.setAmount(receiver.getAmount() + amount);
        sender.addTransaction(this);
        receiver.addTransaction(this);
    }
}
