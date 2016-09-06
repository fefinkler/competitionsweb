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
                    <td>ID</td>
                    <td>Nome</td>
                    <td>Sigla</td>
                    <td>Ativo</td>
                    <td>Editar</td>
                    <td>Excluir</td>
                </tr>
                <%
                    ArrayList<Object> paises = new PaisDAO().consultarTodos();
                    for (int i = 0; i < paises.size(); i++) {
                        Pais pais = (Pais) paises.get(i);
                        String ativo;
                        if (pais.isAtivo()){
                            ativo = "Sim";
                        } else {
                            ativo = "Não";
                        }
                %>
                <tr>
                    <td><%=pais.getIdpais()%></td>
                    <td><%=pais.getNome()%></td>
                    <td><%=pais.getSigla()%></td>
                    <td><%= ativo %></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=editarPais&id=<%=pais.getIdpais()%>">Editar</a></td>
                    <td><a OnClick="return confirm('Confirma exclusão?')" href="/CompetitionsWEB/controlador?parametro=excluirPais&id=<%=pais.getIdpais()%>">Excluir</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
