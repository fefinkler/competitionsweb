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
        <title>Competitions WEB</title>
    </head>
    
        
    <body>
        <h1>Bem-vindo ao Team Manager Tool</h1>
        
        <form method="post" actions="/CompetitionsWEB/acao?parametro=login">
            <label> Usuário</label>
            <input type="text" name="login">
            <br>
            <label>Senha</label>
            <input type="password" name="senha">
            <br>
            <input type="submit" value="Acessar">
        </form>
        
        
        
    </body>
</html>
