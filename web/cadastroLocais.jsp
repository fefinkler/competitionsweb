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
        
            String classCidades = "active";
            String classEstados = "";
            String classPaises = "";
            
            String classActive = (String)  request.getParameter("active");
            
            if (classActive.equalsIgnoreCase("c")){
                classCidades = "active";
            } else if (classActive.equalsIgnoreCase("e")){
                classEstados = "active";
            } else if (classActive.equalsIgnoreCase("p")){
                classPaises = "active";
            } else {
                classCidades = "active";
            }
        %>
        <ul class="nav nav-tabs">
            <li role="presentation" class="<%= classCidades %>"><a href="cadastroCidades.jsp">Cidades</a></li>
            <li role="presentation" class="<%= classEstados %>"><a href="cadastroEstados.jsp">Estados</a></li>
            <li role="presentation" class="<%= classPaises %>"><a href="cadastroPaises.jsp">Países</a></li>
        </ul>
  
    </body>
</html>
