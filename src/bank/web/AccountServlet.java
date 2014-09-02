package bank.web;

import bank.Config;
import bank.model.Account;
import bank.storage.IStorage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Selvin
 * on 02.09.2014.
 */
public class AccountServlet extends HttpServlet {
    private IStorage storage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        Account ac = null;

        switch (action) {
            case "delete":
                storage.deleteClient(id);
                response.sendRedirect("list");
                return;
            case "create":
                //TODO create client
                break;
            case "view":
                ac = storage.loadAccount(id);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("account", ac);
        request.getRequestDispatcher("/accountView.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getStorage();
    }
}
