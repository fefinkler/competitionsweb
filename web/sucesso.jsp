<%-- 
    Document   : sucesso
    Created on : 22/08/2016, 19:48:05
    Author     : Fernanda Finkler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sucesso!</h1>
        
        <%
            String pagina = (String) request.getAttribute("paginaretorno");
        %>
            
        <a href='<%=pagina %>'>Volta para o cadastro</a>
        <br>
        <a href='index.jsp'>Volta para o Início</a>
    </body>
</html>
