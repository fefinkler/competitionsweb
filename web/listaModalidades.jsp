

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
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Ativo</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>
                <%
                    String busca = request.getParameter("criterioModalidades");
                    ArrayList<Object> modalidades = new ModalidadesDAO().consultar(busca);
                    for (int i = 0; i < modalidades.size(); i++) {
                        Modalidades modalidade = (Modalidades) modalidades.get(i);
                        String ativo;
                        if (modalidade.isAtivo()) {
                            ativo = "Sim";
                        } else {
                            ativo = "Não";
                        }
                %>
                <tr>
                    <td><%=modalidade.getIdModalidades()%></td>
                    <td><%=modalidade.getNome()%></td>
                    <td><%= ativo%></td>
                    <td><a href="/CompetitionsWEB/controlador?parametro=editarModalidade&id=<%=modalidade.getIdModalidades()%>">Editar</a></td>
                    <%
                        String msg = "";
                        String parametro = "";
                        if (new ModalidadesDAO().possuiVinculos(modalidade.getIdModalidades())) {
                            msg = "Este registro possui vínculos, não é possível excluir. Deseja inativá-lo?";
                            parametro = "inativar";
                        } else {
                            msg = "Deseja realmente excluir este registro?";
                            parametro = "excluir";
                        }
                    %>
                    <td><a OnClick="return confirm('<%=msg%>')" href="/CompetitionsWEB/controlador?parametro=<%=parametro%>Modalidade&id=<%=modalidade.getIdModalidades()%>">Excluir</a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>

    </body>
</html>
