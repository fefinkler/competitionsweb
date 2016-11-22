<%-- 
    Document   : relatorioCurriculo
    Created on : 22/11/2016, 12:47:05
    Author     : Fernanda Finkler
--%>

<%@page import="daos.AtletaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int idAtletaCurriculo = Integer.parseInt(request.getParameter("idAtletaCurriculo"));
            
            byte[] bytes = new AtletaDAO().gerarRelatorioCurriculo(idAtletaCurriculo);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
