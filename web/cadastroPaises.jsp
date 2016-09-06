<%-- 
    Document   : cadastroPaises
    Created on : 05/09/2016, 21:52:21
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.Pais"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TMT</title>
    </head>
    <body>
        <%@include file ="cadastroLocais.jsp" %>

        <%
            Pais p = (Pais) request.getAttribute("pais");
            if (p == null) {
                p = new Pais();
            }
        %>

        <form action="/CompetitionsWEB/controlador?parametro=cadastraPais" method="post">
            <input type="hidden" name="id" value="<%= p.getIdpais()%>">

            <label>Nome:</label>&nbsp;
            <input type="text" name="nome" value="<%= p.getNome()%>"> &nbsp;
            <label>Sigla:</label>&nbsp;
            <input type="text" name="sigla" value="<%= p.getSigla()%>"> &nbsp;
            <input type="checkbox" name="ativo" <%=p.isAtivo() ? "checked" : ""%>> Ativo &nbsp;
            <input type="submit" value="Salvar">

        </form>

        <BR>
        <%@include file ="listaPais.jsp" %>

    </body>
</html>
