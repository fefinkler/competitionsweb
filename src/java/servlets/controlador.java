/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.AtletaDAO;
import daos.CidadeDAO;
import daos.EstadoDAO;
import daos.ModalidadesDAO;
import daos.PaisDAO;
import daos.TiposDespesasDAO;
import entidades.Atleta;
import entidades.Cidade;
import entidades.Estado;
import entidades.Modalidades;
import entidades.Pais;
import entidades.TiposDespesas;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fernanda Finkler
 */
public class controlador extends HttpServlet {

    HttpServletRequest requisicao;
    HttpServletResponse resposta;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //    processRequest(request, response);
        System.out.println("GGEEEEETTTTTTTTTTTT");
//        String parametro = request.getParameter("bola");
//        System.out.println("Valor de bola: " + parametro);
        requisicao = request;
        resposta = response;

        if (request.getParameter("parametro").equals("editarModalidade")) {
            editarModalidade();
        } else if (request.getParameter("parametro").equals("excluirModalidade")) {
            excluirModalidade();
        } else if (request.getParameter("parametro").equals("inativarModalidade")) {
            inativarModalidade();
        } else if (request.getParameter("parametro").equals("editarTipoDespesa")) {
            editarTiposDespesas();
        } else if (request.getParameter("parametro").equals("excluirTipoDespesa")) {
            excluirTiposDespesas();
        } else if (request.getParameter("parametro").equals("inativarTipoDespesa")) {
            inativarTiposDespesas();
        } else if (request.getParameter("parametro").equals("editarPais")) {
            editarPais();
        } else if (request.getParameter("parametro").equals("excluirPais")) {
            excluirPais();
        } else if (request.getParameter("parametro").equals("inativarPais")) {
            inativarPais();
        } else if (request.getParameter("parametro").equals("editarEstado")) {
            editarEstado();
        } else if (request.getParameter("parametro").equals("buscarEstados")) {
            buscarEstado();
        } else if (request.getParameter("parametro").equals("excluirEstado")) {
            excluirEstado();
        } else if (request.getParameter("parametro").equals("inativarEstado")) {
            inativarEstado();
        } else if (request.getParameter("parametro").equals("editarCidade")) {
            editarCidade();
        } else if (request.getParameter("parametro").equals("excluirCidade")) {
            excluirCidade();
        } else if (request.getParameter("parametro").equals("inativarCidade")) {
            inativarCidade();
        } else if (request.getParameter("parametro").equals("logout")) {
            logout();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        requisicao = request;
        resposta = response;

        if (request.getParameter("parametro").equals("cadastraModalidade")) {
            cadastrarModalidade();

        } else if (request.getParameter("parametro").equals("cadastraTipoDespesa")) {
            cadastrarTiposDespesas();

        } else if (request.getParameter("parametro").equals("cadastraPais")) {
            cadastrarPais();

        } else if (request.getParameter("parametro").equals("cadastraEstado")) {
            cadastrarEstado();

        } else if (request.getParameter("parametro").equals("cadastraCidade")) {
            cadastrarCidade();

        } else if (requisicao.getParameter("parametro").equals("login")) {
            validarLogin();
            
        }

    }

    private void validarLogin() {
        String login = requisicao.getParameter("login");
        String senha = requisicao.getParameter("senha");

        if (new AtletaDAO().podeAcessar(login, senha)) {
            // usuario validado: cria coloca seu nome na sessao
            HttpSession sessao = requisicao.getSession();
            // setando um atributo da sessao
            sessao.setAttribute("usuarioLogado", new Atleta());
            encaminharPagina("menu.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }

    private void logout() {
        HttpSession sessao = requisicao.getSession();
        sessao.invalidate();
        encaminharPagina("index.jsp");
    }

    private void cadastrarModalidade() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        boolean ativo = requisicao.getParameter("ativo") != null;

        Modalidades m = new Modalidades();
        m.setIdModalidades(id);
        m.setNome(nome);
        m.setAtivo(ativo);

        String retorno;
        if (m.getIdModalidades() == 0) {
            retorno = new ModalidadesDAO().salvar(m);
        } else {
            retorno = new ModalidadesDAO().atualizar(m);
        }
        
        try {
            PrintWriter out = resposta.getWriter();
            if (retorno == null) {
//            requisicao.setAttribute("paginaRetorno", "cadLugar.jsp");
//            encaminharPagina("sucesso.jsp");
                out.println("ok");
            } else {
                //encaminharPagina("erro.jsp");
                out.println("erro");
            }
        } catch (Exception e) {
            System.out.println("erro: " + e);
        }
        
//        
//        requisicao.setAttribute("paginaretorno", "cadastroModalidades.jsp");
//        if (retorno == null) {
//            encaminharPagina("sucesso.jsp");
//        } else {
//            encaminharPagina("erro.jsp");
//        }
    }

    private void editarModalidade() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Modalidades m = (Modalidades) new ModalidadesDAO().consultarId(id);

        if (m != null) {
            requisicao.setAttribute("modalidade", m);
            encaminharPagina("cadastroModalidades.jsp");
        }
    }

    private void excluirModalidade() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new ModalidadesDAO().excluir(id);
        requisicao.setAttribute("paginaretorno", "cadastroModalidades.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void inativarModalidade() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new ModalidadesDAO().inativar(id);
        requisicao.setAttribute("paginaretorno", "cadastroModalidades.jsp");
        if (retorno == null) {
            encaminharPagina("cadastroModalidades.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void cadastrarTiposDespesas() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        boolean ativo = requisicao.getParameter("ativo") != null;

        TiposDespesas td = new TiposDespesas();
        td.setIdTiposDespesas(id);
        td.setNome(nome);
        td.setAtivo(ativo);

        String retorno;
        if (td.getIdTiposDespesas()== 0) {
            retorno = new TiposDespesasDAO().salvar(td);
        } else {
            retorno = new TiposDespesasDAO().atualizar(td);
        }
        requisicao.setAttribute("paginaretorno", "cadastroTiposDespesas.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void editarTiposDespesas() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        TiposDespesas td = (TiposDespesas) new TiposDespesasDAO().consultarId(id);

        if (td != null) {
            requisicao.setAttribute("tipodespesa", td);
            encaminharPagina("cadastroTiposDespesas.jsp");
        }
    }

    private void excluirTiposDespesas() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new TiposDespesasDAO().excluir(id);
        requisicao.setAttribute("paginaretorno", "cadastroTiposDespesas.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void inativarTiposDespesas() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new TiposDespesasDAO().inativar(id);
        requisicao.setAttribute("paginaretorno", "cadastroTiposDespesas.jsp");
        if (retorno == null) {
            encaminharPagina("cadastroTiposDespesas.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void cadastrarPais() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        String sigla = requisicao.getParameter("siglaPais");
        boolean ativo = requisicao.getParameter("ativo") != null;

        Pais p = new Pais();
        p.setIdpais(id);
        p.setNome(nome);
        p.setSigla(sigla);
        p.setAtivo(ativo);

        String retorno;
        if (p.getIdpais()== 0) {
            retorno = new PaisDAO().salvar(p);
        } else {
            retorno = new PaisDAO().atualizar(p);
        }
        requisicao.setAttribute("paginaretorno", "cadastroPaises.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void editarPais() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Pais p = (Pais) new PaisDAO().consultarId(id);

        if (p != null) {
            requisicao.setAttribute("pais", p);
            encaminharPagina("cadastroPaises.jsp");
        }
    }

    private void excluirPais() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new PaisDAO().excluir(id);
        requisicao.setAttribute("paginaretorno", "cadastroPaises.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void inativarPais() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new PaisDAO().inativar(id);
        requisicao.setAttribute("paginaretorno", "cadastroPaises.jsp");
        if (retorno == null) {
            encaminharPagina("cadastroPaises.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }

    private void cadastrarEstado() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        String sigla = requisicao.getParameter("siglaEstado");
        int pais = Integer.parseInt(requisicao.getParameter("pais"));
        boolean ativo = requisicao.getParameter("ativo") != null;

        Estado e = new Estado();
        e.setIdestado(id);
        e.setNome(nome);
        e.setSigla(sigla);
        e.setPais(pais);
        e.setAtivo(ativo);

        String retorno;
        if (e.getIdestado()== 0) {
            retorno = new EstadoDAO().salvar(e);
        } else {
            retorno = new EstadoDAO().atualizar(e);
        }
        requisicao.setAttribute("paginaretorno", "cadastroEstados.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void editarEstado() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Estado e = (Estado) new EstadoDAO().consultarId(id);

        if (e != null) {
            requisicao.setAttribute("estado", e);
            encaminharPagina("cadastroEstados.jsp");
        }
    }
    
    private void buscarEstados(){
        
    }

    private void excluirEstado() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new EstadoDAO().excluir(id);
        requisicao.setAttribute("paginaretorno", "cadastroEstados.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void inativarEstado() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new EstadoDAO().inativar(id);
        requisicao.setAttribute("paginaretorno", "cadastroEstados.jsp");
        if (retorno == null) {
            encaminharPagina("cadastroEstados.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void cadastrarCidade() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        int estado = Integer.parseInt(requisicao.getParameter("estado"));
        boolean ativo = requisicao.getParameter("ativo") != null;

        Cidade c = new Cidade();
        c.setIdcidade(id);
        c.setNome(nome);
        c.setEstado(estado);
        c.setAtivo(ativo);

        String retorno;
        if (c.getIdcidade()== 0) {
            retorno = new CidadeDAO().salvar(c);
        } else {
            retorno = new CidadeDAO().atualizar(c);
        }
        requisicao.setAttribute("paginaretorno", "cadastroCidades.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void editarCidade() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Cidade c = (Cidade) new CidadeDAO().consultarId(id);

        if (c != null) {
            requisicao.setAttribute("cidade", c);
            encaminharPagina("cadastroCidades.jsp");
        }
    }
    
    private void excluirCidade() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new CidadeDAO().excluir(id);
        requisicao.setAttribute("paginaretorno", "cadastroCidades.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void inativarCidade() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new CidadeDAO().inativar(id);
        requisicao.setAttribute("paginaretorno", "cadastroCidades.jsp");
        if (retorno == null) {
            encaminharPagina("cadastroCidades.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void encaminharPagina(String pagina) {
        try {
            RequestDispatcher rd = requisicao.getRequestDispatcher(pagina);
            rd.forward(requisicao, resposta);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhas página.");
        }
    }
}
