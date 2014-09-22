package bank.web;

import bank.BankException;
import bank.Config;
import bank.model.Account;
import bank.model.transactions.Transaction;
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
        String currentAccountId = request.getParameter("currentAccountId");
        String currentClientId = storage.getClientId(currentAccountId);
        String receiverAccountId = request.getParameter("receiverAccountId");
        String receiverClientId = storage.getClientId(receiverAccountId);
        storage.addTransaction(new Transaction(type, amount, currentClientId, currentAccountId, receiverClientId, receiverAccountId));
        Account cAcc = storage.loadAccount(currentAccountId);
        Account rAcc = storage.loadAccount(receiverAccountId);
        switch ("type") {
            case"deposit":
                cAcc.setAmount(cAcc.getAmount() + amount);
                storage.updateAccount(cAcc);
                break;
            case"withdrawal":
                cAcc.setAmount(cAcc.getAmount() - amount);
                storage.updateAccount(cAcc);
                break;
            case"transfer":
                rAcc.setAmount(rAcc.getAmount() + amount);
                storage.updateAccount(rAcc);
                cAcc.setAmount(cAcc.getAmount() - amount);
                storage.updateAccount(cAcc);
                break;
            default: throw new BankException("Unknown transaction type!");
        }
        response.sendRedirect("account?id=" + currentAccountId + "&action=view");
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
