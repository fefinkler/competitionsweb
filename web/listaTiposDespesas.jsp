<%-- 
    Document   : listaTiposDespesas
    Created on : 05/09/2016, 21:17:20
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.TiposDespesas"%>
<%@page import="daos.TiposDespesasDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TMT</title>
    </head>
    <body>
        <div class="table-responsive">
            <table class="table table-striped">
                <tr class="header">
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Ativo</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>
                <%
                    ArrayList<Object> tipos = new TiposDespesasDAO().consultarTodos();
                    for (int i = 0; i < tipos.size(); i++) {
                        TiposDespesas tipodespesa = (TiposDespesas) tipos.get(i);
                        String ativo;
                        if (tipodespesa.isAtivo()) {
                            ativo = "Sim";
                        } else {
                            ativo = "Não";
                        }
                %>
                <tr>
                    <td><%=tipodespesa.getIdTiposDespesas()%></td>
                    <td><%=tipodespesa.getNome()%></td>
                    <td><%= ativo%></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=editarTipoDespesa&id=<%=tipodespesa.getIdTiposDespesas()%>">Editar</a></td>
                    <%
                        String msg = "";
                        String parametro = "";
                        if (new TiposDespesasDAO().possuiVinculos(tipodespesa.getIdTiposDespesas())) {
                            msg = "Este registro possui vínculos, não é possível excluir. Deseja inativá-lo?";
                            parametro = "inativar";
                        } else {
                            msg = "Deseja realmente excluir este registro?";
                            parametro = "excluir";
                        }
                    %>
                    <td><a OnClick="return confirm('<%=msg%>')" href="/CompetitionsWEB/controlador?parametro=<%=parametro%>TipoDespesa&id=<%=tipodespesa.getIdTiposDespesas()%>">Excluir</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
