<%-- 
    Document   : menu
    Created on : 22/08/2016, 20:10:18
    Author     : Fernanda Finkler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
        <!--<link href="css/menu.css" rel="stylesheet" type="text/css" />-->
        <!-- Bootstrap core CSS -->
        <link href="boots/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="boots/css/signin.css" rel="stylesheet">
        <link href="boots/css/navbar-fixed-top.css" rel="stylesheet" type="text/css"/>
        <title>CompetitionsWEB</title>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Team Manager Tool</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="menu.jsp">Início</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cadastros <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="cadastroModalidades.jsp">Modalidades</a></li>
                                <li><a href="#">Tipos Despesas</a></li>
                                <li><a href="#">Lugares</a></li>
                                <li role="separator" class="divider"></li>
                                <li class="dropdown-header">Nav header</li>
                                <li><a href="#">Separated link</a></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Relatórios<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="listaModalidades.jsp">Lista Modalidades</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li class="dropdown-header">Nav header</li>
                                <li><a href="#">Separated link</a></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>                        
                        <li><a href="#about">Sobre</a></li>
                        <li><a href="#contact">xxx</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="../navbar/">Default</a></li>
                        <li><a href="../navbar-static-top/">Static top</a></li>
                        <li class="active"><a href="./">Fixed top <span class="sr-only">(current)</span></a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <script src="boots/js/jquery.min.js" type="text/javascript"></script>
        <script src="boots/js/bootstrap.min.js" type="text/javascript"></script>

    </body>
</html>
