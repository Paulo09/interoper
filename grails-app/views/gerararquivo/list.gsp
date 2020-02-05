

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
        <title>Lista Gerararquivo</title>
    </head>
	<nav class="nav-extended btn waves-effect waves-light" style="background-image:url(${createLinkTo(dir:'images/view/list/barraMenu',file:'barraMenu.jpg')});">  
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
    <body style="background-image:url(${createLinkTo(dir:'images/view/list/backgroundPagina',file:'backgroundPagina.jpg')});background-repeat:no-repeat;background-size:cover;">
        <div class="card">
		  <div class="card-image">
		  <img src="${createLinkTo(dir:'images/view/list/backgroundCard',file:'backgroundCard.jpg')}">
		  <span class="card-title"><b>L</b>ista <b>Gerararquivo</b></span>
		</div>
        <div class="body">
             <g:if test="${flash.message}">
				<nav class="nav-extended btn waves-effect waves-light" style="background-image:url(${createLinkTo(dir:'images/view/list/message',file:'message.jpg')});">${flash.message}</nav>
            </g:if>
            <div class="list">
                <table class="striped centered grey lighten-2">
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Base</th>
                   	    
                   	        <g:sortableColumn property="caminhoArquivo" title="Caminho Arquivo" />
                        
                   	        <g:sortableColumn property="nomeArquivo" title="Nome Arquivo" />
                        
                   	        <g:sortableColumn property="tipoArquivo" title="Tipo Arquivo" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${gerararquivoList}" status="i" var="gerararquivo">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${gerararquivo.id}">${fieldValue(bean:gerararquivo, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:gerararquivo, field:'base')}</td>
                        
                            <td>${fieldValue(bean:gerararquivo, field:'caminhoArquivo')}</td>
                        
                            <td>${fieldValue(bean:gerararquivo, field:'nomeArquivo')}</td>
                        
                            <td>${fieldValue(bean:gerararquivo, field:'tipoArquivo')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
			<nav class="nav-extended btn waves-effect waves-light" style="background-image:url(${createLinkTo(dir:'images/view/list/backgroundPagina',file:'backgroundPagina.jpg')});">  
				 <ul class="pagination" align="center"><li class="active" style="background-image:url(${createLinkTo(dir:'images/view/list/paginacao',file:'paginacao.jpg')});"><g:paginate total="${Gerararquivo.count()}"/></ul>
            </nav>
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
