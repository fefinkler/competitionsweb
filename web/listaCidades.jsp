<%-- 
    Document   : listaCidades
    Created on : 18/09/2016, 21:36:14
    Author     : Fernanda Finkler
--%>

<%@page import="daos.PaisDAO"%>
<%@page import="entidades.Pais"%>
<%@page import="entidades.Estado"%>
<%@page import="daos.EstadoDAO"%>
<%@page import="entidades.Cidade"%>
<%@page import="daos.CidadeDAO"%>
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
                    <th>Estado</th>
                    <th>País</th>
                    <th>Ativo</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>
                <%
                    ArrayList<Object> cidades = new CidadeDAO().consultarTodos();
                    for (int i = 0; i < cidades.size(); i++) {
                        Cidade cidade = (Cidade) cidades.get(i);
                        String ativo;
                        if (cidade.isAtivo()) {
                            ativo = "Sim";
                        } else {
                            ativo = "Não";
                        }
                        Estado e = (Estado) new EstadoDAO().consultarId(cidade.getEstado());
                        Pais p = (Pais) new PaisDAO().consultarId(e.getPais());
                %>
                <tr>
                    <td><%=cidade.getIdcidade()%></td>
                    <td><%=cidade.getNome()%></td>
                    <td><%=e.getNome()%></td>
                    <td><%=p.getNome() %></td>
                    <td><%= ativo%></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=editarCidade&id=<%=cidade.getIdcidade()%>">Editar</a></td>
                    <%
                        String msg = "";
                        String parametro = "";
                        if (new CidadeDAO().possuiVinculos(cidade.getIdcidade())) {
                            msg = "Este registro possui vínculos, não é possível excluir. Deseja inativá-lo?";
                            parametro = "inativar";
                        } else {
                            msg = "Deseja realmente excluir este registro?";
                            parametro = "excluir";
                        }
                    %>
                    <td><a OnClick="return confirm('<%=msg%>')" href="/CompetitionsWEB/controlador?parametro=<%=parametro%>Cidade&id=<%=cidade.getIdcidade()%>">Excluir</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>