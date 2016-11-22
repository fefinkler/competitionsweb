<%-- 
    Document   : listaCompeticoes
    Created on : 23/10/2016, 17:32:24
    Author     : Fernanda Finkler
--%>

<%@page import="daos.CidadeDAO"%>
<%@page import="entidades.Cidade"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="daos.CompeticaoDAO"%>
<%@page import="entidades.Competicao"%>
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
            <table id="table-competitions" class="table table-striped">
                <thead class="header">
                <th style="width: 20px">ID</th>
                <th style="width: 150px">Data</th>
                <th style="width: 230px">Nome</th>
                <th>Cidade</th>
                <th>Situação</th>
                <th>Editar</th>
                <th>Excluir</th>
                </thead>
                <tfoot class="header">
                <th style="width: 20px">ID</th>
                <th style="width: 150px">Data</th>
                <th style="width: 230px">Nome</th>
                <th>Cidade</th>
                <th>Situação</th>
                <th>Editar</th>
                <th>Excluir</th>
                </tfoot>
                <tbody>
                    <%
                        ArrayList<Object> competicoes = new CompeticaoDAO().consultarTodos();
                        for (int i = 0; i < competicoes.size(); i++) {
                            Competicao competicao = (Competicao) competicoes.get(i);
                            Cidade cidadeCompet = (Cidade) new CidadeDAO().consultarId(competicao.getCidade());
                            String status = "";
                            if (competicao.getStatus() == 'p') {
                                status = "Programada";
                            } else if (competicao.getStatus() == 'r') {
                                status = "Realizada";
                            } else if (competicao.getStatus() == 's') {
                                status = "Suspensa";
                            }

                            String dia;
                            if (competicao.getDia() != null) {
                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                dia = df.format(competicao.getDia());
                            } else {
                                dia = "n/d";
                            }


                    %>
                    <tr>
                        <td><%=competicao.getId()%></td>
                        <td><%= dia%></td>
                        <td><%=competicao.getNome()%></td>
                        <td><%=cidadeCompet.getNome()%></td>
                        <td><%= status%></td>
                        <td><a href="/CompetitionsWEB/controlador?parametro=editarCompeticao&id=<%=competicao.getId()%>">Editar</a></td>
                        <td><a OnClick="return confirm('Deseja realmente excluir este registro?')" href="/CompetitionsWEB/controlador?parametro=excluirCompeticao&id=<%=competicao.getId()%>">Excluir</a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

    </body>
    <script type="text/javascript">

        $('#table-competitions tfoot th').each(function () {
            var title = $(this).text();
            $(this).html('<input type="text" style="width:100%;" class="form-control" placeholder="' + title + '" />');
        });

        var table = $('#table-competitions').DataTable({
            columnDefs: [],
            processing: true,
            dom: 'Bfrtip',
            caption: {p: 'dasd'},
            buttons: [
                {
                    extend: 'pdf',
                    text: '<i class="fa fa-file-pdf-o"></i>&nbsp;PDF',
                    title: 'Relatório Competicões',
                    className: 'btn btn-default'
                },
                {
                    extend: 'excel',
                    text: '<i class="fa fa-file-excel-o"></i>&nbsp;Excel',
                    title: 'Relatório Competicões',
                    className: 'btn btn-default'
                }
//                    {
//                        extend: 'print',
//                        text: '<i class="fa fa-print"></i>&nbsp;Print',
//                        title: 'Relatorio Competicoes',
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

        $('.dt-buttons').append('<input type="button" name="Novo" class="btn btn-primary" value="Nova Competição" style="float:right" onClick="novaCompeticao()">');

        function novaCompeticao() {
            $ ('#dadosCompeticao').find('input, select').not('input[type=submit], input[type=button]').val('');
            $ ('#dadosCompeticao').find('#programada').click();
            //window.location = "/CompetitionsWEB/controlador?parametro=editarCompeticao&id=2";
        }
    </script>
</html>
