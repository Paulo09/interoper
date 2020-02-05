

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css"/>
	     <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		 
		 <!--Materilize embarcado-->
	      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	      <!--Import materialize.css-->
		  <link type="text/css" rel="stylesheet" href="${createLinkTo(dir:'css',file:'materialize.min.css')}" media="screen,projection"/>
	      <!--Let browser know website is optimized for mobile-->
	      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
   	      <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'materialize.css')}"/>
		  <!--Materilize embarcado-->
        <title>Editar Dados Gerararquivo</title>
    </head>
	<nav class="nav-extended btn waves-effect waves-light" style="background-image:url(${createLinkTo(dir:'images/view/edit/barraMenu',file:'barraMenu.jpg')});">  
		<div class="nav-content">
		  <ul class="tabs tabs-transparent">
			<li class="tab"><a href="#test1">Sair</a></li>
			<li class="tab"><a class="active" href="#test2">Menu</a></li>
			<li class="tab disabled"><a href="#test3">Buscar</a></li>
		  </ul>
		</div>
	</nav>
	<body class="teal lighten-2">   
	<div class="container" style="margin-top:40px;">
    <body style="background-image:url(${createLinkTo(dir:'images/view/edit/backgroundPagina',file:'backgroundPagina.jpg')});background-repeat:no-repeat;background-size:cover;">
        <div class="card">
		  <div class="card-image">
		  <img src="${createLinkTo(dir:'images/view/edit/backgroundCard',file:'backgroundCard.jpg')}">
		  <span class="card-title"><b>E</b>ditar Dados <b>Gerararquivo</b></span>
		</div>
        <div class="body">
            <g:if test="${flash.message}">
	            <nav class="nav-extended btn waves-effect waves-light" style="background-image:url(${createLinkTo(dir:'images/view/edit/message',file:'message.jpg')});">${flash.message}</nav>
            </g:if>
            <g:hasErrors bean="${gerararquivo}">
            <div class="errors">
                <g:renderErrors bean="${gerararquivo}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${gerararquivo?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="base">Base:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:gerararquivo,field:'base','errors')}">
                                    <g:select class="form-control" optionKey="id" from="${Base.list()}" name="base.id" value="${gerararquivo?.base?.id}" ></g:select> <a href="${createLinkTo(dir:'/base/create')}">* Cadastrar Base</a>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="caminhoArquivo">Caminho Arquivo:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:gerararquivo,field:'caminhoArquivo','errors')}">
                                    <i class="fa fa-text-width"></i><input type="text" class="form-control" size="50" placeholder="Digite caminhoArquivo " id="caminhoArquivo" name="caminhoArquivo" size="50" value="${fieldValue(bean:gerararquivo,field:'caminhoArquivo')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nomeArquivo">Nome Arquivo:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:gerararquivo,field:'nomeArquivo','errors')}">
                                    <i class="fa fa-text-width"></i><input type="text" class="form-control" size="50" placeholder="Digite nomeArquivo " id="nomeArquivo" name="nomeArquivo" size="50" value="${fieldValue(bean:gerararquivo,field:'nomeArquivo')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipoArquivo">Tipo Arquivo:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:gerararquivo,field:'tipoArquivo','errors')}">
                                    <i class="fa fa-text-width"></i><input type="text" class="form-control" size="50" placeholder="Digite tipoArquivo " id="tipoArquivo" name="tipoArquivo" size="50" value="${fieldValue(bean:gerararquivo,field:'tipoArquivo')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons" align="center">
                    <span class="button"><g:actionSubmit class="btn waves-effect waves-light" style="background-image:url(${createLinkTo(dir:'images/view/edit/botao',file:'botaoEditar.jpg')});padding:10px;margin:10px;size:30px;width:110px;" action="Update" value="Salvar" onclick="return confirm('Deseja editar os dados?');" /></span>
                    <span class="button"><g:actionSubmit class="btn waves-effect waves-light" style="background-image:url(${createLinkTo(dir:'images/view/edit/botao',file:'botaoDeletar.jpg')});padding:10px;margin:10px;size:30px;width:110px;" action="delete" value="Apagar" onclick="return confirm('Deseja apagar os dados?');" /></span>
                </div>
            </g:form>
        </div>
		   <!--Import jQuery before materialize.js-->
	       <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	       <script type="text/javascript" src="${createLinkTo(dir:'js',file:'materialize.js')}"></script>
		   <script>
			  $('.datepicker').pickadate({format: 'dd/mm/yy',selectMonths: true,selectYears: 15,today: 'Hoje',clear: 'Limpar',close:'Ok',closeOnSelect: false});
			  $(document).ready(function() {$('select').material_select();});
		   </script>
    </body>
</html>
