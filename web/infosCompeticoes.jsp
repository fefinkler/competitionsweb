<%-- 
    Document   : infosCompeticoes
    Created on : 23/10/2016, 19:48:31
    Author     : Fernanda Finkler
--%>

<%@page import="daos.CidadeDAO"%>
<%@page import="daos.EstadoDAO"%>
<%@page import="entidades.Estado"%>
<%@page import="entidades.Cidade"%>
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
            Competicao comp = (Competicao) request.getAttribute("competicao");

            int paisSelecionado = 0;
            int estadoSelecionado = 0;
            int cidadeSelecionada = 0;

            if (comp == null) {
                comp = new Competicao();
            } else {
                cidadeSelecionada = comp.getCidade();
                estadoSelecionado = ((Cidade) new CidadeDAO().consultarId(cidadeSelecionada)).getEstado();
                paisSelecionado = ((Estado) new EstadoDAO().consultarId(estadoSelecionado)).getPais();
                
            }
        %>
        <script lang="text/javascript">

            // funcao associada a mudanca de valor na combo Estados
            $(document).ready(function () {

                function carregaPaisComp( )
                {
                    $.ajax({
                        url: "/CompetitionsWEB/controlador?parametro=getPais",
                        cache: false,
                        success: function (html) {
                            $("#combop").empty();
                            $("#combop").append(html);

                            $('#comboPais').on("change", function () {
                                carregaEstadosComp();
                            });

                            $('#comboPais').find("option[value='" + <%=paisSelecionado%> + "']").attr('selected', 'true');
                        }
                    });
                }

                function carregaEstadosComp( )
                {
                    var idPais = $("#comboPais").val() || <%=paisSelecionado%> || 0;
                    $.ajax({
                        url: "/CompetitionsWEB/controlador?parametro=getEstado&idPais=" + idPais,
                        cache: false,
                        success: function (html) {
                            $("#comboe").empty();
                            $("#comboe").append(html);

                            $('#estado').on("change", function () {
                                carregaCidadesComp();
                            });


                            $('#estado').find("option[value='" + <%=estadoSelecionado%> + "']").attr('selected', 'true');
                        }
                    });
                }

                function carregaCidadesComp( )
                {
                    var idEstado = $("#estado").val() || <%=estadoSelecionado%> || 0;
                    $.ajax({
                        url: "/CompetitionsWEB/controlador?parametro=getCidade&idEstado=" + idEstado,
                        cache: false,
                        success: function (html) {
                            $("#comboc").empty();
                            $("#comboc").append(html);

                            $('#comboc').find("option[value='" + <%=cidadeSelecionada%> + "']").attr('selected', 'true');
                        }
                    });
                }

                loadFormComp = function (  )
                {
                    carregaPaisComp();
                    carregaEstadosComp();
                    carregaCidadesComp();
                }

                loadFormComp();
            });
        </script>
    </head>
    <div>
        <BR>
        
        <div class="container tab-pane form-horizontal">
            
                <input type="hidden" name="id" id="idComp" value="<%= comp.getId()%>">

                <div class="form-group">
                    <label for="nome">Nome:</label>&nbsp;
                    <input type="text" class="form-control" name="nome" id="nome" value="<%= comp.getNome()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Data:</label>&nbsp;
                    <input type="date" class="form-control" name="dia" id="dia" value="<%= comp.getDia()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Situação:</label>&nbsp;
                    <%char status = comp.getStatus();%>
                    <input type="radio" name="status" id="programada" value="p" <%=status!='r' || status!='s' ? "checked" : ""%>> Programada &nbsp; 
                    <input type="radio" name="status" id="realizada" value="r"  <%=status=='r' ? "checked" : ""%>> Realizada &nbsp;
                    <input type="radio" name="status" id="suspensa" value="s" <%=status=='s' ? "checked" : ""%>> Suspensa &nbsp;
                </div>
                <div class="form-group" id="combop"></div>
                <div class="form-group" id="comboe"></div>
                <div class="form-group" id="comboc"></div>
                <div class="form-group">
                    <label for="nome">Localidade:</label>&nbsp;
                    <input type="text" class="form-control" name="local" id="local" value="<%= comp.getLocalidade()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Colocação:</label>&nbsp;
                    <input type="text" class="form-control" name="colocacao" id="colocacao" value="<%= comp.getColocacao()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Premiação:</label>&nbsp;
                    <input type="text" class="form-control" name="premiacao" id="premiacao" value="<%= comp.getPremiacao()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Relato:</label>&nbsp;
                    <textarea row="12" class="form-control" name="relato" id="relato" value="<%= comp.getRelato()%>"></textarea> &nbsp;
                </div>
        </div>
    </div>
</html>
