/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import apoio.ConexaoBD;
import entidades.Atleta;
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
public class AtletaDAO implements IDAO {

    @Override
    public String salvar(Object o) {
        String retorno = null;
        Atleta a = (Atleta) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO atleta VALUES "
                    + "(DEFAULT,"
                    + "'" + a.getNome() + "',"
                    + "'" + a.getDtnasc() + "',"
                    + "'" + a.getRg() + "',"
                    + "'" + a.getCpf() + "',"
                    + "'" + a.getTipoSang() + "',"
                    + "'" + a.getTelefone() + "',"
                    + "'" + a.getEmail() + "',"
                    + "'" + a.getEndereco() + "',"
                    + "'" + a.getCep() + "',"
                    + "'" + a.getParente() + "',"
                    + "'" + a.getTelefoneP() + "',"
                    + "'" + a.getAlergias() + "',"
                    + "'" + a.getCidade() + "',"
                    + "'" + a.getObservacoes() + "',"
                    + "'" + a.getLogin() + "',"
                    + "'" + a.getSenha() + "', "
                    + a.isAtivo() + ")";

            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
            //if (resultado > 0) {
        } catch (Exception e) {
            System.out.println("Erro salvar Atleta = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public String atualizar(Object o) {
        String retorno = null;
        Atleta a = (Atleta) o;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE atleta SET "
                    + "nome = '" + a.getNome() + "',"
                    + "dtnasc = '" + a.getDtnasc() + "',"
                    + "rg = '" + a.getRg() + "',"
                    + "cpf = '" + a.getCpf() + "',"
                    + "tiposang = '" + a.getTipoSang() + "',"
                    + "telefone = '" + a.getTelefone() + "',"
                    + "email = '" + a.getEmail() + "',"
                    + "endereco = '" + a.getEndereco() + "',"
                    + "cep = '" + a.getCep() + "',"
                    + "parente = '" + a.getParente() + "',"
                    + "contatoParente = '" + a.getTelefoneP() + "',"
                    + "alergias = '" + a.getAlergias() + "',"
                    + "ref_cidade = '" + a.getCidade() + "',"
                    + "observacoes = '" + a.getObservacoes() + "', "
                    + "login = '" + a.getLogin() + "', "
                    + "senha = '" + a.getSenha() + "',"
                    + "ativo = " + a.isAtivo()
                    + " WHERE idatleta = " + a.getIdatleta() + "";
            System.out.println("SQL: " + sql);

            int resultado = st.executeUpdate(sql);
            //if (resultado > 0) {                

        } catch (Exception e) {
            System.out.println("Erro salvar Atleta = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    @Override
    public String excluir(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "DELETE FROM atleta WHERE idatleta = " + id + "";
            System.out.println("SQL: " + sql);
            int resultado = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Erro ao excluir Atleta = " + e);
            retorno = e.toString();
        }
        return retorno;
    }

    public boolean possuiVinculos(int id) {
        int qtde;
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM equipe WHERE ref_atleta = " + id + "";
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

    public String inativar(int id) {
        String retorno = null;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "UPDATE atleta SET ativo = false WHERE idatleta = " + id + "";
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
        Atleta a = new Atleta();
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT * FROM atleta WHERE idatleta = " + id + "";

            System.out.println("SQL: " + sql);

            resultadoQ = st.executeQuery(sql);
            resultadoQ.next();
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
            return a;
        } catch (Exception e) {
            System.out.println("Erro consultar atleta = " + e);
            return null;
        }
    }

    public void popularTabelaAtletas(JTable tabela, String criterio) {
        ResultSet resultadoQ;

        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Código";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Telefone";
        cabecalho[3] = "Cidade";
        cabecalho[4] = "Ativo";

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery("SELECT count(*) FROM atleta WHERE nome ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            dadosTabela = new Object[resultadoQ.getInt(1)][5]; //diz quantas linhas e colunas serão criadas: getInt(1) pega o resultado da primeira coluna do Select; e "3" pq serão duas colunas (Nome,Telefone,Cidade).

        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT atleta.idatleta, atleta.nome, atleta.telefone, cidade.nome, atleta.ativo FROM atleta, cidade WHERE atleta.ref_cidade = cidade.idcidade AND atleta.nome ILIKE '%" + criterio + "%' ORDER BY atleta.nome");

            while (resultadoQ.next()) {
                String ativo = resultadoQ.getString(5);
                if (ativo.equals("f")) {
                    ativo = "Não";
                } else if (ativo.equals("t")) {
                    ativo = "Sim";
                }
                dadosTabela[lin][0] = resultadoQ.getString(1); //poderia colocar o número da coluna (1)
                dadosTabela[lin][1] = resultadoQ.getString(2);
                dadosTabela[lin][2] = resultadoQ.getString(3);
                dadosTabela[lin][3] = resultadoQ.getString(4);
                dadosTabela[lin][4] = ativo;
                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela Atletas...");
            System.out.println(e);
        }

        // efetua consulta na tabela
//        try {
//            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
//                    + "SELECT atleta.*, cidade.nome as cidade FROM atleta, cidade"
//                    + "WHERE atleta.ref_cidade = cidade.idcidade AND atleta.nome ILIKE '%" + criterio + "%' ORDER BY atleta.nome");
//
//            while (resultadoQ.next()) {
//                
//                dadosTabela[lin][0] = resultadoQ.getString("nome"); //poderia colocar o número da coluna (1)
//                dadosTabela[lin][1] = resultadoQ.getString("telefone");
//                dadosTabela[lin][2] = resultadoQ.getString(16);
//                lin++;
//            }
//        } catch (Exception e) {
//            System.out.println("problemas para popular tabela...");
//            System.out.println(e);
//        }
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
                    column.setPreferredWidth(240);
                    break;
                case 2:
                    column.setPreferredWidth(107);
                    break;
                case 3:
                    column.setPreferredWidth(200);
                    break;
                case 4:
                    column.setPreferredWidth(50);
                    break;
            }
        }
    }

    public boolean verificaLogin(String login, int id) {
        boolean ok = false;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM atleta WHERE login = '" + login + "' AND login != ''";
            System.out.println("SQL: " + sql);
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            int total = rs.getInt(1);

            if (total == 0) {
                ok = true;
            } else if (total == 1) {
                String sql2 = "SELECT idatleta FROM atleta WHERE login = '" + login + "'";
                System.out.println("SQL: " + sql2);
                rs = st.executeQuery(sql2);
                rs.next();
                int user = rs.getInt("idatleta");
                if (user == id) {
                    ok = true;
                }
            } else {
                ok = false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar login de atleta: " + e);
        }
        return ok;
    }
    
    public int contaParticipacoes(int id){
        int qtde = 0;
        ResultSet resultadoQ;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();
            String sql = "SELECT count(*) FROM equipe WHERE ref_atleta = " + id + "";
            System.out.println("SQL: " + sql);
            resultadoQ = st.executeQuery(sql);
            if (resultadoQ.next()){
                qtde = resultadoQ.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e);
        }
        return qtde;
    }

}
