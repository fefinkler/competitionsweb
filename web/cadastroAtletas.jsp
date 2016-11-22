<%-- 
    Document   : cadastroAtletas
    Created on : 15/11/2016, 16:36:44
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.Atleta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
        <!-- Bootstrap core CSS -->
        <link href="boots/css/bootstrap.min.css" rel="stylesheet">
        <link href="boots/css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this template -->
        <link href="boots/css/signin.css" rel="stylesheet">
        <link href="boots/css/navbar-fixed-top.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet"  type="text/css" href="boots/js/datatables.net-bs/css/dataTables.bootstrap.css"/>
        
        <script type="text/javascript" src="boots/js/jquery.min.js"></script>  
        <script type="text/javascript" src="boots/js/jszip/dist/jszip.min.js"></script>        
        <script type="text/javascript" src="boots/js/pdfmake/build/pdfmake.js"></script>        
        <script type="text/javascript" src="boots/js/pdfmake/build/vfs_fonts.js"></script>
        <script type="text/javascript" src="boots/js/datatables.net/js/jquery.dataTables.js"></script>        
        <script type="text/javascript" src="boots/js/datatables.net-bs/js/dataTables.bootstrap.js"></script>        
        <script type="text/javascript" src="boots/js/datatables.net-buttons/js/dataTables.buttons.js"></script>
        <%@include file ="menu.jsp" %>
        
        <title>TMT</title>
    </head>
    <body>
        <br>
        <h1>Atletas</h1>
        <%
            Atleta atleta = (Atleta) request.getAttribute("atleta");
            if (atleta == null) {
                atleta = new Atleta();
            }
            String classListaA = "";
            String classCadastroA = "";
            String classListaA2 = "";
            String classCadastroA2 = "";
            
            String classActive = (String)  request.getParameter("active");
            
            if (classActive != null && classActive.equalsIgnoreCase("c")){
                classCadastroA = "active";
                classCadastroA2 = " in active";
            } else {
                classListaA = "active";
                classListaA2 = "in active";
            } 
        %>
       <div class ="container">
            <ul class="nav nav-tabs">
                <li class="<%= classListaA %>"><a data-toggle="tab" href="#listaAtletas">Lista</a></li>
                <li class="<%= classCadastroA %>"><a data-toggle="tab" href="#cadastroAtletas">Cadastro</a></li>
            </ul>
            <div class="tab-content">
                <div id="listaAtletas" class="tab-pane fade <%= classListaA2 %>"><%@include file ="listaAtletas.jsp" %></div> <%-- --%>
                <div id="cadastroAtletas" class="tab-pane fade <%= classCadastroA2 %>"><%@include file ="infosAtletas.jsp" %></div> <%--  --%>
            </div>
        </div>
    </body>
</html>
