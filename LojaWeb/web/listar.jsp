<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, model.Jogo"%>
<!DOCTYPE html>
<html>
<head>
    <link href="estilos/styles.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Jogos</title>
</head>
<body>
    <h2>Lista de Jogos</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Desenvolvedora</th>
            <th>Distribuidora</th>
            <th>Lançamento</th>
            <th>Nota</th>
            <th>Preço</th>
            <th>Classificação</th>
            <th>Plataformas</th>
            <th>Ações</th>
        </tr>
        <% 
            List<Jogo> jogos = (List<Jogo>) request.getAttribute("jogos");
            if (jogos == null || jogos.isEmpty()) {
        %>
        <tr>
            <td colspan="11">Nenhum jogo cadastrado.</td>
        </tr>
        <% 
            } else {
                for (Jogo jogo : jogos) {
        %>
        <tr>
            <td><%= jogo.getId() %></td>
            <td><%= jogo.getNome() %></td>
            <td><%= jogo.getDescricao() != null ? jogo.getDescricao() : "" %></td>
            <td><%= jogo.getDesenvolvedora() != null ? jogo.getDesenvolvedora() : "" %></td>
            <td><%= jogo.getDistribuidora() != null ? jogo.getDistribuidora() : "" %></td>
            <td><%= jogo.getLancamento() != null ? jogo.getLancamento() : "" %></td>
            <td><%= jogo.getNota() %></td>
            <td><%= jogo.getPreco() %></td>
            <td><%= jogo.getClassIndicativa() %></td>
            <td><%= jogo.getPlataformas() != null ? jogo.getPlataformas() : "" %></td>
            <td>
                <a href="ControleJogo?op=DELETAR&id=<%= jogo.getId() %>">Deletar</a>
            </td>
        </tr>
        <% 
                }
            }
        %>
    </table>
    <br>
    <a href="index.jsp">Voltar</a>
</body>
</html>