<%-- 
    Document   : cadastroCompeticoes
    Created on : 23/10/2016, 17:31:51
    Author     : Fernanda Finkler
--%>

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
        <h1>Cadastro de Competições</h1>
        <%
            Competicao compet = (Competicao) request.getAttribute("competicao");
            if (compet == null) {
                compet = new Competicao();
            }
            String classLista = "";
            String classCadastro = "";
            String classLista2 = "";
            String classCadastro2 = "";
            
            String classActive = (String)  request.getParameter("active");
            
            if (classActive != null && classActive.equalsIgnoreCase("c")){
                classCadastro = "active";
                classCadastro2 = " in active";
            } else {
                classLista = "active";
                classLista2 = "in active";
            } 
        %>
        <div class ="container">
            <ul class="nav nav-tabs">
                <li class="<%= classLista %>"><a data-toggle="tab" href="#lista">Lista</a></li>
                <li class="<%= classCadastro %>"><a data-toggle="tab" href="#cadastro">Cadastro</a></li>
            </ul>
            <div class="tab-content">
                <div id="lista" class="tab-pane fade <%= classLista2 %>"><%@include file ="listaCompeticoes.jsp" %></div>
                <div id="cadastro" class="tab-pane fade <%= classCadastro2 %>"><%@include file ="cadastroCompeticoes.jsp" %></div>
            </div>
        </div>
    </body>
</html>
