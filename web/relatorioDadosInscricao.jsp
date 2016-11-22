<%-- 
    Document   : relatorioDadosInscricao
    Created on : 22/11/2016, 13:17:40
    Author     : Fernanda Finkler
--%>

<%@page import="daos.CompeticaoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int idCompEquipe = Integer.parseInt(request.getParameter("idCompEquipe"));
            
            byte[] bytes = new CompeticaoDAO().gerarRelatorioDadosInscricao(idCompEquipe);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
