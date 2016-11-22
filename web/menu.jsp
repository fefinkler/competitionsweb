<%-- 
    Document   : menu
    Created on : 22/08/2016, 20:10:18
    Author     : Fernanda Finkler
--%>

<%@page import="daos.CompeticaoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset=UTF-8">
        <!--<link href="css/menu.css" rel="stylesheet" type="text/css" />-->
        <!-- Bootstrap core CSS -->
        <link href="boots/css/bootstrap.min.css" rel="stylesheet">
        <link href="boots/css/sweetalert.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this template -->
        <link href="boots/css/signin.css" rel="stylesheet">
        <link href="boots/css/navbar-fixed-top.css" rel="stylesheet" type="text/css"/>
        <script src="boots/js/jquery.min.js" type="text/javascript"></script>
        <script src="boots/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="boots/js/sweetalert.js" type="text/javascript"></script>
        <title>CompetitionsWEB</title>
    </head>
    <body>

        <%
            // verificando se tem um atributo login na sessao
            // se houver vai continuar e mostrar a pagina
            if (session.getAttribute("usuarioLogado") != null) {
        %>

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
                        <li class="active"><a href="menuCompeticoes.jsp">Início</a></li>
                        
                        
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cadastros <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="menuCompeticoes.jsp?active=l">Competições</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="cadastroAtletas.jsp?active=l">Atletas</a></li>
                                <li><a href="cadastroLocais.jsp?active=c">Locais</a></li>
                                <li><a href="cadastroModalidades.jsp">Modalidades</a></li>
                                <li><a href="cadastroTiposDespesas.jsp">Tipos Despesas</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Relatórios<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="cadastroAtletas.jsp?active=l">Currículo do Atleta</a></li>
                                <li><a href="listaModalidades.jsp">Dados Inscrição</a></li>
                                <li><a href="relatorioHistorico.jsp">Histórico da Equipe</a></li>
                            </ul>
                        </li>                        
                        <li><a href="/CompetitionsWEB/controlador?parametro=logout">Sair</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
        
        

        <%
            // se não existir um login na sessao,
            // vai enviar para a página de login novamente
        } else {
        %>
        <jsp:forward page="index.jsp"></jsp:forward>
        <%
            }
        %>

    </body>
</html>
