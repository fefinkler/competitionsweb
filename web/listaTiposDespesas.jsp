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
                    <td>ID</td>
                    <td>Nome</td>
                    <td>Ativo</td>
                    <td>Editar</td>
                    <td>Excluir</td>
                </tr>
                <%
                    ArrayList<Object> tipos = new TiposDespesasDAO().consultarTodos();
                    for (int i = 0; i < tipos.size(); i++) {
                        TiposDespesas tipodespesa = (TiposDespesas) tipos.get(i);
                        String ativo;
                        if (tipodespesa.isAtivo()){
                            ativo = "Sim";
                        } else {
                            ativo = "Não";
                        }
                %>
                <tr>
                    <td><%=tipodespesa.getIdTiposDespesas()%></td>
                    <td><%=tipodespesa.getNome()%></td>
                    <td><%= ativo %></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=editarTipoDespesa&id=<%=tipodespesa.getIdTiposDespesas()%>">Editar</a></td>
                    <td><a OnClick="return confirm('Confirma exclusão?')" href="/CompetitionsWEB/controlador?parametro=excluirTipoDespesa&id=<%=tipodespesa.getIdTiposDespesas()%>">Excluir</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
