/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.TiposDespesas;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Fernanda Finkler
 */
public class TiposDespesasDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        String retorno = null;
        TiposDespesas td = (TiposDespesas) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO tipo_despesas VALUES (DEFAULT, '" + td.getNome() + "', " + td.isAtivo() + ")";

            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
            //if (resultado > 0) {
        } catch (Exception e) {
            System.out.println("Erro ao salvar Tipo de Despesa = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public String atualizar(Object o) {
        String retorno = null;
        TiposDespesas td = (TiposDespesas) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE tipo_despesas SET nome = '" + td.getNome() + "', ativo = " + td.isAtivo() + " WHERE idtipo_despesas = " + td.getIdTiposDespesas() + "";
            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            //if (resultado > 0) {                

        } catch (Exception e) {
            System.out.println("Erro ao salvar Tipo de Despesa = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public String excluir(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM tipo_despesas WHERE idtipo_despesas = " + id + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir Tipo de Despesa = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> tipos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tipo_despesas ORDER BY idtipo_despesas";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            while (resultado.next()){
                TiposDespesas td = new TiposDespesas();
                td.setIdTiposDespesas(resultado.getInt("idtipo_despesas"));
                td.setNome(resultado.getString("nome"));
                td.setAtivo(resultado.getBoolean("ativo"));
                tipos.add(td);
            }
        } catch (Exception e) {
            System.out.println("Erro consulta todos Tipos Despesas: " + e);
        }
        return tipos;
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        TiposDespesas td = new TiposDespesas();
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM tipo_despesas WHERE idtipo_despesas = " + id + "";

            System.out.println("SQL: " + sql);

            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            td.setIdTiposDespesas(resultadoQ.getInt("idtipo_despesas"));
            td.setNome(resultadoQ.getString("nome"));
            td.setAtivo(resultadoQ.getBoolean("ativo"));
            return td;
        } catch (Exception e) {
            System.out.println("Erro consultar tipo de despesa = " + e);
            return null;
        }
    }
    
    public void popularTabela(JTable tabela, String criterio) {
        ResultSet resultadoQ;

        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Código";
        cabecalho[1] = "Descrição";
        cabecalho[2] = "Ativo";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM tipo_despesas WHERE NOME ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][3]; //diz quantas linhas e colunas serão criadas: getInt(1) pega o resultado da primeira coluna do Select; e "2" pq serão duas colunas (Código,Descrição).

        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM tipo_despesas WHERE NOME ILIKE '%" + criterio + "%' ORDER BY nome");

            while (resultadoQ.next()) {
                String ativo = resultadoQ.getString("ativo");
                if (ativo.equals("f")){
                    ativo = "Não";
                } else if (ativo.equals("t")){
                    ativo = "Sim";
                }

                dadosTabela[lin][0] = resultadoQ.getInt("idtipo_despesas"); //poderia colocar o número da coluna (1)
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = ativo;
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
        
        //alinhamento da conteúdo de uma coluna
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        tabela.getColumnModel().getColumn(0).setCellRenderer(direita);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(60);
                    break;
                case 1:
                    column.setPreferredWidth(309);
                    break;
                case 2:
                    column.setPreferredWidth(50);
                    break;
            }
        }
    }
    
    public void popularComboTiposDespesas(JComboBox combo) {
        ResultSet resultado;
        combo.removeAllItems();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM tipo_despesas ORDER BY nome";
            resultado = st.executeQuery(sql);
            combo.addItem("Selecione...");

            while (resultado.next()) {
                TiposDespesas ts = new TiposDespesas();
                ts.setIdTiposDespesas(resultado.getInt("idtipo_despesas"));
                ts.setNome(resultado.getString("nome"));
                combo.addItem(ts);
            }
        } catch (Exception e) {
            System.out.println("Erro ao popular Combo Despesas = " + e.toString());
        }
    }
    
    public String inativar(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE tipo_despesas SET ativo = false WHERE idtipo_despesas = " + id + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao inativar registro: " + e);
            retorno = e.toString();
        }
        return retorno;
    }
    
    public boolean possuiVinculos (int id){
        int qtde;
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM despesas WHERE ref_tipo_despesas = " + id + "";
            System.out.println("SQL: " + sql);
            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            qtde = resultadoQ.getInt(1);
            if (qtde > 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro aon consultar: " + e);
        }
        return true;
    }
    
    public boolean verificaNome(String nome, int id) {
        ResultSet resultadoQ;
        int qtde = 0;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM tipo_despesas WHERE nome = '" + nome + "'";
            System.out.println("SQL: " + sql);
            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            qtde = resultadoQ.getInt(1);
            if (qtde == 0){
                return true;
            } else if (qtde == 1){
                String sql2 = "SELECT idtipo_despesas FROM tipo_despesas WHERE nome = '" + nome + "'";
                System.out.println("SQL: " + sql2);
                resultadoQ = st.executeQuery(sql2);
                resultadoQ.next();
                int idmodalidade = resultadoQ.getInt("idtipo_despesas");
                if (idmodalidade == id){
                    return true;
                }
            }            
        } catch (Exception e) {
            System.out.println("Erro ao consultar se tipo de despesa já existe: " + e);
        }
        return false;
    }

    public String obterTiposDespesasCombo() {
        String comboTiposDespesas = "";

        try {
            String sql = "select * from tipo_despesas where ativo = true";

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            comboTiposDespesas = "<label for=\"comboTiposDespesas\">Tipos Despesas:</label>&nbsp;";
            comboTiposDespesas += "<select class=\"form-control\" name=\"comboTiposDespesas\" id=\"comboTiposDespesas\">\n";
            comboTiposDespesas += "<option value=\"0\" id=\"0\"> Selecione </option>\n"; 

            while (resultado.next()) {
                int id = resultado.getInt("idtipo_despesas");
                String descricao = resultado.getString("nome");
                comboTiposDespesas += "<option value=\"" + id + "\">" + descricao + " </option>\n";
            }

            comboTiposDespesas += "</select>";
        } catch (Exception e) {
            System.out.println("erro: " + e);
            return null;
        }

        return comboTiposDespesas;
    }
    
}
