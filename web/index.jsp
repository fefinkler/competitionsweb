<%-- 
    Document   : index
    Created on : 15/08/2016, 19:51:33
    Author     : Fernanda Finkler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link href="css/login.css" rel="stylesheet" type="text/css" />-->
        <link href="boots/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="boots/css/signin.css" rel="stylesheet" type="text/css" />

        <title>Competitions WEB</title>
    </head>

        <div class="container">
            <form method="post" action="/CompetitionsWEB/controlador?parametro=login" class="form-signin">
                <h2 class="form-signin-heading">Team Manager Tool</h2>
                <label> Usuário</label>
                <input type="text" name="login" class="form-control">
                <br>
                <label>Senha</label>
                <input type="password" name="senha" class="form-control">
                <br>
                <input type="submit" value="Acessar" class="btn btn-lg btn-primary btn-block">
            </form>
        </div>
</html>
