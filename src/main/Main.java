package main;

import bank.model.Account;
import bank.model.Client;
import bank.model.transactions.Transaction;
import bank.model.transactions.Transfer;
import bank.storage.IStorage;
import bank.storage.SqlStorage;

/**
 * Created by Selvin
 * on 21.08.2014.
 */
public class Main {
    public static void main(String[] args) {

        Client cl1 = new Client("Bob", 34);
        Client cl2 = new Client("Jack", 29);
        Client cl3 = new Client("Joe", 41);
        Client cl4 = new Client("Arvin", 37);
        Client cl5 = new Client("Johny", 25);

        Account ac1 = new Account(cl1, 1000);
        Account ac2 = new Account(cl2, 360);
        Account ac3 = new Account(cl4, 230);
        Account ac4 = new Account(cl4, 325);
        Account ac5 = new Account(cl5, 570);

        Transaction tr1 = new Transfer(ac1, ac2, 350);

        IStorage st = new SqlStorage();
    }
}
