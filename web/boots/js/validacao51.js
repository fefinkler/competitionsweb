/* ---------------------------------------------------------------
  CABECALHO PARA COMENTARIOS DO ARQUIVO DE SCRIPT
  Data: dd/mm/aaaa
  Autor: Juca Bala

  Variacao do arquivo validacao3.js:
  - retirado return false dos IFs
  - retiradas as mensagens de erros dos IFs
  - concatenacao de uma unica mensagem de erro atraves da variavel ERROS
  - acrescentado ELSE aos IFs para restaurar cor de fundo dos campos textos quando corretos
----------------------------------------------------------------*/

function validardadosPais()
{
   erros = "Campos incorretos:\n";
  
   nome = document.dados.nome.value;
   if(nome.length == 0)
   {
     document.dados.nome.style.backgroundColor = "yellow";
     document.dados.nome.focus();
     erros = erros + " Nome\n";
   }
   else document.dados.nome.style.backgroundColor = "white";
   
   siglaPais = document.dados.siglaPais.value;
   if(siglaPais.length != 3)
   {
     document.dados.siglaPais.style.backgroundColor = "yellow";
     document.dados.siglaPais.focus();
     erros = erros + " Sigla\n";
   }
   else document.dados.siglaPais.style.backgroundColor = "white";      
   
   if (erros.length > 20)
   {
     alert (erros);
     return false;
   }
   return true;
}

function validardadosEstado()
{
   erros = "Campos incorretos:\n";
  
   nome = document.dados.nome.value;
   if(nome.length == 0)
   {
     document.dados.nome.style.backgroundColor = "yellow";
     document.dados.nome.focus();
     erros = erros + " Nome\n";
   }
   else document.dados.nome.style.backgroundColor = "white";
   
   siglaEstado = document.dados.siglaEstado.value;
   if(siglaEstado.length != 2)
   {
     document.dados.siglaEstado.style.backgroundColor = "yellow";
     document.dados.siglaEstado.focus();
     erros = erros + " Sigla\n";
   }
   else document.dados.siglaEstado.style.backgroundColor = "white";
   
   // Recupera o valor do combobox
   paisOpcao = document.getElementById('pais').options[document.getElementById('pais').selectedIndex].value;

   // Verifica se selecionou uma opcao
   if(paisOpcao == "0")
   {
    erros = erros + " País\n";
   }
   
   if (erros.length > 20)
   {
     alert (erros);
     return false;
   }
   return true;
}

function validardadosCidade()
{
   erros = "Campos incorretos:\n";
  
   nome = document.dados.nome.value;
   if(nome.length == 0)
   {
     document.dados.nome.style.backgroundColor = "yellow";
     document.dados.nome.focus();
     erros = erros + " Nome\n";
   }
   else document.dados.nome.style.backgroundColor = "white";
   
   // Recupera o valor do combobox
   paisOpcao = document.getElementById('pais').options[document.getElementById('pais').selectedIndex].value;
   // Verifica se selecionou uma opcao
   if(paisOpcao == "0")
   {
    erros = erros + " País\n";
   }
   
   estadoOpcao = document.getElementById('estado').options[document.getElementById('estado').selectedIndex].value;
   if(estadoOpcao == "0")
   {
    erros = erros + " Estado\n";
   }
   
   if (erros.length > 20)
   {
     alert (erros);
     return false;
   }
   return true;
}

function validardadosMTD()
{
   erros = "Campos incorretos:\n";
  
   nome = document.dados.nome.value;
   if(nome.length == 0)
   {
     document.dados.nome.style.backgroundColor = "yellow";
     document.dados.nome.focus();
     erros = erros + " Nome\n";
   }
   else document.dados.nome.style.backgroundColor = "white";
   
   if (erros.length > 20)
   {
     alert (erros);
     return false;
   }
   return true;
}