package controller;

import DAO.JogoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Jogo;

@WebServlet(name = "ControleJogo", urlPatterns = {"/ControleJogo"})
public class ControleJogo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String op = request.getParameter("op");
        JogoDAO jdao = new JogoDAO();
        Jogo j = new Jogo();
        String mensagem = "";

        try {
            if (op.equals("CADASTRAR")) {
                String nome = request.getParameter("nome");
                String precoStr = request.getParameter("preco");
                String classIndicativa = request.getParameter("classIndicativa");
                
                if (nome == null || nome.trim().isEmpty()) {
                    mensagem = "Erro: Nome é obrigatório.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    return;
                }
                if (precoStr == null || precoStr.trim().isEmpty()) {
                    mensagem = "Erro: Preço é obrigatório.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    return;
                }
                if (classIndicativa == null || classIndicativa.trim().isEmpty()) {
                    mensagem = "Erro: Classificação indicativa é obrigatória.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    return;
                }

                j.setNome(nome);
                j.setDescricao(request.getParameter("descricao"));
                j.setDesenvolvedora(request.getParameter("desenvolvedora"));
                j.setDistribuidora(request.getParameter("distribuidora"));
                String lancamentoStr = request.getParameter("lancamento");
                if (lancamentoStr != null && !lancamentoStr.isEmpty()) {
                    j.setLancamento(LocalDate.parse(lancamentoStr, DateTimeFormatter.ISO_LOCAL_DATE));
                }
                j.setNota(Double.parseDouble(request.getParameter("nota") != null ? request.getParameter("nota") : "0"));
                j.setPreco(Double.parseDouble(precoStr));
                j.setClassIndicativa(classIndicativa);
                j.setPlataformas(request.getParameter("plataformas"));
                jdao.cadastrar(j);
                mensagem = "Jogo cadastrado com sucesso.";
                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
            } else if (op.equals("DELETAR")) {
                String idStr = request.getParameter("id");
                if (idStr == null || idStr.trim().isEmpty()) {
                    mensagem = "Erro: ID não informado para deletar.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    return;
                }
                j.setId(Integer.parseInt(idStr));
                Jogo jogoExistente = jdao.consultarById(j);
                if (jogoExistente.getId() == 0) {
                    mensagem = "Erro: Jogo com ID " + idStr + " não encontrado.";
                } else {
                    jdao.deletar(j);
                    mensagem = "Jogo deletado com sucesso.";
                }
                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
            } else if (op.equals("ATUALIZAR")) {
                String idStr = request.getParameter("id");
                String nome = request.getParameter("nome");
                String precoStr = request.getParameter("preco");
                String classIndicativa = request.getParameter("classIndicativa");

                if (idStr == null || idStr.trim().isEmpty()) {
                    mensagem = "Erro: ID não informado para atualizar.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    return;
                }
                if (nome == null || nome.trim().isEmpty()) {
                    mensagem = "Erro: Nome é obrigatório.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    return;
                }
                if (precoStr == null || precoStr.trim().isEmpty()) {
                    mensagem = "Erro: Preço é obrigatório.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    return;
                }
                if (classIndicativa == null || classIndicativa.trim().isEmpty()) {
                    mensagem = "Erro: Classificação indicativa é obrigatória.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    return;
                }

                j.setId(Integer.parseInt(idStr));
                Jogo jogoExistente = jdao.consultarById(j);
                if (jogoExistente.getId() == 0) {
                    mensagem = "Erro: Jogo com ID " + idStr + " não encontrado.";
                    request.setAttribute("msg", mensagem);
                    request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
                    return;
                }
                j.setNome(nome);
                j.setDescricao(request.getParameter("descricao"));
                j.setDesenvolvedora(request.getParameter("desenvolvedora"));
                j.setDistribuidora(request.getParameter("distribuidora"));
                String lancamentoStr = request.getParameter("lancamento");
                if (lancamentoStr != null && !lancamentoStr.isEmpty()) {
                    j.setLancamento(LocalDate.parse(lancamentoStr, DateTimeFormatter.ISO_LOCAL_DATE));
                }
                j.setNota(Double.parseDouble(request.getParameter("nota") != null ? request.getParameter("nota") : "0"));
                j.setPreco(Double.parseDouble(precoStr));
                j.setClassIndicativa(classIndicativa);
                j.setPlataformas(request.getParameter("plataformas"));
                jdao.atualizar(j);
                mensagem = "Jogo atualizado com sucesso.";
                request.setAttribute("msg", mensagem);
                request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
            } else if (op.equals("LISTAR")) {
                List<Jogo> jogos = jdao.consultarTodos();
                request.setAttribute("jogos", jogos);
                request.getRequestDispatcher("listar.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            mensagem = "Erro: Formato inválido para ID, nota ou preço.";
            request.setAttribute("msg", mensagem);
            request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            request.setAttribute("msg", mensagem);
            request.getRequestDispatcher("resultadocadastrar.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gerenciar jogos";
    }
}