package bank.model.transactions;

/**
 * Created by Selvin
 * on 22.08.2014.
 */
public enum TransactionType {
    TRANSFER("Transfer"),
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal");

    private String title;

    TransactionType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
