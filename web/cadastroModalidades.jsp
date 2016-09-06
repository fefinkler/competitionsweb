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
        <title>TMT</title>
    </head>
    <body>
        <%@include file ="menu.jsp" %>
        <br>
        <h1>Cadastro de Modalidades</h1>

        <%
            Modalidades m = (Modalidades) request.getAttribute("modalidade");
            if (m == null) {
                m = new Modalidades();
            }
        %>

        <form action="/CompetitionsWEB/controlador?parametro=cadastraModalidade" method="post">
            <input type="hidden" name="id" value="<%= m.getIdModalidades()%>">

            <label>Nome:</label>&nbsp;
            <input type="text" name="nome" value="<%= m.getNome()%>"> &nbsp;
            <input type="checkbox" name="ativo" <%=m.isAtivo() ? "checked" : ""%>> Ativo &nbsp;
            <input type="submit" value="Salvar">

        </form>

        <BR>
        <%@include file ="listaModalidades.jsp" %>

    </body>
</html>