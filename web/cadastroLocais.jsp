<%-- 
    Document   : cadastroLocais
    Created on : 22/08/2016, 22:08:48
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
            <input type="text" name="nome">
            <br>
            <input type="submit" value="Salvar">

        </form>

    </body>
</html>