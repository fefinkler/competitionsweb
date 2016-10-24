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

        <script lang="text/javascript">
            buscarMod = function () {
                alert( $('#busca').val() );
                window.location.href = "cadastroModalidades.jsp?busca=" + $('#busca').val();
            };
        </script>

    </head>
    <body>

        <br>
        <h1>Cadastro de Modalidades</h1>

        <%            
        Object b = request.getParameter( "busca" );
        
        String busca = b == null ? "" : b.toString();
        
        Modalidades m = (Modalidades) request.getAttribute("modalidade");
            if (m == null) {
                m = new Modalidades();
            }
        %>

        <form action="/CompetitionsWEB/controlador?parametro=cadastraModalidade" method="post" name="dados" onSubmit="return validardadosMTD(event);">
            <!--<form name="formModalidade" id="formModalidade" method="post" action="">-->    
            <input type="hidden" id="id" name="id" value="<%= m.getIdModalidades()%>">

            <label>Nome:</label>&nbsp;
            <input type="text" id="nome" name="nome" value="<%= m.getNome()%>"> &nbsp;
            <input type="checkbox" id="ativo" name="ativo" <%=m.isAtivo() ? "checked" : ""%>> Ativo &nbsp;
            <input type="submit" id="salvar" name="Submit" class="formobjects" value="Salvar">
            <input type="reset" value="Limpar"

                   <BR>
            <label>Busca:</label>&nbsp;
            <input type="text" id="busca" name="busca" value=""> &nbsp;
            <input type="button" id="buscar" name="Buscar" onclick="buscarMod()"class="formobjects" value="Buscar">

        </form>

        <BR>
        <jsp:include page='listaModalidades.jsp'>
            <jsp:param name="criterioModalidades" value="<%=busca%>" />
        </jsp:include>

    </body>
</html>