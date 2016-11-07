<%-- 
    Document   : cadastroPaises
    Created on : 05/09/2016, 21:52:21
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.Pais"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importacao do arquivo de validacao -->
        <script src="boots/js/validacao51.js" type="text/javascript"></script>
        <title>TMT</title>
    </head>
    <body>
        <jsp:include page='cadastroLocais.jsp'>
            <jsp:param name="active" value="p"></jsp:param>
        </jsp:include>
        <BR>

        <%
            Pais p = (Pais) request.getAttribute("pais");
            if (p == null) {
                p = new Pais();
            }
        %>

        <form action="/CompetitionsWEB/controlador?parametro=cadastraPais" method="post" name="dados" onSubmit="return validardadosPais();">
            <input type="hidden" name="id" value="<%= p.getIdpais()%>">

            <label>Nome:</label>&nbsp;
            <input type="text" name="nome" value="<%= p.getNome()%>"> &nbsp;
            <label>Sigla:</label>&nbsp;
            <input type="text" name="siglaPais" value="<%= p.getSigla()%>"> &nbsp;
            <input type="checkbox" name="ativo" <%=p.isAtivo() ? "checked" : ""%>> Ativo &nbsp;
            <input type="submit" name="Submit" class="formobjects" value="Salvar">

            <!--<input type="reset" name="Reset" class="formobjects" value="Limpar">-->
        </form>

        <BR>
        <%@include file ="listaPaises.jsp" %>

    </body>
</html>
