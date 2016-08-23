<%-- 
    Document   : cadastroModalidades
    Created on : 15/08/2016, 21:29:22
    Author     : Fernanda Finkler
--%>

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

        <form action="/CompetitionsWEB/controlador?acao=cadastraModalidade" method="post">
            <label>Nome:&nbsp;</label>
            <input type="text" name="nome"> &nbsp;
            <input type="submit" value="Salvar">
        </form>
        <BR>
        <%@include file ="listaModalidades.jsp" %>

    </body>
</html>