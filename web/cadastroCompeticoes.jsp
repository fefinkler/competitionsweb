<%-- 
    Document   : cadastroCompeticoes
    Created on : 23/10/2016, 18:47:04
    Author     : Fernanda Finkler
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importacao do arquivo de validacao -->
        <script src="boots/js/jquery.min.js" type="text/javascript"></script>
        <script src="boots/js/validacao51.js" type="text/javascript"></script>
        <title>TMT</title>
        
        <script>
            function showTabs(name)
            {
                $( '#cad' ).children().map( function( index, e ) 
                { 
                    var ele = $( e ); 
                    if(ele.attr( 'id' ) === name){
                        ele.attr('class', 'tab-pane active');
                    } else {
                        ele.attr('class', 'tab-pane');
                    }
                        
                } );
            }         
        </script>
    </head>
    <body>        
        <div class ="container">
        <ul class="nav nav-tabs">
            <li onClick="showTabs('TabInfos')"><a data-toggle="tab">Geral</a></li>
            <li onClick="showTabs('TabPercurso')"><a data-toggle="tab">Percurso</a></li>
            <li onClick="showTabs('TabEquipe')"><a data-toggle="tab">Equipe</a></li>
            <li onClick="showTabs('TabDespesas')"><a data-toggle="tab">Despesas</a></li>
        </ul>
  
            <form class="" action="/CompetitionsWEB/controlador?parametro=cadastraCompeticao" method="post" name="dados">
                <div class="tab-content" id="cad">
                    <div id="TabInfos" class="tab-pane"><%@include file ="infosCompeticoes.jsp" %></div>
                    <div id="TabPercurso" class="tab-pane"><%@include file ="percurso.jsp" %></div>
                    <div id="TabEquipe" class="tab-pane"><%@include file ="equipe.jsp" %></div>
                    <div id="TabDespesas" class="tab-pane"><%@include file ="despesas.jsp" %></div>
                </div>
                
                <div class="form-group">
                    <input type="submit" name="Submit" class="formobjects" value="Salvar">
                </div>   
            </form>
        
        </div>
    </body>
</html>
