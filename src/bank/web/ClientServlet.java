package bank.web;

import bank.Config;
import bank.model.Client;
import bank.storage.IStorage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Selvin
 * on 26.08.2014.
 */
public class ClientServlet extends HttpServlet {
    private IStorage storage;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String age = request.getParameter("age");
        if(age.length() == 0) {
            response.sendRedirect("list");
            return;
        }
        for(Character c: age.toCharArray()) {
            if(!Character.isDigit(c)) {
                //TODO Paste error window with description
                response.sendRedirect("list");
                return;
            }
        }
        Client cl = new Client(request.getParameter("name"), Integer.parseInt(age));
        storage.addClient(cl);
        response.sendRedirect("client?id=" + cl.getClientId() + "&action=view");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        Client c = null;

        switch (action) {
            case "delete":
                storage.deleteClient(id);
                response.sendRedirect("list");
                return;
            case "view":
                c = storage.loadClient(id);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("client", c);
        request.getRequestDispatcher("/clientView.jsp").forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getStorage();
    }
}
