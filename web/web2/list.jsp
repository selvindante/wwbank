<%@ page import="bank.Config" %>
<%@ page import="bank.model.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>List of bank clients</title>
</head>
<body>
<header>List of bank clients</header>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Name</th>
            <th>Age</th>
        </tr>
        <% for (Client c : Config.getStorage().getAll()) { %>
        <tr>
            <td>
                <a href="client?id=<%=c.getClientId()%>&action=view"><%=c.getName()%>
                </a>
            </td>
            <td>
                <%=c.getAge()%>
            </td>
        </tr>
        <% } %>
    </table>
</section>
</body>
</html>