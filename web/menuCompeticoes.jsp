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
        
        <title>TMT</title>

        <script>
            function showTab(name)
            {
                $('.tab-content').children().map(function (index, e)
                {
                    var ele = $(e);
                    if (ele.attr('id') === name) {
                        ele.attr('class', 'tab-pane active');
                    } else {
                        ele.attr('class', 'tab-pane');
                    }

                });
            }
            
        </script>
    </head>
    <body>
        <%@include file ="menu.jsp" %>
        <br>
        <h1>Cadastro de Competicoes</h1>


        <div class ="container">
            <ul class="nav nav-tabs">
                <li onClick="showTab('TabLista')"><a data-toggle="tab">Lista</a></li>
                <li onClick="showTab('TabCadastro')"><a data-toggle="tab">Cadastro</a></li>
            </ul>

            <div class="tab-content">
                
                <div id="TabLista" class="tab-pane"><%@include file ="listaCompeticoes.jsp" %></div>
                <div id="TabCadastro" class="tab-pane"><%@include file ="cadastroCompeticoes.jsp" %></div>
            </div>

        </div>
    </body>
</html>
