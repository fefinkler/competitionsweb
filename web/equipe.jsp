<%-- 
    Document   : equipe
    Created on : 23/10/2016, 19:48:58
    Author     : Fernanda Finkler
--%>

<%@page import="daos.CompeticaoDAO"%>
<%@page import="entidades.Competicao"%>
<%@page import="daos.CidadeDAO"%>
<%@page import="entidades.Cidade"%>
<%@page import="daos.AtletaDAO"%>
<%@page import="entidades.Atleta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
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
            Competicao competEquipe = (Competicao) request.getAttribute("competicao");
            if (competEquipe == null) {
                competEquipe = new Competicao();
            }
        %>

        <script lang="text/javascript">

            function adicionaAtleta(elem, idComp, idAtleta)
            {
                if (idAtleta && idComp) {
                    $.ajax({
                        type: "post",
                        url: "/CompetitionsWEB/controlador?parametro=addAtleta",
                        data: "idAtleta=" + idAtleta + "&idComp=" + idComp,
                        cache: false,
                        success: function (compet) {
                            $( '#table-equipe' ).append( compet );
                        }
                    });
                } else {
                    swal("Erro ao adicionar Atleta!");
                }
            }

            function removeAtleta(elem, idComp, idAtleta)
            {
                if (idAtleta && idComp) {
                    $.ajax({
                        type: "post",
                        url: "/CompetitionsWEB/controlador?parametro=excluirAtleta",
                        data: "idAtleta=" + idAtleta + "&idComp=" + idComp,
                        cache: false,
                        success: function () {
                            $(elem).parent().parent().remove();
                        }
                    });
                } else {
                    swal("Erro ao remover Atleta da Equipe!");
                }
            }

        </script>
    </head>
    <body>
        <div class="form-group">
            <table id="table-atletas" class="table table-striped">
                <thead class="header">
                <th>Nome</th>
                <th>Cidade</th>
                </thead>
                <%
                    ArrayList<Atleta> atletas = new AtletaDAO().consultarTodosAtivos();
                    for (int i = 0; i < atletas.size(); i++) {
                        Atleta a = (Atleta) atletas.get(i);
                        Cidade c = (Cidade) new CidadeDAO().consultarId(a.getCidade());
                %>
                <tr>
                    <td><%= a.getNome()%></td>
                    <td><%= c.getNome()%></td>
                    <td><a role="button" onclick="adicionaAtleta(this, <%= competEquipe.getId()%>, <%=a.getIdatleta()%>)">Adicionar</a></td>
                </tr>
                <%}%>
            </table>
        </div>

        <div class="form-group">
            <table id="table-equipe" class="table table-striped">
                <thead class="header">
                <th>Nome</th>
                <th>Cidade</th>
                </thead>
                <%
                    ArrayList<Atleta> atletasEquipe = new CompeticaoDAO().consultarEquipe(competEquipe);
                    for (int i = 0; i < atletasEquipe.size(); i++) {
                        Atleta a = (Atleta) atletasEquipe.get(i);
                        Cidade c = (Cidade) new CidadeDAO().consultarId(a.getCidade());
                %>
                <tr>
                    <td><%= a.getNome()%></td>
                    <td><%= c.getNome()%></td>
                    <td><a role="button" onclick="removeAtleta(this, <%= competEquipe.getId()%>, <%=a.getIdatleta()%>)">Remover</a></td>
                </tr>
                
            </table>
        </div>
            <BR>
            <div class="form-group">
                <input type="button" id="idBotao"  value="valueBotao" OnClick="parent.location.href='relatorioDadosInscricao.jsp?idCompEquipe=<%=competEquipe.getId()%>'">
                <!--<input type="button" name="Imprimir" class="btn btn-primary" value="Imprimir" onClick="relatorioCurriculo.jsp?idAtletaCurriculo">-->
            </div> 
    </body>
</html>
