
<%@ page import="bank.model.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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
<section>

</section>
</body>
</html>