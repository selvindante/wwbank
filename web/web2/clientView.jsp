
<%@ page import="bank.model.Account" %>
<%@ page import="bank.model.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Client client = (Client) (request.getAttribute("client"));
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
    <title>Client: <%=client.getName()%>
    </title>
</head>
<body>
<header>
    Personal page of client: <%=client.getName()%>
    &nbsp; &nbsp; <button>Edit</button>
    &nbsp; &nbsp; <button onclick="document.location.href='client?id=<%=client.getClientId()%>&action=delete'">Delete</button>
</header>
<section>
    <p>
    Name: ${client.getName()}
    <p>
    Age: ${client.getAge()}
    <p>
    <%--TODO Fix total amount display--%>
    Amount: ${client.getAmount()}
    <p>
    Client ID: ${client.getClientId()}
    <p>
    <%if(!client.getAccounts().isEmpty()) {%>
        List of client accounts:
        <p>
        &nbsp; &nbsp; <button onclick="document.location.href='account?id=<%=client.getClientId()%>&action=create'">Add</button>
        &nbsp; &nbsp; <button>Clear</button>
        <p>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Account ID</th>
                <th>Amount</th>
            </tr>
            <% for (Account acc : client.getAccounts().values()) if(acc != null) { %>
            <tr>
                <td>
                    <a href="account?id=<%=acc.getAccountId()%>&action=view"><%=acc.getAccountId()%>
                    </a>
                </td>
                <td>
                    <%=acc.getAmount()%>
                </td>
            </tr>
            <% } %>
        </table>
        <p>
    <% }
    else {%>
        This client does not have accounts. You can <button onclick="document.location.href='account?id=<%=client.getClientId()%>&action=create'">Add</button> account.
        <p>
    <% } %>
    <button onclick="document.location.href='list.jsp'">ОК</button>
</section>
</body>
</html>