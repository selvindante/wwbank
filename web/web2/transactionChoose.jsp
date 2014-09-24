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
    String accountId = (String) (request.getAttribute("accountId"));
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
    <header>Creating of new transaction. Step 1.</header>
    <form id="transaction" method="get" action="transaction" enctype="application/x-www-form-urlencoded">
        <dl>
            <dt>Type:</dt>
            <%for(TransactionType tt: TransactionType.values()) {%>
                <p><input type="radio" name="type" value="<%=tt.toString()%>"> <%=tt.toString()%></p>
            <%}%>
        </dl>
        <button type="submit"><%-- onclick="return validate()--%>Next</button>
    </form>
    <button onclick="window.history.back()">Cancel</button>

</body>
</html>
