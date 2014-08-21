package model.transactions;

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

    public Transaction() {
        this.transactionId = UUID.randomUUID().toString();
        this.type = getClass().getSimpleName();
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        this.date = df.format(now);
    }

    public String getTransactionId() {
        return transactionId;
    }
}
