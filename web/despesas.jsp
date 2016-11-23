<%-- 
    Document   : despesas
    Created on : 23/10/2016, 19:49:11
    Author     : Fernanda Finkler
--%>

<%@page import="daos.TiposDespesasDAO"%>
<%@page import="entidades.Competicao"%>
<%@page import="entidades.Despesa"%>
<%@page import="daos.CompeticaoDAO"%>
<%@page import="entidades.TiposDespesas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <%
            Competicao competi = (Competicao) request.getAttribute("competicao");
            if (competi == null) {
                competi = new Competicao();
            }
        %>
        
        <script lang="text/javascript">

            function carregaDespesas( )
            {
                $.ajax({
                    url: "/CompetitionsWEB/controlador?parametro=getDespesas",
                    cache: false,
                    success: function (html) {
                        $("#comboDespesas").empty();
                        $("#comboDespesas").append(html);
                    }
                });
            }
            function adicionaDespesa()
            {
                var idDespesa = $('#comboTiposDespesas').val();
                var valor = $('#valor').val();
                var observacao = $('#obs').val();
                var idComp = $('#idComp').val();

                if (idDespesa && valor && idComp) {
                    $.ajax({
                        type: "post",
                        url: "/CompetitionsWEB/controlador?parametro=addDespesa",
                        data: "idDespesa=" + idDespesa + "&valor=" + valor + "&obs=" + observacao + "&idComp=" + idComp,
                        cache: false,
                        success: function (compet) {
                            $( '#table-despesa' ).append( compet );
                            $('#comboTiposDespesas').val(0);
                            $('#valor').val("");
                            $('#obs').val("");
                        }
                    });
                } else {
                    swal("Selecione um tipo de Despesa e defina seu valor!");
                }
            }
            
            function removeDespesa( elem, idComp, idDespesa )
            {
                if (idDespesa && idComp) {
                    $.ajax({
                        type: "post",
                        url: "/CompetitionsWEB/controlador?parametro=excluirDespesa",
                        data: "idDespesa=" + idDespesa + "&idComp=" + idComp,
                        cache: false,
                        success: function () {
                           $( elem ).parent().parent().remove();
                        }
                    });
                } else {
                    swal("Erro ao remover Despesa!");
                }
            }
            
            $(document).ready(function () {
                carregaDespesas();
            });
            
        </script>
        
    </head>
    <body>
        <div>
            <BR>

            <div class="container tab-pane form-horizontal">
                <div class="form-group row">
                    <div class="form-group" id="comboDespesas"></div>

                    <div class="form-group">
                        <label for="valor">Valor:</label>&nbsp;
                        <input type="text" class="form-control" name="valor" id="valor"> &nbsp;
                    </div>
                    
                    <div class="form-group">
                        <label for="obs">Observação:</label>&nbsp;
                        <input type="text" class="form-control" name="obs" id="obs"> &nbsp;
                    </div>
                    
                    <div><input type="button" class="btn btn-block" role="button" value="Adicionar" onclick="adicionaDespesa()"></div>
                </div>



                <div class="form-group">
                    <table id="table-despesa" class="table table-striped">
                        <thead class="header">
                            <th>Despesa</th>
                            <th>Valor</th>
                            <th>Observação</th>
                        </thead>
                        <%                            
                            ArrayList<Despesa> despesas = new CompeticaoDAO().consultarDespesa(competi);
                            for (int y = 0; y < despesas.size(); y++) {
                                Despesa d = (Despesa) despesas.get(y);
                                TiposDespesas td = (TiposDespesas) new TiposDespesasDAO().consultarId(d.getDespesa());

                        %>
                        <tr>
                            <td><%= td.getNome()%></td>
                            <td><%= d.getValor()%></td>
                            <td><%= d.getObservacao()%></td>
                            <td><a role="button" onclick="removeDespesa( this, <%=d.getCompeticao()%>, <%=d.getDespesa()%> )">Remover</a></td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
