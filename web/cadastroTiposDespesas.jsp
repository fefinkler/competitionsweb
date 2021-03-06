<%-- 
    Document   : cadastroTiposDespesas
    Created on : 31/08/2016, 13:03:35
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.TiposDespesas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="boots/js/validacao51.js" type="text/javascript"></script>
        <title>TMT</title>
    </head>
    <body>
        <%@include file ="menu.jsp" %>
        <br>
        <h1>Cadastro de Tipos de Despesas</h1>

        <%
            TiposDespesas td = (TiposDespesas) request.getAttribute("tipodespesa");
            if (td == null) {
                td = new TiposDespesas();
            }
        %>

        <form action="/CompetitionsWEB/controlador?parametro=cadastraTipoDespesa" method="post" name="dados" onSubmit="return validardadosMTD();">
            <input type="hidden" name="id" value="<%= td.getIdTiposDespesas()%>">

            <label>Nome:</label>&nbsp;
            <input type="text" name="nome" value="<%= td.getNome()%>"> &nbsp;
            <input type="checkbox" name="ativo" <%=td.isAtivo() ? "checked" : ""%>> Ativo &nbsp;
            <input type="submit" name="Submit" class="formobjects" value="Salvar">

            <!--<input type="reset" name="Reset" class="formobjects" value="Limpar">-->
        </form>

        <BR>
        <%@include file ="listaTiposDespesas.jsp" %>

    </body>
</html>
