<%@ page import="bank.model.Account" %>
<%@ page import="bank.model.transactions.Transaction" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 02.09.2014
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Account account = (Account) (request.getAttribute("account"));
%>

<head>
    <link rel="stylesheet" href="css/style.css">
    <STYLE type="text/css">
        th, td {
            padding: 4px 10px 4px 0;
            vertical-align: top;
        }

        tr {
            border-bottom: 1px solid #DDDDDD;
        }

        table {
            margin-bottom: 1.4em;
        }

        table {
            border-collapse: collapse;
            border-spacing: 0;
        }
    </STYLE>
    <title>Account: <%=account.getAccountId()%>
    </title>
</head>
<body>
<header>Account: <%=account.getAccountId()%> &nbsp; <button>Delete</button></header>
<section>
    <p>
        Client ID: ${account.getClientId()}
    <p>
        Account ID: ${account.getAccountId()}
    <p>
        Amount: ${account.getAmount()}
    <p>
    <%if(!account.getTransactions().isEmpty()) {%>
    List of transactions:
    <p>
        &nbsp; &nbsp; <button onclick="document.location.href='transactionChoose.jsp'">Add</button>
        &nbsp; &nbsp; <button>Clear</button>
    <p>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Date</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Sender client ID</th>
            <th>Sender account ID</th>
            <th>Receiver client ID</th>
            <th>Receiver account ID</th>
            <th>Transaction ID</th>
        </tr>
        <% for (Transaction tr : account.getTransactions().values()) if(tr != null) { %>
        <tr>
            <td>
                <%=tr.getDate()%>
            </td>
            <td>
                <%=tr.getType()%>
            </td>
            <td>
                <%=tr.getAmount()%>
            </td>
            <td>
                <%=tr.getSenderClientId()%>
            </td>
            <td>
                <%=tr.getSenderAccountId()%>
            </td>
            <td>
                <%=tr.getReceiverClientId()%>
            </td>
            <td>
                <%=tr.getReceiverAccountId()%>
            </td>
            <td>
                <%=tr.getTransactionId()%>
            </td>
        </tr>
        <% } %>
    </table>
    <p>
            <% }
        else {%>
        This account does not have transactions. You can <button onclick="document.location.href='transactionChoose.jsp'">Add</button> transaction.
    <p>
            <% } %>
        <button onclick="document.location.href='client?id=<%=account.getClientId()%>&action=view'">ОК</button>
</section>
</body>
</html>
