<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="estilos/styles.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Resultado Cadastrar</title>
</head>
<body>
    <% String msg = (String) request.getAttribute("msg"); %>
    <h1>Resultado: <% out.println(msg); %></h1>
    <a href="index.jsp">Voltar</a>
</body>
</html>