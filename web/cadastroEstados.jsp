<%-- 
    Document   : cadastroEstados
    Created on : 12/09/2016, 20:03:20
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.Estado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importacao do arquivo de validacao -->
        <script src="boots/js/validacao51.js" type="text/javascript"></script>
        <title>TMT</title>
    </head>
    <body>
        <jsp:include page='cadastroLocais.jsp'>
            <jsp:param name="active" value="e"/>
        </jsp:include>
        <BR>

        <%
            Estado e = (Estado) request.getAttribute("estado");
            if (e == null) {
                e = new Estado();
            }
        %>

        <form action="/CompetitionsWEB/controlador?parametro=cadastraEstado" method="post" name="dados" onSubmit="return validardadosEstado();">
            <input type="hidden" name="id" value="<%= e.getIdestado()%>">

            <label>Nome:</label>&nbsp;
            <input type="text" name="nome" value="<%= e.getNome()%>"> &nbsp;
            <label>Sigla:</label>&nbsp;
            <input type="text" name="siglaEstado" value="<%= e.getSigla()%>"> &nbsp;

            <label>País:</label>&nbsp;
            <select name="pais" size="1" id="pais">
                <option value="0">Selecione</option>
                <%
                    ArrayList<Object> paises = new PaisDAO().consultarTodosAtivos();
                    for (int i = 0; i < paises.size(); i++) {
                        Pais pais = (Pais) paises.get(i);

                %>
                <option value="<%=pais.getIdpais()%>" <%=e.getPais()==pais.getIdpais() ? "selected" : ""%>><%=pais.getNome()%></option>
                <%
                    }
                %>
            </select> &nbsp;

            <input type="checkbox" name="ativo" <%=e.isAtivo() ? "checked" : ""%>> Ativo &nbsp;
            <input type="submit" name="Submit" class="formobjects" value="Salvar">

            <!--<input type="reset" name="Reset" class="formobjects" value="Limpar">-->
        </form>

        <BR>
        <%@include file ="listaEstados.jsp" %>

    </body>
</html>
