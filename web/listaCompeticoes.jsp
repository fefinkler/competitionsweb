<%-- 
    Document   : listaCompeticoes
    Created on : 23/10/2016, 17:32:24
    Author     : Fernanda Finkler
--%>

<%@page import="daos.CompeticaoDAO"%>
<%@page import="entidades.Competicao"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="table-responsive">
            <table class="table table-striped">
                <tr class="header">
                    <th>ID</th>
                    <th>Data</th>
                    <th>Nome</th>
                    <th>Situação</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>
                <%
                    ArrayList<Object> competicoes = new CompeticaoDAO().consultarTodos();
                    for (int i = 0; i < competicoes.size(); i++) {
                        Competicao competicao = (Competicao) competicoes.get(i);
                        
                %>
                <tr>
                    <td><%=competicao.getId()%></td>
                    <td><%=competicao.getDia()%></td>
                    <td><%=competicao.getNome()%></td>
                    <td><%=competicao.getStatus()%></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=editarCompeticao&id=<%=competicao.getId()%>">Editar</a></td>
                    <td><a OnClick="return confirm('Deseja realmente excluir este registro?')" href="/CompetitionsWEB/controlador?parametro=excluirCompeticao&id=<%=competicao.getId()%>">Excluir</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
