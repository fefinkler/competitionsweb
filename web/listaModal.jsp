<%-- 
    Document   : listaModal
    Created on : 10/10/2016, 20:45:06
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

        <style type="text/css" media="all">
            table#tableTrClick tr.trClick{background: #fff; color: #000; cursor: pointer;}  
            table#tableTrClick tr.trClick:hover{background: blue; color: #fff;}  
        </style>

        <script lang="javascript">
            $(document).ready(function () {

                $('#tableTrClick tr').click(function () {
                    $('#id').val($(this).find('td').eq(0).html());
                    $('#nome').val($(this).find('td').eq(1).html());
                    $('#ativo').val($(this).find('td').eq(2).html());
                });
            });

        </script>


        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de Modalidades!</h1>
        <div id="content">
            <table class="table table-responsive" id="tableTrClick">               

                <tr>
                    <td>ID</td>
                    <td>Nome</td>
                    <td>Ativo</td>
                </tr>
                <%
                    ArrayList<Object> modalidades = new ModalidadesDAO().consultarTodos();

                    for (int i = 0; i < modalidades.size(); i++) {
                        Modalidades modalidade = (Modalidades) modalidades.get(i);
                        String ativo;
                        if (modalidade.isAtivo()) {
                            ativo = "Sim";
                        } else {
                            ativo = "Não";
                        }
                %>

                <tr class="trClick">
                    <td><%= modalidade.getIdModalidades()%></td>
                    <td><%= modalidade.getNome()%></td>
                    <td><%= ativo%></td>
                    <!--<td><a OnClick="return confirm('Confirma exclusão?')" href="index_original.html">Excluir</a></td>-->
                    <!--<td><a href="/CompetitionsWEB/controlador?parametro=editarModalidade&id=<%=modalidade.getIdModalidades()%>">Editar</a></td>-->
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

        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        </div>

    </body>
</html>
