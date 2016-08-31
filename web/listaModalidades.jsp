<%-- 
    Document   : listaModalidades
    Created on : 15/08/2016, 20:38:15
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.Modalidades"%>
<%@page import="daos.ModalidadesDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TMT</title>
    </head>
    <body>

        <table border="0">
            <tr>
                <td>ID</td>
                <td>Nome</td>
                <td>Editar</td>
                <td>Excluir</td>
            </tr>
            <%
                ArrayList<Object> modalidades = new ModalidadesDAO().consultarTodos();
                for (int i = 0; i < modalidades.size(); i++) {
                    Modalidades m = (Modalidades) modalidades.get(i);
            %>
            <tr>
                <td><%=m.getIdModalidades()%></td>
                <td><%=m.getNome()%></td>
                <td><a href="/CompetitionsWEB/controlador?parametro=editarModalidade&id=<%=m.getIdModalidades()%>">Editar</a></td>
                <td><a href="/CompetitionsWEB/controlador?parametro=excluirModalidade&id=<%=m.getIdModalidades()%>">Excluir</a></td>
            </tr>
            <%
                }
            %>
        </table>

    </body>
</html>
