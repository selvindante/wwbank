package bank.model.transactions;

import bank.BankException;
import bank.model.Account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Selvin
 * on 21.08.2014.
 */
public class Transaction {
    private String transactionId;
    private String type;
    private String date;
    private String senderClientId = null;
    private String senderAccountId = null;
    private String receiverClientId = null;
    private String receiverAccountId = null;
    private int amount;

    public Transaction(TransactionType type, Account sender, Account receiver, int amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.type = type.toString();
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        this.date = df.format(now);
        this.senderClientId = sender.getClientId();
        this.senderAccountId = sender.getAccountId();
        this.receiverClientId = receiver.getClientId();
        this.receiverAccountId = receiver.getAccountId();
        this.amount = amount;
        if(this.type.equals("Transfer")) {
            //Transaction
            sender.setAmount(sender.getAmount() - amount);
            receiver.setAmount(receiver.getAmount() + amount);
            sender.addTransaction(this);
            receiver.addTransaction(this);
        }
        else throw new BankException("Unknown transaction type!");
    }

    public Transaction(TransactionType type, Account account, int amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.type = type.toString();
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        this.date = df.format(now);
        this.amount = amount;
        switch(this.type) {
            case"Deposit":
                this.receiverClientId = account.getClientId();
                this.receiverAccountId = account.getAccountId();
                //Transaction
                account.setAmount(account.getAmount() + amount);
                account.addTransaction(this);
                break;
            case"Withdrawal":
                this.senderClientId = account.getClientId();
                this.senderAccountId = account.getAccountId();
                //Transaction
                account.setAmount(account.getAmount() - amount);
                account.addTransaction(this);
                break;
            default: throw new BankException("Unknown transaction type!");
        }
    }

    public Transaction(String transactionId, String type, String date, int amount, String senderClientId, String senderAccountId, String receiverClientId, String receiverAccountId) {
        this.transactionId = transactionId;
        this.type = type;
        this.date = date;
        this.senderClientId = senderClientId;
        this.senderAccountId = senderAccountId;
        this.receiverClientId = receiverClientId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getSenderAccountId() {
        return senderAccountId;
    }

    public String getReceiverAccountId() {
        return receiverAccountId;
    }

    public String getSenderClientId() {
        return senderClientId;
    }

    public String getReceiverClientId() {
        return receiverClientId;
    }

    public int getAmount() {
        return amount;
    }
}
