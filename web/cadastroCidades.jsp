<%-- 
    Document   : cadastroCidades
    Created on : 18/09/2016, 21:03:59
    Author     : Fernanda Finkler
--%>

<%@page import="entidades.Cidade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- importacao do arquivo de validacao -->
        <script src="boots/js/jquery.min.js" type="text/javascript"></script>
        <script src="boots/js/validacao51.js" type="text/javascript"></script>
        <title>TMT</title>
        
        <script type="text/javascript">
            function popularComboEstados()
            {
                var idpais = $(this).attr("value");
                
                $.ajax({
                        type: "GET",
                        url: "/CompetitionsWEB/controlador?parametro=buscarEstados",
                        data: idpais
                    }).done(function (retorno) {
                        var resultado = $.trim(retorno);
                        if (resultado == "erro") {
                            //alert("Deu erro:" + retorno);
                             bootbox.alert("Deu erro!");
                        } else {
//                            bootbox.alert("Deu certo!");
                            swal("Deu certo!")
                            //alert("Deu certo: " + retorno);
                            $('#formLugar').each(function () {
                                this.reset();
                            });
                            $('#nomeLugar').focus();
                        }
                    });
            }
        </script>
        
    </head>
    <body>
        <jsp:include page='cadastroLocais.jsp'>
            <jsp:param name="active" value="c"/>
        </jsp:include>
        <BR>

        <%
            Cidade c = (Cidade) request.getAttribute("cidade");
            if (c == null) {
                c = new Cidade();
            }
            int paisSelecionado = 0;
        %>

        <form action="/CompetitionsWEB/controlador?parametro=cadastraCidade" method="post" name="dados" onSubmit="return validardadosCidade();">
            <input type="hidden" name="id" value="<%= c.getIdcidade()%>">

            <label>Nome:</label>&nbsp;
            <input type="text" name="nome" value="<%= c.getNome()%>"> &nbsp;

            <label>País:</label>&nbsp;
            <select name="pais" size="1" id="pais" >
                <option value="0">Selecione</option>
                <%
                    ArrayList<Object> paises = new PaisDAO().consultarTodosAtivos();
                    
                    for (int i = 0; i < paises.size(); i++) {
                        Pais pais = (Pais) paises.get(i);
                        if (c.getIdcidade() == 0) {
                %>
                <option onclick="popularComboEstados();" value="<%=pais.getIdpais()%>"><%=pais.getNome()%></option>
                <%
                        } else {
                            Estado e = (Estado) new EstadoDAO().consultarId(c.getEstado());
                            paisSelecionado = e.getPais();
                %>
                <option onclick="popularComboEstados();" value="<%=pais.getIdpais()%>" <%=e.getPais() == pais.getIdpais() ? "selected" : ""%>><%=pais.getNome()%></option>
                <%
                        }
                    }
                %>
            </select> &nbsp;

            <label>Estado:</label>&nbsp;
            <select name="estado" size="1" id="estado">
                <option value="0">Selecione</option>
                <%
                    ArrayList<Object> estados = new EstadoDAO().consultarTodosAtivos(paisSelecionado);
                    for (int i = 0; i < estados.size(); i++) {
                        Estado estado = (Estado) estados.get(i);

                %>
                <option value="<%=estado.getIdestado()%>" <%=c.getEstado() == estado.getIdestado() ? "selected" : ""%>><%=estado.getNome()%></option>
                <%
                    }
                %>
            </select> &nbsp;

            <input type="checkbox" name="ativo" <%=c.isAtivo() ? "checked" : ""%>> Ativo &nbsp;
            <input type="submit" name="Submit" class="formobjects" value="Salvar">

            <!--<input type="reset" name="Reset" class="formobjects" value="Limpar">-->
        </form>

        <BR>
        <%@include file ="listaCidades.jsp" %>

    </body>
</html>