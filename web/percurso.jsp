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
            Competicao art = (Competicao) request.getAttribute("competicao");
            if (art == null) {
                art = new Competicao();
            }

        %>
        <script lang="text/javascript">

            // funcao associada a mudanca de valor na combo Estados
            $(document).ready(function () {

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
                    <div><button class="btn btn-block">Adicionar</button></div>
                </div>



                <div class="form-group">
                    <table>
                        <tr>
                            <th>Modalidade</th>
                            <th>Distância</th>
                        </tr>
                        <%                            
                            ArrayList<Percurso> percursos = new CompeticaoDAO().consultarPercurso(art);
                            for (int i = 0; i < percursos.size(); i++) {
                                Percurso p = (Percurso) percursos.get(i);
                                Modalidades m = (Modalidades) new ModalidadesDAO().consultarId(p.getModalidade());

                        %>
                        <tr>
                            <td><%= m.getNome()%></td>
                            <td><%= p.getKm()%></td>
                            <td><a href="/CompetitionsWEB/controlador?parametro=excluirPercurso&idComp=<%=p.getCompeticao()%>&idModal=<%=p.getModalidade()%>">Remover</a></td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
