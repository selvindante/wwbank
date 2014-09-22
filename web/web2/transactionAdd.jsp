<%@ page import="bank.model.transactions.TransactionType" %>
<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 22.09.2014
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    TransactionType trans = null;
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
    <title>Creating of new transaction</title>
</head>
<body>
    <header>Creating of new client</header>
    <%-- TODO Link with TransactionServlet, fix checkbox... --%>
    <form id="transaction" method="post" action="transaction" enctype="application/x-www-form-urlencoded">
        <dl>
            <dt>Type:</dt>
            <%for(TransactionType tt: TransactionType.values()) {%>
                <td><%=tt.toString()%> <input type="radio" name="<%=tt.toString().toLowerCase()%>" value="<%=tt.toString()%>"></td>
            <%}%>
        </dl>
        <dl>
            <dt>Amount:</dt>
            <dd><input type="text" name="amount" size=50 value=""></dd>
        </dl>
        <dl>
            <dt>Sender account ID:</dt>
            <dd><input type="text" name="currentAccountId" size=50 value=""></dd>
        </dl>
        <dl>
            <dt>Receiver Account ID:</dt>
            <dd><input type="text" name="receiverAccountId" size=50 value=""></dd>
        </dl>
        <button type="submit"><%-- onclick="return validate()--%>Save</button>
    </form>
    <button onclick="window.history.back()">Cancel</button>

</body>
</html>
