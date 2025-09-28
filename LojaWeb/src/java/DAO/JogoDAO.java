package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Jogo;
import util.Conexao;

public class JogoDAO {

    public void cadastrar(Jogo j) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement(
            "INSERT INTO Jogo (nome, descricao, desenvolvedora, distribuidora, lancamento, nota, preco, classIndicativa, plataformas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        comando.setString(1, j.getNome());
        comando.setString(2, j.getDescricao());
        comando.setString(3, j.getDesenvolvedora());
        comando.setString(4, j.getDistribuidora());
        comando.setObject(5, j.getLancamento());
        comando.setDouble(6, j.getNota());
        comando.setDouble(7, j.getPreco());
        comando.setString(8, j.getClassIndicativa());
        comando.setString(9, j.getPlataformas());
        comando.execute();
        con.close();
    }

    public void deletar(Jogo j) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("DELETE FROM Jogo WHERE id = ?");
        comando.setInt(1, j.getId());
        comando.execute();
        con.close();
    }

    public void atualizar(Jogo j) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement(
            "UPDATE Jogo SET nome = ?, descricao = ?, desenvolvedora = ?, distribuidora = ?, lancamento = ?, nota = ?, preco = ?, classIndicativa = ?, plataformas = ? WHERE id = ?");
        comando.setString(1, j.getNome());
        comando.setString(2, j.getDescricao());
        comando.setString(3, j.getDesenvolvedora());
        comando.setString(4, j.getDistribuidora());
        comando.setObject(5, j.getLancamento());
        comando.setDouble(6, j.getNota());
        comando.setDouble(7, j.getPreco());
        comando.setString(8, j.getClassIndicativa());
        comando.setString(9, j.getPlataformas());
        comando.setInt(10, j.getId());
        comando.execute();
        con.close();
    }

    public Jogo consultarById(Jogo j) throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("SELECT * FROM Jogo WHERE id = ?");
        comando.setInt(1, j.getId());
        ResultSet rs = comando.executeQuery();
        Jogo jogo = new Jogo();
        if (rs.next()) {
            jogo.setId(rs.getInt("id"));
            jogo.setNome(rs.getString("nome"));
            jogo.setDescricao(rs.getString("descricao"));
            jogo.setDesenvolvedora(rs.getString("desenvolvedora"));
            jogo.setDistribuidora(rs.getString("distribuidora"));
            jogo.setLancamento(rs.getObject("lancamento", LocalDate.class));
            jogo.setNota(rs.getDouble("nota"));
            jogo.setPreco(rs.getDouble("preco"));
            jogo.setClassIndicativa(rs.getString("classIndicativa"));
            jogo.setPlataformas(rs.getString("plataformas"));
        }
        con.close();
        return jogo;
    }

    public List<Jogo> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = Conexao.getConexao();
        PreparedStatement comando = con.prepareStatement("SELECT * FROM Jogo");
        ResultSet rs = comando.executeQuery();
        List<Jogo> jogos = new ArrayList<>();
        while (rs.next()) {
            Jogo jogo = new Jogo();
            jogo.setId(rs.getInt("id"));
            jogo.setNome(rs.getString("nome"));
            jogo.setDescricao(rs.getString("descricao"));
            jogo.setDesenvolvedora(rs.getString("desenvolvedora"));
            jogo.setDistribuidora(rs.getString("distribuidora"));
            jogo.setLancamento(rs.getObject("lancamento", LocalDate.class));
            jogo.setNota(rs.getDouble("nota"));
            jogo.setPreco(rs.getDouble("preco"));
            jogo.setClassIndicativa(rs.getString("classIndicativa"));
            jogo.setPlataformas(rs.getString("plataformas"));
            jogos.add(jogo);
        }
        con.close();
        return jogos;
    }
}