<%-- 
    Document   : cadastroTiposDespesas
    Created on : 31/08/2016, 13:03:35
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
        <h1>Cadastro de Locais</h1>

        <form action="/CompetitionsWEB/controlador?parametro=cadastraLocal" method="post">
            <label>Nome:&nbsp;</label>
            <input type="text" name="nome">
            <br>
            <label>Sigla:&nbsp;</label>
            <input type="text" name="sigla">
            <br>
            <label>Ativo:&nbsp;</label>
            <%--<input type="text" name="ativo">--%>
            <br>
            <input type="submit" value="Salvar">
        </form>

    </body>
</html>
