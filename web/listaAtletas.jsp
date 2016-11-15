<%-- 
    Document   : listaAtletas
    Created on : 15/11/2016, 17:02:52
    Author     : Fernanda Finkler
--%>

<%@page import="daos.CidadeDAO"%>
<%@page import="entidades.Cidade"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entidades.Atleta"%>
<%@page import="daos.AtletaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script type="text/javascript" src="boots/js/jquery.min.js"></script>        
        <script type="text/javascript" src="boots/js/datatables.net/js/jquery.dataTables.js"></script>        
        <script type="text/javascript" src="boots/js/datatables.net-bs/js/dataTables.bootstrap.js"></script> 
        <script type="text/javascript" src="boots/js/jszip/dist/jszip.min.js"></script>        
        <script type="text/javascript" src="boots/js/pdfmake/build/pdfmake.js"></script>        
        <script type="text/javascript" src="boots/js/pdfmake/build/vfs_fonts.js"></script>
        <script type="text/javascript" src="boots/js/datatables.net-buttons/js/dataTables.buttons.js"></script>
        <script type="text/javascript" src="boots/js/datatables.net-buttons/js/buttons.flash.js"></script>        
        <script type="text/javascript" src="boots/js/datatables.net-buttons/js/buttons.colVis.js"></script>        
        <script type="text/javascript" src="boots/js/datatables.net-buttons/js/buttons.html5.js"></script>        
        <script type="text/javascript" src="boots/js/datatables.net-buttons/js/buttons.print.js"></script>
    </head>
    <body>
        <div class="table-responsive">
            <table id="table-atletas" class="table table-striped">
                <thead class="header">
                <th>ID</th>
                <th>Nome</th>
                <th>Data Nascimento</th>
                <th>Cidade</th>
                <th>Telefone</th>
                <th>Situação</th>
                <th>Editar</th>
                <th>Excluir</th>
                </thead>
                <tfoot class="header">
                <th>ID</th>
                <th>Nome</th>
                <th>Data Nascimento</th>
                <th>Cidade</th>
                <th>Telefone</th>
                <th>Situação</th>
                <th>Editar</th>
                <th>Excluir</th>
                </tfoot>
                <tbody>
                    <%
                        ArrayList<Object> atletas = new AtletaDAO().consultarTodos();
                        for (int i = 0; i < atletas.size(); i++) {
                            Atleta atletaL = (Atleta) atletas.get(i);
                            Cidade cidadeAtleta = (Cidade) new CidadeDAO().consultarId(atletaL.getCidade());

                            String status = "";
                            if (atletaL.isAtivo()) {
                                status = "Ativo";
                            } else {
                                status = "Inativo";
                            }

                            String dia;
                            if (atletaL.getDtnasc() != null) {
                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                dia = df.format(atletaL.getDtnasc());
                            } else {
                                dia = "n/d";
                            }


                    %>
                    <tr>
                        <td><%=atletaL.getIdatleta()%></td>
                        <td><%=atletaL.getNome()%></td>
                        <td><%= dia%></td>
                        <td><%=cidadeAtleta.getNome()%></td>
                        <td><%=atletaL.getTelefone()%></td>
                        <td><%= status%></td>
                        <td><a href="/CompetitionsWEB/controlador?parametro=editarAtleta&id=<%=atletaL.getIdatleta()%>">Editar</a></td>
                        <%
                            String msg = "";
                            String parametro = "";
                            if (new AtletaDAO().possuiVinculos(atletaL.getIdatleta())) {
                                msg = "Este registro possui vínculos, não é possível excluir. Deseja inativá-lo?";
                                parametro = "inativar";
                            } else {
                                msg = "Deseja realmente excluir este registro?";
                                parametro = "excluir";
                            }
                        %>
                        <td><a OnClick="return confirm('<%=msg%>')" href="/CompetitionsWEB/controlador?parametro=<%=parametro%>Atleta&id=<%=atletaL.getIdatleta()%>">Excluir</a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

    </body>
    <script type="text/javascript">

        $('#table-atletas tfoot th').each(function () {
            var title = $(this).text();
            $(this).html('<input type="text" style="width:100%;" class="form-control" placeholder="' + title + '" />');
        });

        var table = $('#table-atletas').DataTable({
            columnDefs: [],
            processing: true,
            dom: 'Bfrtip',
            caption: {p: 'dasd'},
            buttons: [
                {
                    extend: 'pdf',
                    text: '<i class="fa fa-file-pdf-o"></i>&nbsp;PDF',
                    title: 'Relatório Atletas',
                    className: 'btn btn-default'
                },
                {
                    extend: 'excel',
                    text: '<i class="fa fa-file-excel-o"></i>&nbsp;Excel',
                    title: 'Relatório Atletas',
                    className: 'btn btn-default'
                }
//                    {
//                        extend: 'print',
//                        text: '<i class="fa fa-print"></i>&nbsp;Print',
//                        title: 'Relatório Atletas',
//                        className: 'btn btn-default'
//                    } 
            ]
        });

        table.columns().every(function () {
            var that = this;
            $('input', this.footer()).on('keyup change', function () {
                if (that.search() !== this.value) {
                    that.search(this.value).draw();
                }
            });
        });

        $(".dataTables_filter").remove();

        $('.dt-buttons').append('<input type="button" name="NovoAtleta" class="btn btn-primary" value="Novo Atleta" style="float:right" onClick="novoAtleta()">');

        function novoAtleta() {
            $ ('#dadosAtleta').find('input, select').not('input[type=submit], input[type=button]').val('');
            //$ ('#dadosAtleta').find('#programada').click();
            //window.location = "/CompetitionsWEB/controlador?parametro=editarCompeticao&id=2";
        }
    </script>
</html>
