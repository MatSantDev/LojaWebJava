<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="estilos/styles.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manter Jogo</title>
</head>
<body>
    <h2>Manter Jogo</h2>
    <form action="ControleJogo" method="post">
        <label>ID (para atualizar ou deletar):</label>
        <input type="number" name="id" min="1"><br><br>

        <label>Nome:</label>
        <input type="text" name="nome"><br><br>

        <label>Descrição:</label>
        <textarea name="descricao"></textarea><br><br>

        <label>Desenvolvedora:</label>
        <input type="text" name="desenvolvedora"><br><br>

        <label>Distribuidora:</label>
        <input type="text" name="distribuidora"><br><br>

        <label>Data de Lançamento:</label>
        <input type="date" name="lancamento"><br><br>

        <label>Nota (0 a 10):</label>
        <input type="number" name="nota" step="0.1" min="0" max="10"><br><br>

        <label>Preço:</label>
        <input type="number" name="preco" step="0.01"><br><br>

        <label>Classificação Indicativa:</label>
        <select name="classIndicativa">
            <option value="LIVRE">Livre</option>
            <option value="10">10</option>
            <option value="12">12</option>
            <option value="14">14</option>
            <option value="16">16</option>
            <option value="18">18</option>
        </select><br><br>

        <label>Plataformas (separadas por vírgula):</label>
        <input type="text" name="plataformas" placeholder="PC,XBOX,PS5"><br><br>

        <input type="submit" name="op" value="CADASTRAR">
        <input type="submit" name="op" value="ATUALIZAR">
        <input type="submit" name="op" value="DELETAR">
    </form>
    <br>
    <a href="ControleJogo?op=LISTAR">Ver lista de jogos</a>
</body>
</html>