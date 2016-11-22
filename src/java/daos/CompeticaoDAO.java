/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.Atleta;
import entidades.Competicao;
import entidades.Despesa;
import entidades.Equipe;
import entidades.Modalidades;
import entidades.Percurso;
import entidades.TiposDespesas;
import interfaces.IDAO;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author User
 */
public class CompeticaoDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        String retorno = null;
        Competicao c = (Competicao) o;
        try {
            String data;
            if (c.getDia() == null) {
                data = null;
            } else {
                data = "'" + c.getDia() + "'";
            }

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO competicao  VALUES "
                    + "(DEFAULT,"
                    + "'" + c.getNome() + "',"
                    + data + ","
                    + "'" + c.getStatus() + "',"
                    + " " + c.getCidade() + ","
                    + "'" + c.getLocalidade() + "',"
                    + "'" + c.getColocacao() + "',"
                    + "'" + c.getPremiacao() + "',"
                    + "'" + c.getRelato() + "') RETURNING  idcompeticao";

            System.out.println("SQL: " + sql);
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            c.setId(rs.getInt(1));
            //if (resultado > 0) {
        } catch (Exception e) {
            System.out.println("Erro salvar Competicao = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public String atualizar(Object o) {
        String retorno = null;
        Competicao c = (Competicao) o;
        try {
            String data;
            if (c.getDia() == null) {
                data = null;
            } else {
                data = "'" + c.getDia() + "'";
            }

            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE competicao SET nome = '"
                    + c.getNome() + "', "
                    + "dia = " + data + ", "
                    + "status = '" + c.getStatus() + "', "
                    + "ref_cidade = " + c.getCidade() + ", "
                    + "localidade = '" + c.getLocalidade() + "', "
                    + "colocacao = '" + c.getColocacao() + "', "
                    + "premiacao = '" + c.getPremiacao() + "', "
                    + "relato = '" + c.getRelato() + "' "
                    + "WHERE idcompeticao = " + c.getId() + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
            //if (resultado > 0) {
        } catch (Exception e) {
            System.out.println("Erro atualizar Competicao = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public String excluir(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM percurso WHERE ref_competicao = " + id + ";"
                    + "\n"
                    + "DELETE FROM equipe WHERE ref_competicao = " + id + ";"
                    + "\n"
                    + "DELETE FROM despesas WHERE ref_competicao = " + id + ";"
                    + "\n"
                    + "DELETE FROM competicao WHERE idcompeticao = " + id + ";";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir Competicão = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> competicoes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM competicao ORDER BY dia";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            while (resultado.next()){
                Competicao c = new Competicao();
                c.setId(resultado.getInt("idcompeticao"));
                c.setNome(resultado.getString("nome"));
                c.setDia(resultado.getDate("dia"));
                c.setStatus(resultado.getString("status").charAt(0));
                c.setLocalidade(resultado.getString("localidade"));
                c.setColocacao(resultado.getString("colocacao"));
                c.setPremiacao(resultado.getString("premiacao"));
                c.setRelato(resultado.getString("relato"));
                c.setCidade(resultado.getInt("ref_cidade"));
                competicoes.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro consulta todas competições: " + e);
        }
        return competicoes;
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        ArrayList<Object> competicoes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM competicao WHERE nome ILIKE '%" + criterio + "%' ORDER BY dia";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            while (resultado.next()){
                Competicao c = new Competicao();
                c.setId(resultado.getInt("idcompeticao"));
                c.setNome(resultado.getString("nome"));
                c.setDia(resultado.getDate("dia"));
                c.setStatus(resultado.getString("status").charAt(0));
                c.setLocalidade(resultado.getString("localidade"));
                c.setColocacao(resultado.getString("colocacao"));
                c.setPremiacao(resultado.getString("premiacao"));
                c.setRelato(resultado.getString("relato"));
                
                competicoes.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro buscar modalidades: " + e);
        }
        return competicoes;
    }

    @Override
    public Object consultarId(int id) {
        Competicao c = new Competicao();
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM competicao WHERE idcompeticao = " + id + "";

            System.out.println("SQL: " + sql);

            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            c.setId(resultadoQ.getInt("idcompeticao"));
            c.setNome(resultadoQ.getString("nome"));
            c.setDia(resultadoQ.getDate("dia"));
            c.setStatus(resultadoQ.getString("status").charAt(0));
            c.setCidade(resultadoQ.getInt("ref_cidade"));
            c.setLocalidade(resultadoQ.getString("localidade"));
            c.setColocacao(resultadoQ.getString("colocacao"));
            c.setPremiacao(resultadoQ.getString("premiacao"));
            c.setRelato(resultadoQ.getString("relato"));
            return c;
        } catch (Exception e) {
            System.out.println("Erro consultar competicao = " + e);
            return null;
        }
    }

    public Date converteData(String data) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new Date(df.parse(data).getTime());
        } catch (Exception e) {
            try
            {
                df = new SimpleDateFormat( "yyyy-MM-dd");
                return new Date( df.parse ( data ).getTime() );
            }
            catch ( Exception ex )
            {
                return null;
            }
        }
    }

    public void popularTabelaCompeticao(JTable tabela, String criterio) {
        ResultSet resultadoQ;
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Código";
        cabecalho[1] = "Data";
        cabecalho[2] = "Título";
        cabecalho[3] = "Situação";
        cabecalho[4] = "Cidade";
        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM competicao WHERE nome ILIKE '%" + criterio + "%'");
            resultadoQ.next();
            dadosTabela = new Object[resultadoQ.getInt(1)][5];
        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }
        int lin = 0;
        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT co.*, ci.nome as cidade FROM competicao co, cidade ci "
                    + "WHERE co.ref_cidade = ci.idcidade AND co.nome ILIKE '%" + criterio + "%' ORDER BY dia");
            while (resultadoQ.next()) {
                if (resultadoQ.getDate("dia") != null){
                   SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                   String dia = df.format(resultadoQ.getDate("dia")); 
                   dadosTabela[lin][1] = dia;
                }
                
                String status = resultadoQ.getString("status");
                if (status.equals("p")) {
                    status = "Programada";
                } else if (status.equals("r")) {
                    status = "Realizada";
                } else {
                    status = "Suspensa";
                }
                
                dadosTabela[lin][0] = resultadoQ.getInt("idcompeticao");
                
                dadosTabela[lin][2] = resultadoQ.getString("nome");
                dadosTabela[lin][3] = status;
                dadosTabela[lin][4] = resultadoQ.getString("cidade");;
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela de competições...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);
        
        //alinhamento da conteúdo de uma coluna
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);
        tabela.getColumnModel().getColumn(1).setCellRenderer(direita);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(55);
                    break;
                case 1:
                    column.setPreferredWidth(70);
                    break;
                case 2:
                    column.setPreferredWidth(217);
                    break;
                case 3:
                    column.setPreferredWidth(80);
                    break;
                case 4:
                    column.setPreferredWidth(150);
                    break;
            }
        }
    }

    public String salvarPercurso(Percurso p) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO percurso  VALUES "
                    + "(" + p.getCompeticao() + ", "
                    + p.getModalidade() + ", "
                    + p.getKm() + ")";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar modalidade no percurso = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    public String excluirPercurso(int competicao, int modalidade) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM percurso WHERE ref_competicao = " + competicao + " AND ref_modalidades = " + modalidade + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir Percurso = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    public ArrayList<Percurso> consultarPercurso(Competicao c) {
        ArrayList<Percurso> percursos = new ArrayList();
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM percurso WHERE ref_competicao = " + c.getId() + "";
            System.out.println("SQL: " + sql);
            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                Percurso p = new Percurso(resultadoQ.getInt("ref_competicao"), resultadoQ.getInt("ref_modalidades"), resultadoQ.getDouble("km"));
                percursos.add(p);
            }
            return percursos;
        } catch (Exception e) {
            System.out.println("Erro ao consultar percurso = " + e);
            return null;
        }
    }

    public void popularComboModalidades(JComboBox combo) {
        ResultSet resultado;
        combo.removeAllItems();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM modalidades WHERE ativo = true ORDER BY nome";
            resultado = st.executeQuery(sql);
            combo.addItem("Selecione...");

            while (resultado.next()) {
                Modalidades m = new Modalidades();
                m.setIdModalidades(resultado.getInt("idmodalidades"));
                m.setNome(resultado.getString("nome"));
                m.setAtivo(resultado.getBoolean("ativo"));
                combo.addItem(m);
            }
        } catch (Exception e) {
            System.out.println("Erro ao popular Combo Modalidades = " + e.toString());
        }
    }

    public ArrayList<Atleta> consultarEquipe(Competicao c) {
        ArrayList<Atleta> atletas = new ArrayList<>();
        try {
            String sql = "SELECT a.* FROM atleta a, equipe e WHERE a.idatleta = e.ref_atleta AND e.ref_competicao = " + c.getId() + " ORDER BY nome";
            ResultSet resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

            while (resultadoQ.next()) {
                Atleta a = new Atleta();
                a.setIdatleta(resultadoQ.getInt("idatleta"));
                a.setNome(resultadoQ.getString("nome"));
                a.setDtnasc(resultadoQ.getDate("dtnasc"));
                a.setRg(resultadoQ.getString("rg"));
                a.setCpf(resultadoQ.getString("cpf"));
                a.setTipoSang(resultadoQ.getString("tiposang"));
                a.setTelefone(resultadoQ.getString("telefone"));
                a.setEmail(resultadoQ.getString("email"));
                a.setEndereco(resultadoQ.getString("endereco"));
                a.setCep(resultadoQ.getString("cep"));
                a.setParente(resultadoQ.getString("parente"));
                a.setTelefoneP(resultadoQ.getString("contatoParente"));
                a.setAlergias(resultadoQ.getString("alergias"));
                a.setCidade(resultadoQ.getInt("ref_cidade"));
                a.setObservacoes(resultadoQ.getString("observacoes"));
                a.setLogin(resultadoQ.getString("login"));
                a.setSenha(resultadoQ.getString("senha"));
                a.setAtivo(resultadoQ.getBoolean("ativo"));

                atletas.add(a);
            }
        } catch (Exception e) {
            System.out.println("Erro consulta atletas da equipe: " + e);
        }
        return atletas;
    }
    
    public String salvarEquipe(Equipe p) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO equipe  VALUES "
                    + "(" + p.getCompeticao() + ","
                    + p.getAtleta() + ")";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar atleta na equipe = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    public String excluirEquipe(int competicao, int atleta) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM equipe WHERE ref_competicao = " + competicao + " AND ref_atleta = " + atleta;
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir atleta da equipe = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    

    public String atualizaDistanciaTotal(int id) {
        String distanciaTotal = "0";
        try {
            ResultSet resultadoQ;
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT SUM(km) FROM percurso WHERE ref_competicao = " + id);
            resultadoQ.next();
            distanciaTotal = String.valueOf(resultadoQ.getDouble(1));
        } catch (Exception e) {
            System.out.println("Erro ao somar distâncias: " + e);
        }
        return distanciaTotal;
    }

    public String salvarDespesa(Despesa d) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO despesas  VALUES "
                    + "(" + d.getCompeticao() + ", "
                    + d.getDespesa() + ", "
                    + d.getValor() + ", '"
                    + d.getObservacao() + "')";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar despesa = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    public String excluirDespesa(int competicao, int despesa) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM despesas WHERE ref_competicao = " + competicao + " AND ref_tipo_despesas = " + despesa + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir despesa = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    public ArrayList<Despesa> consultarDespesa(Competicao c) {
        ArrayList<Despesa> despesas = new ArrayList();
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM despesas WHERE ref_competicao = " + c.getId() + "";
            System.out.println("SQL: " + sql);
            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                Despesa d = new Despesa();
                d.setCompeticao(c.getId());
                d.setDespesa(resultadoQ.getInt("ref_tipo_despesas"));
                d.setValor(resultadoQ.getDouble("valor"));
                d.setObservacao(resultadoQ.getString("observacao"));
                despesas.add(d);
            }
            return despesas;
        } catch (Exception e) {
            System.out.println("Erro ao consultar percurso = " + e);
            return null;
        }
    }

    public void popularComboDespesas(JComboBox combo) {
        ResultSet resultado;
        combo.removeAllItems();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM tipo_despesas WHERE ativo = true ORDER BY nome";
            resultado = st.executeQuery(sql);
            combo.addItem("Selecione...");

            while (resultado.next()) {
                TiposDespesas td = new TiposDespesas();
                td.setIdTiposDespesas(resultado.getInt("idtipo_despesas"));
                td.setNome(resultado.getString("nome"));
                td.setAtivo(resultado.getBoolean("ativo"));
                combo.addItem(td);
            }
        } catch (Exception e) {
            System.out.println("Erro ao popular Combo Tipos Despesas = " + e.toString());
        }
    }

    public void popularTabelaDespesas(JTable tabela, Competicao c) {
        ResultSet resultadoQ;
        // dados da tabela
        Object[][] dadosTabela = null;
        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Despesa";
        cabecalho[1] = "Valor";
        cabecalho[2] = "Observação";
        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM despesas WHERE ref_competicao = " + c.getId());
            resultadoQ.next();
            dadosTabela = new Object[resultadoQ.getInt(1)][3];
        } catch (Exception e) {
            System.out.println("Erro ao consultar Despesas: " + e);
        }
        int lin = 0;
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT nome, valor, observacao FROM despesas, tipo_despesas WHERE ref_tipo_despesas = idtipo_despesas AND ref_competicao = " + c.getId());
            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getString("nome"); //poderia colocar o número da coluna (1)
                dadosTabela[lin][1] = resultadoQ.getDouble("valor");
                dadosTabela[lin][2] = resultadoQ.getString("observacao");
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }
        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);
        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(140);
                    break;
                case 1:
                    column.setPreferredWidth(60);
                    break;
                case 2:
                    column.setPreferredWidth(212);
                    break;
            }
        }
    }

    public String atualizaDespesaTotal(int id) {
        String despesaTotal = "0";
        try {
            ResultSet resultadoQ;
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT SUM(valor) FROM despesas WHERE ref_competicao = " + id);
            resultadoQ.next();
            despesaTotal = String.valueOf(resultadoQ.getDouble(1));
        } catch (Exception e) {
            System.out.println("Erro ao somar despesas: " + e);
        }
        return despesaTotal;
    }
    
    public byte[] gerarRelatorioHistorico() {
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            File reportFile = new File("C:/Users/Fernanda Finkler/Documents/NetBeansProjects/CompetitionsWEB/src/java/relatorios/HistoricoEquipe.jasper");
            Map parameters = new HashMap();
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("erro ao gerar relatorio: " + e);
        }
        return null;
    }
    
    public byte[] gerarRelatorioDadosInscricao(int idComp) {
        Competicao c = (Competicao) consultarId(idComp);
        ArrayList<Atleta> equipe = consultarEquipe(c);
        
        List<Integer> ids = new ArrayList();
        
        for (int i = 0; i < equipe.size(); i++) {
            ids.add( equipe.get(i).getIdatleta() );
        }
        try {
            Connection conn = new ConexaoBD().getInstance().getConnection();
            File reportFile = new File("C:/Users/Fernanda Finkler/Documents/NetBeansProjects/CompetitionsWEB/src/java/relatorios/DadosEquipe.jasper");
            Map parameters = new HashMap();
            
            parameters.put("idz",ids );
            
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);

            return bytes;
        } catch (Exception e) {
            System.out.println("Erro ao gerar relatório: " + e);
        }
        return null;
    }
}
