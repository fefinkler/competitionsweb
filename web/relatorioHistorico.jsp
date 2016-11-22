<%-- 
    Document   : relatorioHistorico
    Created on : 21/11/2016, 22:20:03
    Author     : Fernanda Finkler
--%>

<%@page import="daos.CompeticaoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="apoio.ConexaoBD"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TMT</title>
    </head>
    <body>
        <%
            byte[] bytes = new CompeticaoDAO().gerarRelatorioHistorico();

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        %>
    </body>
</html>
