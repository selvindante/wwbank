package bank.web;

import bank.BankException;
import bank.Config;
import bank.model.Account;
import bank.model.transactions.Transaction;
import bank.model.transactions.TransactionType;
import bank.storage.IStorage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Selvin
 * on 22.09.2014.
 */
public class TransactionServlet extends HttpServlet {
    private IStorage storage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        int amount = Integer.parseInt(request.getParameter("amount"));

        switch (type) {
            case"Deposit":
                String receiverAccountId = request.getParameter("receiverAccountId");
                Account rAcc = storage.loadAccount(receiverAccountId);
                storage.addTransaction(new Transaction(TransactionType.DEPOSIT, rAcc, amount));
                storage.updateAccount(rAcc);
                response.sendRedirect("account?id=" + receiverAccountId + "&action=view");
                break;
            case"Withdrawal":
                String senderAccountId = request.getParameter("senderAccountId");
                Account sAcc = storage.loadAccount(senderAccountId);
                storage.addTransaction(new Transaction(TransactionType.WITHDRAWAL, sAcc, amount));
                storage.updateAccount(sAcc);
                response.sendRedirect("account?id=" + senderAccountId + "&action=view");
                break;
            case"Transfer":
                String senderAccountId1 = request.getParameter("senderAccountId");
                String receiverAccountId1 = request.getParameter("receiverAccountId");
                Account rAcc1 = storage.loadAccount(receiverAccountId1);
                Account sAcc1 = storage.loadAccount(senderAccountId1);
                storage.addTransaction(new Transaction(TransactionType.TRANSFER, sAcc1, rAcc1, amount));
                storage.updateAccount(rAcc1);
                storage.updateAccount(sAcc1);
                response.sendRedirect("account?id=" + senderAccountId1 + "&action=view");
                break;
            default: throw new BankException("Unknown transaction type!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getStorage();
    }
}
