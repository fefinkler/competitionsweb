/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.Pais;
import interfaces.IDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Fernanda Finkler
 */
public class PaisDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Pais p = (Pais) o;
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO pais VALUES ( DEFAULT, '" + p.getSigla() + "', '" + p.getNome() + "', " + p.isAtivo() + ") ";
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
        Pais p = (Pais) o;
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE pais SET sigla = '" + p.getSigla() + "', nome = '" + p.getNome() + "', ativo = " + p.isAtivo() + " WHERE idpais = " + p.getIdpais() + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar registro: " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    public String inativar(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE pais SET ativo = false WHERE idpais = " + id + "";
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
            String sql = "DELETE FROM pais WHERE idpais = " + id + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao Excluir País: " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public ArrayList<Object> consultarTodos() {
        ArrayList<Object> paises = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pais ORDER BY nome";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            while (resultado.next()){
                Pais p = new Pais();
                p.setIdpais(resultado.getInt("idpais"));
                p.setNome(resultado.getString("nome"));
                p.setSigla(resultado.getString("sigla"));
                p.setAtivo(resultado.getBoolean("ativo"));
                paises.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro consulta todos países: " + e);
        }
        return paises;
    }

    public ArrayList<Object> consultarTodosAtivos() {
        ArrayList<Object> paises = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pais WHERE ativo = 'true' ORDER BY nome";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            while (resultado.next()){
                Pais p = new Pais();
                p.setIdpais(resultado.getInt("idpais"));
                p.setNome(resultado.getString("nome"));
                p.setSigla(resultado.getString("sigla"));
                p.setAtivo(resultado.getBoolean("ativo"));
                paises.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro consulta todos Países Ativos: " + e);
        }
        return paises;
    }
    
    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        Pais p = new Pais();
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM pais WHERE idpais = " + id + "";
            System.out.println("SQL: " + sql);
            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            p.setIdpais(resultadoQ.getInt("idpais"));
            p.setSigla(resultadoQ.getString("sigla"));
            p.setNome(resultadoQ.getString("nome"));
            p.setAtivo(resultadoQ.getBoolean("ativo"));
            return p;
        } catch (Exception e) {
            System.out.println("Erro aon consultar: " + e);
            return null;
        }
    }

    public void popularTabela(JTable tabela, String criterio) {
        ResultSet resultadoQ;

        Object[][] dadosTabela = null;
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Código";
        cabecalho[1] = "Sigla";
        cabecalho[2] = "País";
        cabecalho[3] = "Ativo";

        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery("SELECT count(*) FROM pais  WHERE nome ilike '%" + criterio + "%'");
            resultadoQ.next();
            dadosTabela = new Object[resultadoQ.getInt(1)][4];
        } catch (Exception e) {
            System.out.println("Erro ao popular tabela: " + e);
        }

        int linha = 0;
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM pais WHERE nome ilike '%" + criterio + "%' ORDER BY sigla");
            while (resultadoQ.next()) {
                String ativo = resultadoQ.getString("ativo");
                if (ativo.equals("f")) {
                    ativo = "Não";
                } else if (ativo.equals("t")) {
                    ativo = "Sim";
                }
                dadosTabela[linha][0] = resultadoQ.getInt("idpais");
                dadosTabela[linha][1] = resultadoQ.getString("sigla");
                dadosTabela[linha][2] = resultadoQ.getString("nome");
                dadosTabela[linha][3] = ativo;
                linha++;
            }
        } catch (Exception e) {
            System.out.println("Problemas para popular tabela: " + e);
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
                    column.setPreferredWidth(380);
                    break;
                case 3:
                    column.setPreferredWidth(50);
                    break;
            }
        }
    }

    public boolean possuiVinculos(int id) {
        int qtde;
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM estado WHERE ref_pais = " + id + "";
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
            System.out.println("Erro aon consultar: " + e);
        }
        return true;
    }

}
