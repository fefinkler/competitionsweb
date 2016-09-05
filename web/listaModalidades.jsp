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
                    ArrayList<Object> modalidades = new ModalidadesDAO().consultarTodos();
                    for (int i = 0; i < modalidades.size(); i++) {
                        Modalidades modalidade = (Modalidades) modalidades.get(i);
                        String ativo;
                        if (modalidade.isAtivo()){
                            ativo = "Sim";
                        } else {
                            ativo = "Não";
                        }
                %>
                <tr>
                    <td><%=modalidade.getIdModalidades()%></td>
                    <td><%=modalidade.getNome()%></td>
                    <td><%= ativo %></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=editarModalidade&id=<%=modalidade.getIdModalidades()%>">Editar</a></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=excluirModalidade&id=<%=modalidade.getIdModalidades()%>">Excluir</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
