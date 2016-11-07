<%-- 
    Document   : listaPais
    Created on : 05/09/2016, 21:57:20
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.Pais"%>
<%@page import="daos.PaisDAO"%>
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
                    <th>Sigla</th>
                    <th>Ativo</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>
                <%
                    ArrayList<Object> listPaises = new PaisDAO().consultarTodos();
                    for (int i = 0; i < listPaises.size(); i++) {
                        Pais pais = (Pais) listPaises.get(i);
                        String ativo;
                        if (pais.isAtivo()) {
                            ativo = "Sim";
                        } else {
                            ativo = "Não";
                        }
                %>
                <tr>
                    <td><%=pais.getIdpais()%></td>
                    <td><%=pais.getNome()%></td>
                    <td><%=pais.getSigla()%></td>
                    <td><%= ativo%></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=editarPais&id=<%=pais.getIdpais()%>">Editar</a></td>
                    <%
                        String msg = "";
                        String parametro = "";
                        if (new PaisDAO().possuiVinculos(pais.getIdpais())) {
                            msg = "Este registro possui vínculos, não é possível excluir. Deseja inativá-lo?";
                            parametro = "inativar";
                        } else {
                            msg = "Deseja realmente excluir este registro?";
                            parametro = "excluir";
                        }
                    %>
                    <td><a OnClick="return confirm('<%=msg%>')" href="/CompetitionsWEB/controlador?parametro=<%=parametro%>Pais&id=<%=pais.getIdpais()%>">Excluir</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
