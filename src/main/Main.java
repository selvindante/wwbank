package main;

import model.Account;
import model.Client;
import model.transactions.Transaction;
import model.transactions.Transfer;

/**
 * Created by Selvin
 * on 21.08.2014.
 */
public class Main {
    public static void main(String[] args) {

        Client cl1 = new Client("Bob", 34);
        Client cl2 = new Client("Jack", 29);

        Account ac1 = new Account(cl1.getClientId(), 1000);
        Account ac2 = new Account(cl2.getClientId(), 360);

        cl1.addAccount(ac1);
        cl2.addAccount(ac2);

        Transaction tr1 = new Transfer(ac1, ac2, 350);

        ac1.addTransaction(tr1);
        ac2.addTransaction(tr1);
    }
}
