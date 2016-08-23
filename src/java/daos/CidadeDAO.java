/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.Cidade;
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
public class CidadeDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Cidade c = (Cidade) o;
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO cidade VALUES ( DEFAULT, '" + c.getNome() + "', " + c.getEstado() + ", " + c.isAtivo() + ")";
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
       Cidade c = (Cidade) o;
       String retorno = null;
       try { 
           Statement st = ConexaoBD.getInstance().getConnection().createStatement();
           String sql = "UPDATE cidade SET nome = '" + c.getNome() + "', ref_estado = " + c.getEstado() + ", ativo = " + c.isAtivo()+ " WHERE idcidade = " + c.getIdcidade() + "";
           System.out.println("SQL: " + sql);
           int resultado = st.executeUpdate(sql);
       } catch (Exception e) {
           System.out.println("Erro ao atualizar Cidade: " + e);
           retorno = e.toString();
       }
       return retorno;
    }

    @Override
    public String excluir(int id) {
        String retorno = null;
        try { 
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM cidade WHERE idcidade = " + id + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir cidade: " + e);
            retorno = e.toString();
        }
        return retorno;
    }
    
    public String inativar(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE cidade SET ativo = false WHERE idcidade = " + id + "";
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        Cidade c = new Cidade();
        ResultSet resultadoQ;
        try { 
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM cidade WHERE idcidade = " + id + "";
            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            c.setIdcidade(resultadoQ.getInt("idcidade"));
            c.setNome(resultadoQ.getString("nome"));
            c.setEstado(resultadoQ.getInt("ref_estado"));
            c.setAtivo(resultadoQ.getBoolean("ativo"));
            return c;
        } catch (Exception e) {
            System.out.println("Erro ao consultar cidade: " + e);
            return null;
        }
    }
    
    public void popularTabela (JTable tabela, String criterio){
        ResultSet resultadoQ;
        Object[][] dadosTabela = null;
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Estado";
        cabecalho[3] = "País";
        cabecalho[4] = "Ativo";
        
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM cidade WHERE nome ilike '%" + criterio + "%'");
            resultadoQ.next();
            dadosTabela = new Object[resultadoQ.getInt(1)][5];
        } catch (Exception e){
            System.out.println("Erro ao consultar cidades1: " + e);
        }
        
        int linha = 0;
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT cidade.*, estado.nome as estado, pais.nome as pais FROM cidade, estado, pais "
                    + "WHERE cidade.ref_estado = estado.idestado AND estado.ref_pais = pais.idpais AND cidade.nome ILIKE '%" + criterio + "%' ORDER BY pais.nome, estado.nome, cidade.nome");

            while (resultadoQ.next()) {
                String ativo = resultadoQ.getString("ativo");
                if (ativo.equals("f")){
                    ativo = "Não";
                } else if (ativo.equals("t")){
                    ativo = "Sim";
                }
                dadosTabela[linha][0] = resultadoQ.getInt("idcidade"); //poderia colocar o número da coluna (1)
                dadosTabela[linha][1] = resultadoQ.getString("nome");
                dadosTabela[linha][2] = resultadoQ.getString("estado");
                dadosTabela[linha][3] = resultadoQ.getString("pais");
                dadosTabela[linha][4] = ativo;
                linha++;
            }
        } catch (Exception e){
            System.out.println("Erro ao consultar cidades2: " + e);
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
                    column.setPreferredWidth(145);
                    break;
                case 2:
                    column.setPreferredWidth(145);
                    break;
                case 3:
                    column.setPreferredWidth(145);
                    break;
                case 4:
                    column.setPreferredWidth(50);
                    break;
            }
        }
    }
        
    public void popularComboPais(JComboBox combo) {
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
        
    public void popularComboEstados(JComboBox combo, int pais){
        ResultSet resultado;
        combo.removeAllItems();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM estado WHERE ref_pais = " + pais + " AND ativo = true ORDER BY nome";
            resultado = st.executeQuery(sql);
            combo.addItem("Selecione...");

            while (resultado.next()) {
                Estado e = new Estado();
                e.setIdestado(resultado.getInt("idestado"));
                e.setNome(resultado.getString("nome"));
                e.setSigla(resultado.getString("sigla"));
                e.setPais(resultado.getInt("ref_pais"));
                e.setAtivo(resultado.getBoolean("ativo"));
                combo.addItem(e);
            }
        } catch (Exception e) {
            System.out.println("Erro ao popular Combo Estados = " + e.toString());
        }
    }
    
    public void popularComboCidades (JComboBox combo, int estado){
        ResultSet resultado;
        combo.removeAllItems();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM cidade WHERE ref_estado = " + estado + " AND ativo = true ORDER BY nome";
            resultado = st.executeQuery(sql);
            combo.addItem("Selecione...");

            while (resultado.next()) {
                Cidade c = new Cidade();
                c.setIdcidade(resultado.getInt("idcidade"));
                c.setNome(resultado.getString("nome"));
                c.setEstado(resultado.getInt("ref_estado"));
                c.setAtivo(resultado.getBoolean("ativo"));
                combo.addItem(c);
            }
        } catch (Exception e) {
            System.out.println("Erro ao popular Combo Estados = " + e.toString());
        }
    }
    
    public boolean possuiVinculos (int id){
        int qtde;
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM atleta as a, competicao as c WHERE a.ref_cidade = " + id + " OR c.ref_cidade = " + id + "";
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
}