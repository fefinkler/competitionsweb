<%-- 
    Document   : cadastroCidades
    Created on : 18/09/2016, 21:03:59
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.Cidade"%>
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
            Cidade c = (Cidade) request.getAttribute("cidade");
            
            int paisSelecionado = 0;
            int estadoSelecionado = 0;
            
            if (c == null) {
                c = new Cidade();
            }
            else
            {
              paisSelecionado = ( (Estado)new EstadoDAO().consultarId( c.getEstado() ) ).getPais();
              estadoSelecionado = c.getEstado();
            }
        %>
        
        <script lang="text/javascript">

            // funcao associada a mudanca de valor na combo Estados
            $(document).ready(function () {
              
                function carregaPais( )
                {
                    $.ajax({
                        url: "/CompetitionsWEB/controlador?parametro=getPais",
                        cache: false,
                        success: function (html) {
                            $("#combop").empty();
                            $("#combop").append(html);
                            
                              $('#comboPais').on("change", function () {
                                        carregaEstados();
                              });
                              
                              $('#comboPais').find( "option[value='" + <%=paisSelecionado%> + "']" ).attr( 'selected', 'true' );
                        }
                    });
                }
                
                function carregaEstados( )
                {
                    var idPais = $("#comboPais").val() || <%=paisSelecionado%>|| 0;
                    $.ajax({
                        url: "/CompetitionsWEB/controlador?parametro=getEstado&idPais=" + idPais,
                        cache: false,
                        success: function (html) {
                            $("#comboe").empty();
                            $("#comboe").append(html);
                            
                            $('#comboe').find( "option[value='" + <%=estadoSelecionado%> + "']" ).attr( 'selected', 'true' );
                        }
                    });
                }

                loadForm = function( )
                {
                    carregaPais();
                    carregaEstados(  );
                }
                
                loadForm();
            });
        </script>

    </head>
    <body>
        <jsp:include page='cadastroLocais.jsp'>
            <jsp:param name="active" value="c"></jsp:param>
        </jsp:include>
        <BR>

        
        <div class="container tab-pane">
            <form class="form-inline" action="/CompetitionsWEB/controlador?parametro=cadastraCidade" method="post" name="dados" onSubmit="return validardadosCidade();">
                <input type="hidden" name="id" value="<%= c.getIdcidade()%>">

                <div class="form-group">
                    <label for="nome">Nome:</label>&nbsp;
                    <input type="text" class="form-control" name="nome" id="nome" value="<%= c.getNome()%>"> &nbsp;
                </div>
                <div class="form-group" id="combop"></div>
                <div class="form-group" id="comboe"></div>
                <div class="form-group">
                    <input type="checkbox" name="ativo" <%=c.isAtivo() ? "checked" : ""%>> Ativo &nbsp;
                </div>
                <div class="form-group">
                    <input type="submit" name="Submit" class="formobjects" value="Salvar">
                </div>   

                <!--<input type="reset" name="Reset" class="formobjects" value="Limpar">-->
            </form>
        </div>

        <BR>
        <%@include file ="listaCidades.jsp" %>

    </body>
</html>