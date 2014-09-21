<%--
  Created by IntelliJ IDEA.
  User: Selvin
  Date: 21.09.2014
  Time: 13:03
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
    <title>Creating of new client</title>
</head>
<body>
    <header>Creating of new client</header>
    <form id="client" method="post" action="client" enctype="application/x-www-form-urlencoded">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" name="name" size=50 value=""></dd>
        </dl>
        <dl>
            <dt>Age:</dt>
            <dd><input type="text" name="age" size=50 value=""></dd>
        </dl>
        <button type="submit"><%-- onclick="return validate()--%>Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>

</body>
</html>
