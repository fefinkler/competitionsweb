/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import apoio.ConexaoBD;
import daos.AtletaDAO;
import daos.CidadeDAO;
import daos.CompeticaoDAO;
import daos.EstadoDAO;
import daos.ModalidadesDAO;
import daos.PaisDAO;
import daos.TiposDespesasDAO;
import entidades.Atleta;
import entidades.Cidade;
import entidades.Competicao;
import entidades.Despesa;
import entidades.Equipe;
import entidades.Estado;
import entidades.Modalidades;
import entidades.Pais;
import entidades.Percurso;
import entidades.TiposDespesas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

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
        
        response.setCharacterEncoding( "UTF-8" );
        requisicao = request;
        resposta = response;
        
        if (request.getParameter("parametro").equals("editarAtleta")) {
            editarAtleta();
        } else if (request.getParameter("parametro").equals("excluirAtleta")) {
            excluirAtleta();
        } else if (request.getParameter("parametro").equals("inativarAtleta")) {
            inativarAtleta();
        }if (request.getParameter("parametro").equals("editarModalidade")) {
            editarModalidade();
        } else if (request.getParameter("parametro").equals("excluirModalidade")) {
            excluirModalidade();
        } else if (request.getParameter("parametro").equals("inativarModalidade")) {
            inativarModalidade();
        } else if (request.getParameter("parametro").equals("verificarNome")){
            verificarNome();
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
            buscarEstados();
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
        } else if (request.getParameter("parametro").equals("editarCompeticao")) {
            editarCompeticao();
        } else if (request.getParameter("parametro").equals("excluirCompeticaoo")) {
            excluirCompeticao();
        } else if (request.getParameter("parametro").equals("logout")) {
            logout();
        } else if (request.getParameter("parametro").equals("getCidade")) {
            int id_estado = Integer.parseInt(request.getParameter("idEstado"));
            String cidades = new CidadeDAO().obterCidadeCombo(id_estado);
            response.getWriter().println(cidades);
        } else if (request.getParameter("parametro").equals("getEstado")) {
            int id_pais = Integer.parseInt(request.getParameter("idPais"));
            String estados = new EstadoDAO().obterEstadoCombo(id_pais);
            response.getWriter().println(estados);
        } else if (request.getParameter("parametro").equals("getPais")) {
            String paises = new PaisDAO().obterPaisCombo();
            response.getWriter().println(paises);
        } else if (request.getParameter("parametro").equals("getModalidades")) {
            String modalidades = new ModalidadesDAO().obterModalidadesCombo();
            response.getWriter().println(modalidades);
        } else if (request.getParameter("parametro").equals("getDespesas")) {
            String tiposdespesas = new TiposDespesasDAO().obterTiposDespesasCombo();
            response.getWriter().println(tiposdespesas);
        }  else if (request.getParameter("parametro").equals("relatorioHistorico")) {
            gerarRelatorioHistorico();
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
        response.setCharacterEncoding( "UTF-8" );        
        requisicao = request;
        resposta = response;

        if (request.getParameter("parametro").equals("cadastraModalidade")) {
            cadastrarModalidade();

        } else if (request.getParameter("parametro").equals("cadastraAtleta")) {
            cadastrarAtleta();

        } else if (request.getParameter("parametro").equals("cadastraTipoDespesa")) {
            cadastrarTiposDespesas();

        } else if (request.getParameter("parametro").equals("cadastraPais")) {
            cadastrarPais();

        } else if (request.getParameter("parametro").equals("cadastraEstado")) {
            cadastrarEstado();

        } else if (request.getParameter("parametro").equals("cadastraCidade")) {
            cadastrarCidade();

        } else if (request.getParameter("parametro").equals("cadastraCompeticao")) {
            cadastrarCompeticao();

        } else if (requisicao.getParameter("parametro").equals("login")) {
            validarLogin();
            
        } else if (requisicao.getParameter("parametro").equals("addPercurso")) {
            adicionaPercurso();
            
        } else if (request.getParameter("parametro").equals("excluirPercurso")) {
            excluirPercurso();
            
        } else if (requisicao.getParameter("parametro").equals("addDespesa")) {
            adicionaDespesa();
            
        } else if (request.getParameter("parametro").equals("excluirDespesa")) {
            excluirDespesa();
            
        } else if (requisicao.getParameter("parametro").equals("addAtleta")) {
            adicionarNaEquipe();
            
        } else if (request.getParameter("parametro").equals("excluirAtleta")) {
            excluirDaEquipe();
            
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
           
           encaminharPagina("menuCompeticoes.jsp?active=l");
        } else {
            encaminharPagina("erro.jsp");
        }
    }

    private void logout() {
        HttpSession sessao = requisicao.getSession();
        sessao.invalidate();
        encaminharPagina("index.jsp");
    }
    
    private void cadastrarAtleta() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Atleta a = new Atleta();
        a.setIdatleta(id);
        a.setNome(requisicao.getParameter("nome"));
        a.setAtivo(requisicao.getParameter("status") != null);
        a.setDtnasc((Date) new CompeticaoDAO().converteData(requisicao.getParameter("datanasc")));
        a.setCpf(requisicao.getParameter("cpf"));
        a.setRg(requisicao.getParameter("rg"));
        a.setTipoSang(requisicao.getParameter("tiposang"));
        a.setAlergias(requisicao.getParameter("alergias"));
        a.setTelefone(requisicao.getParameter("telefone"));
        a.setEmail(requisicao.getParameter("email"));
        a.setEndereco(requisicao.getParameter("endereco"));
        a.setCep(requisicao.getParameter("cep"));
        a.setCidade(Integer.parseInt(requisicao.getParameter("cidade")));
        a.setParente(requisicao.getParameter("parente"));
        a.setTelefoneP(requisicao.getParameter("telefonep"));
        a.setObservacoes(requisicao.getParameter("obs"));
        a.setLogin(requisicao.getParameter("login"));
        a.setSenha(requisicao.getParameter("senha"));

        String retorno;
        if (a.getIdatleta()== 0) {
            retorno = new AtletaDAO().salvar(a);
        } else {
            retorno = new AtletaDAO().atualizar(a);
        }
        
        requisicao.setAttribute("paginaretorno", "cadastroAtletas.jsp?active=l");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void editarAtleta() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Atleta a = (Atleta) new AtletaDAO().consultarId(id);

        if (a != null) {
            requisicao.setAttribute("atleta", a);
            encaminharPagina("cadastroAtletas.jsp?active=c");
        }
    }

    private void excluirAtleta() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new AtletaDAO().excluir(id);
        requisicao.setAttribute("paginaretorno", "cadastroAtletas.jsp?active=l");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void inativarAtleta() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new AtletaDAO().inativar(id);
        requisicao.setAttribute("paginaretorno", "cadastroAtletas.jsp?active=l");
        if (retorno == null) {
            encaminharPagina("cadastroAtletas.jsp?active=l");
        } else {
            encaminharPagina("erro.jsp");
        }
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
        
        requisicao.setAttribute("paginaretorno", "cadastroModalidades.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
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
    
    private void verificarNome() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String nome = requisicao.getParameter("nome");
        boolean ok =  new ModalidadesDAO().verificaNome(nome, id);
        
        try {
            PrintWriter out = resposta.getWriter();
            out.write(String.valueOf(ok));
            out.close();
        } catch (Exception e) {
            System.out.println("erro: " + e);
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
        int id = Integer.parseInt(requisicao.getParameter("idpais"));
        ArrayList<Estado> estados = new EstadoDAO().consultarTodosAtivos(id);
        
        try {
            PrintWriter out = resposta.getWriter();
            out.append( estados.toString() );
            out.close();
        } catch (Exception e) {
            System.out.println("erro: " + e);
        }
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
    
    private void cadastrarCompeticao() {
        int id = Integer.parseInt(requisicao.getParameter("idComp"));
        String nome = requisicao.getParameter("nome");
        Date dia = (Date) new CompeticaoDAO().converteData(requisicao.getParameter("dia"));
        char status = requisicao.getParameter("status").charAt(0);
        String localidade = requisicao.getParameter("local");
        String colocacao = requisicao.getParameter("colocacao");
        String premiacao = requisicao.getParameter("premiacao");
        String relato = requisicao.getParameter("relato");
        int cidade = Integer.parseInt(requisicao.getParameter("cidade"));
        
        Competicao c = new Competicao();
        c.setId(id);
        c.setNome(nome);
        c.setDia(dia);
        c.setStatus(status);
        c.setLocalidade(localidade);
        c.setColocacao(colocacao);
        c.setPremiacao(premiacao);
        c.setRelato(relato);
        c.setCidade(cidade);
        
        String retorno;
        if (c.getId()== 0) {
            retorno = new CompeticaoDAO().salvar(c);
            
        } else {
            retorno = new CompeticaoDAO().atualizar(c);
        }
        requisicao.setAttribute("competicao", null);
        requisicao.setAttribute("paginaretorno", "menuCompeticoes.jsp");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void editarCompeticao() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        Competicao c = (Competicao) new CompeticaoDAO().consultarId(id);

        if (c != null) {
            requisicao.setAttribute("competicao", c);
            encaminharPagina("menuCompeticoes.jsp?active=c");
        }
    }
    
    private void excluirCompeticao() {
        int id = Integer.parseInt(requisicao.getParameter("id"));
        String retorno = new CompeticaoDAO().excluir(id);
        requisicao.setAttribute("paginaretorno", "menuCompeticoes.jsp?active=l");
        if (retorno == null) {
            encaminharPagina("sucesso.jsp");
        } else {
            encaminharPagina("erro.jsp");
        }
    }
    
    private void excluirPercurso() {
        int idComp = Integer.parseInt(requisicao.getParameter("idComp"));
        int idModal = Integer.parseInt(requisicao.getParameter("idModal"));
        String retorno = new CompeticaoDAO().excluirPercurso(idComp, idModal);
    }
    
    private void adicionaPercurso() throws IOException{
        int idComp = Integer.parseInt(requisicao.getParameter("idComp"));
        Modalidades m = (Modalidades) new ModalidadesDAO().consultarId(Integer.parseInt(requisicao.getParameter("idModal")));
        int km = Integer.parseInt(requisicao.getParameter("km"));
        Percurso p = new Percurso(idComp, m.getIdModalidades(), km);
        String retorno = new CompeticaoDAO().salvarPercurso(p);
        
        resposta.getWriter().println( "<tr>" +
                                        "<td>"+ m.getNome() + "</td>" +
                                        "<td>"+ km + "</td>" +
                                        "<td><a role=\"button\" onclick=\"removePercurso( this, " + idComp + ", " + m.getIdModalidades() + ")\">Remover</a></td>" +
                                      "</tr>" );
    }
    
    private void adicionaDespesa() throws IOException{
        int idComp = Integer.parseInt(requisicao.getParameter("idComp"));
        TiposDespesas td = (TiposDespesas) new TiposDespesasDAO().consultarId(Integer.parseInt(requisicao.getParameter("idDespesa")));
        int valor = Integer.parseInt(requisicao.getParameter("valor"));
        String observacao = requisicao.getParameter("obs");
        Despesa d = new Despesa();
        d.setCompeticao(idComp);
        d.setDespesa(td.getIdTiposDespesas());
        d.setValor(valor);
        d.setObservacao(observacao);
        String retorno = new CompeticaoDAO().salvarDespesa(d);
        
        resposta.getWriter().println( "<tr>" +
                                        "<td>"+ td.getNome()+ "</td>" +
                                        "<td>"+ valor + "</td>" +
                                        "<td>"+ observacao + "</td>" +
                                        "<td><a role=\"button\" onclick=\"removeDespesa( this, " + idComp + ", " + td.getIdTiposDespesas() + ")\">Remover</a></td>" +
                                      "</tr>" );
    }
    
    private void excluirDespesa() {
        int idComp = Integer.parseInt(requisicao.getParameter("idComp"));
        int idDespesa = Integer.parseInt(requisicao.getParameter("idDespesa"));
        String retorno = new CompeticaoDAO().excluirDespesa(idComp, idDespesa);
    }
    
    private void adicionarNaEquipe() throws IOException{
        int idComp = Integer.parseInt(requisicao.getParameter("idComp"));
        Atleta a = (Atleta) new AtletaDAO().consultarId(Integer.parseInt(requisicao.getParameter("idAtleta")));
        Cidade c = (Cidade) new CidadeDAO().consultarId(a.getCidade());
        Equipe e = new Equipe(idComp, a.getIdatleta());
        String retorno = new CompeticaoDAO().salvarEquipe(e);
        
        resposta.getWriter().println( "<tr>" +
                                        "<td>"+ a.getNome() + "</td>" +
                                        "<td>"+ c.getNome() + "</td>" +
                                        "<td><a role=\"button\" onclick=\"removaAtleta(this, " + idComp + ", " + a.getIdatleta() + " \">Remover</a></td>" +
                                        "</tr>" );
    }
    
    private void excluirDaEquipe() {
        int idComp = Integer.parseInt(requisicao.getParameter("idComp"));
        int idAtleta = Integer.parseInt(requisicao.getParameter("idAtleta"));
        String retorno = new CompeticaoDAO().excluirEquipe(idComp, idAtleta);
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
    
    private void gerarRelatorioHistorico() {                                             
        // chamada de relatório, COM parâmetros
        try {
            // Compila o relatorio
            JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("/java/relatorios/HistóricoEquipe.jrxml"));

            // Mapeia campos de parametros para o relatorio, mesmo que nao existam
            Map parametros = new HashMap();

            // Executa relatoio
            JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, ConexaoBD.getInstance().getConnection());

            // Exibe resultado em video
            JasperViewer.viewReport(impressao, false);
            
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e);
        } 
    }
}
