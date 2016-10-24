/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.Estado;
import entidades.Pais;
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
public class EstadoDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Estado estado = (Estado) o;
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO estado VALUES (DEFAULT, '" + estado.getSigla() + "', '" + estado.getNome() + "', " + estado.getPais() + ", " + estado.isAtivo() + ")";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar registro: " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public String atualizar(Object o) {
        Estado es = (Estado) o;
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE estado SET sigla = '" + es.getSigla() + "', nome = '" + es.getNome() + "', ref_pais = " + es.getPais() + ", ativo = " + es.isAtivo() + " WHERE idestado = " + es.getIdestado() + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar registro: " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public String excluir(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM estado WHERE idestado = " + id + "";
            st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir estado: " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    public String inativar(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE estado SET ativo = false WHERE idestado = " + id + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar registro: " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> estados = new ArrayList<>();
        try {
            String sql = "SELECT * FROM estado ORDER BY nome";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            while (resultado.next()){
                Estado e = new Estado();
                e.setIdestado(resultado.getInt("idestado"));
                e.setNome(resultado.getString("nome"));
                e.setSigla(resultado.getString("sigla"));
                e.setPais(resultado.getInt("ref_pais"));
                e.setAtivo(resultado.getBoolean("ativo"));
                estados.add(e);
            }
        } catch (Exception e) {
            System.out.println("Erro consulta todos estados: " + e);
        }
        return estados;
    }
    
    public ArrayList<Estado> consultarTodosAtivos(int pais) {
        ArrayList<Estado> estados = new ArrayList<>();
        try {
            String sql = "SELECT * FROM estado WHERE ref_pais = " + pais + "AND ativo = 'true' ORDER BY nome";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            while (resultado.next()){
                Estado e = new Estado();
                e.setIdestado(resultado.getInt("idestado"));
                e.setNome(resultado.getString("nome"));
                e.setSigla(resultado.getString("sigla"));
                e.setPais(resultado.getInt("ref_pais"));
                e.setAtivo(resultado.getBoolean("ativo"));
                estados.add(e);
            }
        } catch (Exception e) {
            System.out.println("Erro consulta todos Estados Ativos: " + e);
        }
        return estados;
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        Estado es = new Estado();
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM estado WHERE idestado = " + id + "";
            System.out.println("SQL: " + sql);
            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            es.setIdestado(resultadoQ.getInt("idestado"));
            es.setSigla(resultadoQ.getString("sigla"));
            es.setNome(resultadoQ.getString("nome"));
            es.setPais(resultadoQ.getInt("ref_pais"));
            es.setAtivo(resultadoQ.getBoolean("ativo"));
            return es;
        } catch (Exception e) {
            System.out.println("Erro ao consultar Estado: " + e);
            return null;
        }
    }

    public void popularTabela(JTable tabela, String criterio) {
        ResultSet resultadoQ;
        Object[][] dadosTabela = null;
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Código";
        cabecalho[1] = "Sigla";
        cabecalho[2] = "Estado";
        cabecalho[3] = "País";
        cabecalho[4] = "Ativo";

        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM estado WHERE nome ilike '%" + criterio + "%'");
            resultadoQ.next();
            dadosTabela = new Object[resultadoQ.getInt(1)][5];
        } catch (Exception e) {
            System.out.println("Erro ao consultar estados: " + e);
        }

        int linha = 0;
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT estado.*, pais.nome as pais FROM estado, pais WHERE ref_pais = pais.idpais AND estado.NOME ILIKE '%" + criterio + "%' ORDER BY pais.nome, estado.nome");

            while (resultadoQ.next()) {
                String ativo = resultadoQ.getString("ativo");
                if (ativo.equals("f")) {
                    ativo = "Não";
                } else if (ativo.equals("t")) {
                    ativo = "Sim";
                }
                dadosTabela[linha][0] = resultadoQ.getInt("idestado"); //poderia colocar o número da coluna (1)
                dadosTabela[linha][1] = resultadoQ.getString("sigla");
                dadosTabela[linha][2] = resultadoQ.getString("nome");
                dadosTabela[linha][3] = resultadoQ.getString("pais");
                dadosTabela[linha][4] = ativo;
                linha++;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar estados: " + e);
        }

        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {

            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

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
                    column.setPreferredWidth(55);
                    break;
                case 2:
                    column.setPreferredWidth(200);
                    break;
                case 3:
                    column.setPreferredWidth(180);
                    break;
                case 4:
                    column.setPreferredWidth(50);
                    break;
            }
        }
    }

    public void popularCombo(JComboBox combo) {
        ResultSet resultado;
        combo.removeAllItems();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM pais WHERE ativo = true ORDER BY nome";
            resultado = st.executeQuery(sql);
            combo.addItem("Selecione...");

            while (resultado.next()) {
                Pais p = new Pais();
                p.setIdpais(resultado.getInt("idpais"));
                p.setNome(resultado.getString("nome"));
                p.setSigla(resultado.getString("sigla"));
                p.setAtivo(resultado.getBoolean("ativo"));
                combo.addItem(p);
            }
        } catch (Exception e) {
            System.out.println("Erro ao popular Combo Países = " + e.toString());
        }
    }

    public boolean possuiVinculos(int id) {
        int qtde;
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM cidade WHERE ref_estado = " + id + "";
            System.out.println("SQL: " + sql);
            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            qtde = resultadoQ.getInt(1);
            if (qtde > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }
        return true;
    }
    
    public String obterEstadoCombo(int idPais) {

        String comboEstado = "";

        try {
            String sql = "select * from estado where ativo = true AND ref_pais = " + idPais;

            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            comboEstado = "<label for=\"comboe\">Estado:</label>&nbsp;";
            comboEstado += "<select class=\"form-control\" name=\"estado\" id=\"estado\">\n";
            comboEstado += "<option value=\"0\" id=\"0\"> Selecione </option>\n";

            while (resultado.next()) {
                int id = resultado.getInt("idestado");
                String descricao = resultado.getString("nome");
                comboEstado += "<option value=\"" + id + "\">" + descricao + " </option>\n";
            }

            comboEstado += "</select>";
        } catch (Exception e) {
            System.out.println("erro: " + e);
            return null;
        }

        return comboEstado;
    }
}
