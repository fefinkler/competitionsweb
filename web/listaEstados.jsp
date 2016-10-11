<%-- 
    Document   : listaEstados
    Created on : 12/09/2016, 20:15:00
    Author     : Fernanda Finkler
--%>

<%@page import="daos.PaisDAO"%>
<%@page import="entidades.Pais"%>
<%@page import="entidades.Estado"%>
<%@page import="daos.EstadoDAO"%>
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
                    <th>País</th>
                    <th>Ativo</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>
                <%
                    ArrayList<Object> estados = new EstadoDAO().consultarTodos();
                    for (int i = 0; i < estados.size(); i++) {
                        Estado estado = (Estado) estados.get(i);
                        String ativo;
                        if (estado.isAtivo()) {
                            ativo = "Sim";
                        } else {
                            ativo = "Não";
                        }
                        Pais p = (Pais) new PaisDAO().consultarId(estado.getPais());
                %>
                <tr>
                    <td><%=estado.getIdestado()%></td>
                    <td><%=estado.getNome()%></td>
                    <td><%=estado.getSigla()%></td>
                    <td><%=p.getNome() %></td>
                    <td><%= ativo%></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=editarEstado&id=<%=estado.getIdestado()%>">Editar</a></td>
                    <%
                        String msg = "";
                        String parametro = "";
                        if (new EstadoDAO().possuiVinculos(estado.getIdestado())) {
                            msg = "Este registro possui vínculos, não é possível excluir. Deseja inativá-lo?";
                            parametro = "inativar";
                        } else {
                            msg = "Deseja realmente excluir este registro?";
                            parametro = "excluir";
                        }
                    %>
                    <td><a OnClick="return confirm('<%=msg%>')" href="/CompetitionsWEB/controlador?parametro=<%=parametro%>Estado&id=<%=estado.getIdestado()%>">Excluir</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
