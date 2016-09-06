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
        <h1>Cadastro de Locais</h1>

        <%
            String active = (String)  request.getParameter("a");
        %>
        <ul class="nav nav-tabs">
            <li role="presentation" class="<%= activM %>"><a href="cadastroModalidades.jsp">Cidades</a></li>
            <li role="presentation"><a href="#">Estados</a></li>
            <li role="presentation"><a href="cadastroPaises.jsp">Países</a></li>
        </ul>
  
    </body>
</html>
