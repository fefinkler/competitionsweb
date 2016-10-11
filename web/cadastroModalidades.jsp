<%-- 
    Document   : cadastroModalidades
    Created on : 15/08/2016, 21:29:22
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.Modalidades"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="boots/js/validacao51.js" type="text/javascript"></script>
        <title>TMT</title>
        <%@include file ="menu.jsp" %>
        
        <script type="text/javascript">
            function getXMLHttpRequestObject()
            {
                var xmlhttp;
                if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
                    try {
                        xmlhttp = new XMLHttpRequest();
                    } catch (e) {
                        xmlhttp = false;
                    }
                }
                return xmlhttp;
            }

            function enviarDados() {
                var http = new getXMLHttpRequestObject();

//                GET
//                var url = "acao";
//                var parameters = "a=cadLugar";
//                http.open("GET", url + "?" + parameters, true);

                var url = "acao";
                nome = document.getElementById("nome").value;
                ativo = document.getElementById("ativo").value;
                var parameters = "parametro=cadastraModalidade" + "&nome=" + nome + "&ativo=" + ativo;
                http.open("POST", url, true);
                //Send the proper header information along with the request
                http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                http.setRequestHeader("Content-length", parameters.length);
                http.setRequestHeader("Connection", "close");
                http.onreadystatechange = function () { //Handler function for call back on state change.
                    if (http.readyState == 4) {
                        alert(http.responseText);
                    }
                }
                http.send(parameters);
            }            

            // ------------------------------------------------------------------------------------------
            // Via JQuery
            $(document).ready(function () {
                $('#salvar').click(function ()
                {
                    $.ajax({
                        type: "POST",
                        url: "/CompetitionsWEB/controlador?parametro=cadastraModalidade",
                        data: $('form').serialize()
                    }).done(function (retorno) {
                        var resultado = $.trim(retorno);
                        if (resultado == "erro") {
                            alert("Deu erro:" + retorno);
                            // bootbox.alert("Deu erro!");
                        } else {
//                            bootbox.alert("Deu certo!");
                            swal("Deu certo!")
                 //           alert("Deu certo: " + retorno);
                            $('#formModalidade').each(function () {
                                this.reset();
                            });
                            $('#nome').focus();
                        }
                    });
                    return false;
                }
                );
            });
        </script>
        
    </head>
    <body>
        
        <br>
        <h1>Cadastro de Modalidades</h1>

        <%
            Modalidades m = (Modalidades) request.getAttribute("modalidade");
            if (m == null) {
                m = new Modalidades();
            }
        %>

        <!--<form action="/CompetitionsWEB/controlador?parametro=cadastraModalidade" method="post" name="dados" onSubmit="return validardadosMTD();">-->
        <form name="formModalidade" id="formModalidade" method="post" action="">    
            <input type="hidden" id="id" name="id" value="<%= m.getIdModalidades()%>">

            <label>Nome:</label>&nbsp;
            <input type="text" id="nome" name="nome" value="<%= m.getNome()%>"> &nbsp;
            <input type="checkbox" id="ativo" name="ativo" <%=m.isAtivo() ? "checked" : ""%>> Ativo &nbsp;
            <input type="submit" id="salvar" name="Submit" class="formobjects" value="Salvar">

            <!--<input type="reset" name="Reset" class="formobjects" value="Limpar">-->

        </form>
        
        <hr>
        <h4>Lista de Modalidades</h4>
        <!-- Consultar modal -->
        <button type="button" class="btn" href="listaModal.jsp" data-toggle="modal" data-target="#myModal">
            Consultar Modalidades
        </button>            

        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">

                        </h4>
                    </div>
                    <div class="modal-body" id="modal-body">

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>