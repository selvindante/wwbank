<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 24.09.2014
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
    <title>Withdraw</title>
</head>
<body>
<header>Creating of new transaction. Step 2.</header>
<form id="transaction" method="post" action="transaction" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="type" value="Transfer">
    <dl>
        <dt>Amount:</dt>
        <dd><input type="text" name="amount" size=50 value=""></dd>
    </dl>
    <dl>
        <dt>Sender Account ID:</dt>
        <dd><input type="text" name="senderAccountId" size=50 value=""></dd>
    </dl>
    <dl>
        <dt>Receiver Account ID:</dt>
        <dd><input type="text" name="receiverAccountId" size=50 value=""></dd>
    </dl>
    <button type="submit"><%-- onclick="return validate()--%>Next</button>
</form>
<button onclick="window.history.back()">Cancel</button>
</body>
</html>
