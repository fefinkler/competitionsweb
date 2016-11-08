<%-- 
    Document   : percurso
    Created on : 23/10/2016, 19:48:45
    Author     : Fernanda Finkler
--%>

<%@page import="daos.ModalidadesDAO"%>
<%@page import="entidades.Modalidades"%>
<%@page import="entidades.Percurso"%>
<%@page import="daos.CompeticaoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidades.Competicao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importacao do arquivo de validacao -->
        <script src="boots/js/jquery.min.js" type="text/javascript"></script>
        <script src="boots/js/validacao51.js" type="text/javascript"></script>
        <title>TMT</title>

        <%
            Competicao competPer = (Competicao) request.getAttribute("competicao");
            if (competPer == null) {
                competPer = new Competicao();
            }

        %>
        <script lang="text/javascript">

            function carregaModalidades( )
            {
                $.ajax({
                    url: "/CompetitionsWEB/controlador?parametro=getModalidades",
                    cache: false,
                    success: function (html) {
                        $("#comboModalidades").empty();
                        $("#comboModalidades").append(html);
                    }
                });
            }
            function adicionaPercurso()
            {
                var idModal = $('#comboModal').val();
                var km = $('#dist').val();
                var idComp = $('#idComp').val();

                if (idModal && km && idComp) {
                    $.ajax({
                        type: "post",
                        url: "/CompetitionsWEB/controlador?parametro=addPercurso",
                        data: "idModal=" + idModal + "&km=" + km + "&idComp=" + idComp,
                        cache: false,
                        success: function (compet) {
                            $( '#table-percurso' ).append( compet );
                            $('#comboModal').val(0);
                            $('#dist').val("");
                        }
                    });
                } else {
                    swal("Selecione uma modalidade e defina sua distância!");
                }
            }
            
            function removePercurso( elem, idComp, idModal )
            {
                if (idModal && idComp) {
                    $.ajax({
                        type: "post",
                        url: "/CompetitionsWEB/controlador?parametro=excluirPercurso",
                        data: "idModal=" + idModal + "&idComp=" + idComp,
                        cache: false,
                        success: function () {
                           $( elem ).parent().parent().remove();
                        }
                    });
                } else {
                    swal("Erro ao remover Modalidade!");
                }
            }
            
            $(document).ready(function () {
                carregaModalidades();
            });
            
        </script>
    </head>
    <body>
        <div>
            <BR>

            <div class="container tab-pane form-horizontal">
                <div class="form-group row">
                    <div class="form-group" id="comboModalidades"></div>

                    <div class="form-group">
                        <label for="dist">Distância:</label>&nbsp;
                        <input type="text" class="form-control" name="dist" id="dist"> &nbsp;
                    </div>
                    <div><input type="button" class="btn btn-block" role="button" value="Adicionar" onclick="adicionaPercurso()"></div>
                </div>



                <div class="form-group">
                    <table id="table-percurso" class="table table-striped">
                        <thead class="header">
                            <th>Modalidade</th>
                            <th>Distância</th>
                        </thead>
                        <%                            
                            ArrayList<Percurso> percursos = new CompeticaoDAO().consultarPercurso(competPer);
                            for (int i = 0; i < percursos.size(); i++) {
                                Percurso p = (Percurso) percursos.get(i);
                                Modalidades m = (Modalidades) new ModalidadesDAO().consultarId(p.getModalidade());

                        %>
                        <tr>
                            <td><%= m.getNome()%></td>
                            <td><%= p.getKm()%></td>
                            <td><a role="button" onclick="removePercurso( this, <%=p.getCompeticao()%>, <%=p.getModalidade()%> )">Remover</a></td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
