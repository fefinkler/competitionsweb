<%-- 
    Document   : infosAtletas
    Created on : 15/11/2016, 18:21:45
    Author     : Fernanda Finkler
--%>

<%@page import="daos.EstadoDAO"%>
<%@page import="entidades.Estado"%>
<%@page import="daos.CidadeDAO"%>
<%@page import="entidades.Cidade"%>
<%@page import="entidades.Atleta"%>
<%@page import="entidades.Atleta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <!-- importacao do arquivo de validacao -->
        <script src="boots/js/jquery.min.js" type="text/javascript"></script>
        <script src="boots/js/validacao51.js" type="text/javascript"></script>
        <title>TMT</title>

        <%
            Atleta atl = (Atleta) request.getAttribute("atleta");

            int paisSelecionado = 0;
            int estadoSelecionado = 0;
            int cidadeSelecionada = 0;

            if (atl == null) {
                atl = new Atleta();
            } else {
                cidadeSelecionada = atl.getCidade();
                estadoSelecionado = ((Cidade) new CidadeDAO().consultarId(cidadeSelecionada)).getEstado();
                paisSelecionado = ((Estado) new EstadoDAO().consultarId(estadoSelecionado)).getPais();

            }
        %>
        <script lang="text/javascript">

            // funcao associada a mudanca de valor na combo Estados
            $(document).ready(function () {

                function carregaPaisAtl( )
                {
                    $.ajax({
                        url: "/CompetitionsWEB/controlador?parametro=getPais",
                        cache: false,
                        success: function (html) {
                            $("#combop").empty();
                            $("#combop").append(html);

                            $('#comboPais').on("change", function () {
                                carregaEstadosAtl();
                            });

                            $('#comboPais').find("option[value='" + <%=paisSelecionado%> + "']").attr('selected', 'true');
                        }
                    });
                }

                function carregaEstadosAtl( )
                {
                    var idPais = $("#comboPais").val() || <%=paisSelecionado%> || 0;
                    $.ajax({
                        url: "/CompetitionsWEB/controlador?parametro=getEstado&idPais=" + idPais,
                        cache: false,
                        success: function (html) {
                            $("#comboe").empty();
                            $("#comboe").append(html);

                            $('#estado').on("change", function () {
                                carregaCidadesAtl();
                            });


                            $('#estado').find("option[value='" + <%=estadoSelecionado%> + "']").attr('selected', 'true');
                        }
                    });
                }

                function carregaCidadesAtl( )
                {
                    var idEstado = $("#estado").val() || <%=estadoSelecionado%> || 0;
                    $.ajax({
                        url: "/CompetitionsWEB/controlador?parametro=getCidade&idEstado=" + idEstado,
                        cache: false,
                        success: function (html) {
                            $("#comboc").empty();
                            $("#comboc").append(html);

                            $('#comboc').find("option[value='" + <%=cidadeSelecionada%> + "']").attr('selected', 'true');
                        }
                    });
                }

                loadFormAtl = function (  )
                {
                    carregaPaisAtl();
                    carregaEstadosAtl();
                    carregaCidadesAtl();
                }

                loadFormAtl();
            });
        </script>
    </head>
    <body>
        <BR>
        <form class="" id="dadosAtleta" action="/CompetitionsWEB/controlador?parametro=cadastraAtleta" method="post" name="dados">
            <div class="container tab-pane form-horizontal">

                <input type="hidden" name="id" id="idAtleta" value="<%= atl.getIdatleta()%>">

                <div class="form-group">
                    <label for="nome">Nome:</label>&nbsp;
                    <input type="text" class="form-control" name="nome" id="nome" value="<%= atl.getNome()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Data Nascimento:</label>&nbsp;
                    <input type="date" class="form-control" name="datanasc" id="datanasc" value="<%= atl.getDtnasc()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Situação:</label>&nbsp;
                    <%
                        char status = 't';
                        if (! atl.isAtivo()) {
                            status = 'f';
                        }
                    %>
                    <input type="radio" name="status" id="ativo" value="t" <%=status == 't' ? "checked" : ""%>> Ativo &nbsp; 
                    <input type="radio" name="status" id="inativo" value="f" <%=status == 'f' ? "checked" : ""%>> Inativo &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Alergias:</label>&nbsp;
                    <input type="text" class="form-control" name="alergias" id="alergias" value="<%= atl.getAlergias()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">CPF:</label>&nbsp;
                    <input type="text" class="form-control" name="cpf" id="cpf" value="<%= atl.getCpf()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">RG:</label>&nbsp;
                    <input type="text" class="form-control" name="rg" id="rg" value="<%= atl.getRg()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Tipo Sanguíneo:</label>&nbsp;
                    <input type="text" class="form-control" name="tiposang" id="tiposang" value="<%= atl.getTipoSang()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Telefone:</label>&nbsp;
                    <input type="text" class="form-control" name="telefone" id="telefone" value="<%= atl.getTelefone()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">E-mail:</label>&nbsp;
                    <input type="text" class="form-control" name="email" id="email" value="<%= atl.getEmail()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Endereço:</label>&nbsp;
                    <input type="text" class="form-control" name="endereco" id="endereco" value="<%= atl.getEndereco()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">CEP:</label>&nbsp;
                    <input type="text" class="form-control" name="cep" id="cep" value="<%= atl.getCep()%>"> &nbsp;
                </div>
                <div class="form-group" id="combop"></div>
                <div class="form-group" id="comboe"></div>
                <div class="form-group" id="comboc"></div>
                <div class="form-group">
                    <label for="nome">Parente/Amigo:</label>&nbsp;
                    <input type="text" class="form-control" name="parente" id="parente" value="<%= atl.getParente()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Contato:</label>&nbsp;
                    <input type="text" class="form-control" name="telefonep" id="telefonep" value="<%= atl.getTelefoneP()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Observações:</label>&nbsp;
                    <textarea row="20" class="form-control" name="obs" id="obs" value="<%= atl.getObservacoes()%>"></textarea> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Login:</label>&nbsp;
                    <input type="text" class="form-control" name="login" id="login" value="<%= atl.getLogin()%>"> &nbsp;
                </div>
                <div class="form-group">
                    <label for="nome">Senha:</label>&nbsp;
                    <input type="text" class="form-control" name="senha" id="senha" value="<%= atl.getSenha()%>"> &nbsp;
                </div>
            </div>
            <div class="form-group">
                <input type="submit" name="Submit" class="btn btn-primary" value="Salvar">
            </div>   
        </form>
    </body>
</html>
