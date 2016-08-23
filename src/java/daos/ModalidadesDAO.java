/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.Modalidades;
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
public class ModalidadesDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        Modalidades m = (Modalidades) o;
        try {
            String sql = "INSERT INTO modalidades VALUES (default, '" + m.getNome() + "')";
           int retorno = ConexaoBD.getInstance().getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao salvar Modalidade: " + e);
            return e.toString();
        }
        return null;
    }

    @Override
    public String atualizar(Object o) {
        String retorno = null;
        Modalidades modal = (Modalidades) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE modalidades SET nome = '" + modal.getNome() + "', ativo = " + modal.isAtivo() + " WHERE idModalidades = " + modal.getIdModalidades() + "";
            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            //if (resultado > 0) {                

        } catch (Exception e) {
            System.out.println("Erro salvar Modalidade = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public String excluir(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM modalidades WHERE idModalidades = " + id + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir Modalidade = " + e);
            retorno = e.toString();
        }
        return retorno;
    }
    
    public String inativar(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE modalidades SET ativo = false WHERE idmodalidades = " + id + "";
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
        ArrayList<Object> modalidades = new ArrayList<>();
        try {
            String sql = "SELECT * FROM modalidades";
            ResultSet resultado = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);
            
            while (resultado.next()){
                Modalidades m = new Modalidades();
                m.setIdModalidades(resultado.getInt("idmodalidades"));
                m.setNome(resultado.getString("nome"));
                modalidades.add(m);
            }
        } catch (Exception e) {
            System.out.println("Erro consulta todas modalidades: " + e);
        }
        return modalidades;
    }

    @Override
    public ArrayList<Object> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object consultarId(int id) {
        Modalidades modalidades = new Modalidades();
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM modalidades WHERE idModalidades = " + id + "";

            System.out.println("SQL: " + sql);

            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            modalidades.setIdModalidades(resultadoQ.getInt("idModalidades"));
            modalidades.setNome(resultadoQ.getString("nome"));
            modalidades.setAtivo(resultadoQ.getBoolean("ativo"));
            return modalidades;
        } catch (Exception e) {
            System.out.println("Erro consultar modalidade = " + e);
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
                    + "SELECT count(*) FROM modalidades WHERE nome ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][3]; //diz quantas linhas e colunas serão criadas: getInt(1) pega a primeira coluna do Select; e "2" pq serão duas colunas (Código,Descrição).

        } catch (Exception e) {
            System.out.println("Erro ao consultar XXX: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM modalidades WHERE nome ILIKE '%" + criterio + "%' ORDER BY nome");

            while (resultadoQ.next()) {
                String ativo = resultadoQ.getString("ativo");
                if (ativo.equals("f")){
                    ativo = "Não";
                } else if (ativo.equals("t")){
                    ativo = "Sim";
                }

                dadosTabela[lin][0] = resultadoQ.getInt("idModalidades"); //poderia colocar o número da coluna (1)
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = ativo;

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
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
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
//            @Override
//            public Class getColumnClass(int column) {
//
//                if (column == 2) {
//                    // return ImageIcon.class;
//                }
//                return Object.class;
//            }
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
                    column.setPreferredWidth(319);
                    break;
                case 2:
                    column.setPreferredWidth(50);
                    break;
            }
        }
        // renderizacao das linhas da tabela = mudar a cor
//        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value,
//                    boolean isSelected, boolean hasFocus, int row, int column) {
//                super.getTableCellRendererComponent(table, value, isSelected,
//                        hasFocus, row, column);
//                if (row % 2 == 0) {
//                    setBackground(Color.GREEN);
//                } else {
//                    setBackground(Color.LIGHT_GRAY);
//                }
//                return this;
//            }
//        });
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
    
    public boolean possuiVinculos (int id){
        int qtde;
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM percurso WHERE ref_modalidades = " + id + "";
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
            System.out.println("Erro ao consultar: " + e);
        }
        return true;
    }

    public boolean verificaNome(String nome, int id) {
        ResultSet resultadoQ;
        int qtde = 0;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM modalidades WHERE nome = '" + nome + "'";
            System.out.println("SQL: " + sql);
            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
            qtde = resultadoQ.getInt(1);
            if (qtde == 0){
                return true;
            } else if (qtde == 1){
                String sql2 = "SELECT idmodalidades FROM modalidades WHERE nome = '" + nome + "'";
                System.out.println("SQL: " + sql2);
                resultadoQ = st.executeQuery(sql2);
                resultadoQ.next();
                int idmodalidade = resultadoQ.getInt("idmodalidades");
                if (idmodalidade == id){
                    return true;
                }
            }            
        } catch (Exception e) {
            System.out.println("Erro ao consultar se modalidade já existe: " + e);
        }
        return false;
    }
}
