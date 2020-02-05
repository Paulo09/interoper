import javax.servlet.http.HttpServletResponse
import grails.converters.*

import java.sql.*;
import groovy.sql.Sql

class GerararquivoController {
	
	def sql = Sql.newInstance("jdbc:postgresql://localhost:5432/robo","postgres", "root", "org.postgresql.Driver")
	 
	///////////////////////////////////////////////////////////////
    ////           API - RESTFULL - jRestFull-API 1.4          ////
	////           @Paulo Castro v4                            ////            
	///////////////////////////////////////////////////////////////
    
    def index = { redirect(action:list,params:params) }

    def listar = {
        def objJson = Gerararquivo.list() ?: []
		def arrObjJson=[]
     	if(params.id)
        {
			if(Gerararquivo.findById(params.id)){
			arrObjJson.add(Gerararquivo.findById(params.id))
			render arrObjJson as JSON}
			if(!Gerararquivo.findById(params.id)){
			def json='{"id":'+"${params.id}"+',"msg":"Gerararquivo nao encontrado!"}'
			render json}
        }
	    else{render objJson as JSON}
    }
	
	  def deletar = {	
		if(Gerararquivo.findById(request.JSON.id)){
		Gerararquivo.get(request.JSON.id)?.delete()
		render "Gerararquivo Id:${request.JSON.id} Deletado com sucesso!"}
		else{render "${className} Id:${request.JSON.id} nao encontrado!"}
	}

    def editar = {
        Gerararquivo c = Gerararquivo.get(request.JSON.id)
		c.properties = request.JSON	
		if(c.save()){render "Gerararquivo Id:${c.id} - Editado com sucesso!!" 
		}else{render "Erro: Id: ${c.id} nao encontrado!"}
    }

    def salvar = {
         def gerararquivo = new Gerararquivo(request.JSON)
		if(gerararquivo.save()){
			render "Gerararquivo Id:${gerararquivo.id} - Salvo com sucesso!" 
		}else{render "Erro: Gerararquivo nao foi salvo!"}	    
    }
   
    def list = {
        if(!params.max) params.max = 10
        [ gerararquivoList: Gerararquivo.list( params ) ]
    }

    def show = {
        def gerararquivo = Gerararquivo.get( params.id )
		
		    try { 
                  def selectBase = "select count(*) as cont from pg_database where datistemplate = 'f'".toString()
			      sql.rows(selectBase)
				  def selectBaseOrigem = "select count(*) as cont from base".toString()
				  sql.rows(selectBaseOrigem)
				  
				  if(sql.rows(selectBaseOrigem).toString().replace('[{cont=','').replace('}]','') != sql.rows(selectBase).toString().replace('[{cont=','').replace('}]','')){	
					println "-------------- Insert ------------------"
				    //def criar = "INSERT INTO base (select NEXTVAL('id'),0,datcollate,datname from pg_database where datistemplate = 'f')".toString()
			        //sql.rows(criar)
				  }else{
				   println "------1-------"+sql.rows(selectBaseOrigem).toString().replace('[{cont=','').replace('}]','')
				   println "------2-------"+sql.rows(selectBase).toString().replace('[{cont=','').replace('}]','')
				 }	
				  
				  
				   
			   }catch (Exception e){}

        if(!gerararquivo) {
            flash.message = "Gerararquivo não encontrado id ${params.id}"
            redirect(action:list)
        }
        else { return [ gerararquivo : gerararquivo ] }
    }

    def delete = {
        def gerararquivo = Gerararquivo.get( params.id )
        if(gerararquivo) {
            gerararquivo.delete()
            flash.message = "Gerararquivo ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Gerararquivo não encontrado id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def gerararquivo = Gerararquivo.get( params.id )

        if(!gerararquivo) {
            flash.message = "Gerararquivo não encontrado id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ gerararquivo : gerararquivo ]
        }
    }

    def update = {
        def gerararquivo = Gerararquivo.get( params.id )
        if(gerararquivo) {
            gerararquivo.properties = params
            if(!gerararquivo.hasErrors() && gerararquivo.save()) {
                flash.message = "Gerararquivo ${params.id} updated"
                redirect(action:show,id:gerararquivo.id)
            }
            else {
                render(view:'edit',model:[gerararquivo:gerararquivo])
            }
        }
        else {
            flash.message = "Gerararquivo não encontrado id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def gerararquivo = new Gerararquivo()
        gerararquivo.properties = params
        return ['gerararquivo':gerararquivo]
    }

    def save = {
        def gerararquivo = new Gerararquivo(params)
				
        if(gerararquivo.hasErrors()) {
            flash.message = "Gerararquivo ${gerararquivo.id} cadastrado"
            redirect(action:show,id:gerararquivo.id)
        }
        else {
            render(view:'create',model:[gerararquivo:gerararquivo])
        }
    }
	
	def selecionartabelas = {
	     def gerararquivo = new Gerararquivo(params)
		 def tabelas=[];def cont=0;def selectCampos=""
		 
		 
		def myList = [] as Vector
		    myList << params.nometabela
			println myList
		 
		try {
			def conexao = Sql.newInstance("jdbc:postgresql://localhost:5432/$gerararquivo.base.nome","postgres", "root", "org.postgresql.Driver")			
			def selectBaseOrigem = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'"
			tabelas=conexao.rows(selectBaseOrigem).table_name;def gerarClasse="";def lista=[]
			lista=params.nometabela
			
			if(params.nometabela.size() > 1){
				
				params.nometabela.each{
				    def dd=0;def campos=""; 
					
					selectCampos = "SELECT data_type,column_name FROM information_schema.columns WHERE table_name in ('${params.nometabela[cont]}')".toString()
					gerarClasse=conexao.rows(selectCampos)
					
					if(gerarClasse){
					
					    
					
					   gerarClasse.each{
					  
					        gerarClasse
							
							def objClasse = "class "+"${params.nometabela[cont]}"+" {\n"+
								gerarClasse[dd].toString().replace('column_name=','').replace('{data_type=','').replace('character','String').replace('character varying','String').replace('varying,','').replace('integer','Integer').replace(', ',' ').replace('}','').replace('bigint','Integer') //.replace('version','').replace('{data_type=','').replace('integer','Integer').replace('character','String').replace('character varying','String').replace('{data_type=character','String').replace(' column_name=','').replace('}','').replace(',',' ')+
							"\n}";
							
							File file = new File("grails-app\\classes\\${params.nometabela[cont]}.groovy");					
							if(!file.exists())
							{						
							def criarapp = new FileWriter(new File("grails-app\\classes\\${params.nometabela[cont]}.groovy"));	
							    criarapp.write(objClasse); 
							    criarapp.close();
							}
						dd++
						}
						
					}
					
						if(!gerarClasse){
						
						        
						
							    selectCampos = "SELECT data_type,column_name FROM information_schema.columns WHERE table_name in ('${params.nometabela}')".toString()
								gerarClasse=conexao.rows(selectCampos)
								
								def con1=0;def res=""
								gerarClasse.each{
								   res += ' '+gerarClasse[con1].toString().replace('column_name=','').replace('{data_type=','').replace('character','String').replace('character varying','String').replace('varying,','').replace('integer','Integer').replace(', ',' ').replace('}','').replace('bigint','Integer').replace('_','').replace('timestamp without time zone','Date').replace('text','String')+"\n"
								   con1++
								}
								
								def objClasse = "package ${gerararquivo.base.nome.replace('_','')} \n"+"import grails.rest.* \n"+"@Resource(uri='/api/${params.nometabela.toLowerCase().replace('_','')}') \n"+"""class ${params.nometabela.substring(0,1).toUpperCase()}${params.nometabela.substring(1,params.nometabela.size()).toLowerCase().replace('_','')}{\n${res}}
												"""
								
								File file = new File("grails-app\\classes\\${params.nometabela.substring(0,1).toUpperCase()}${params.nometabela.substring(1,params.nometabela.size()).toLowerCase().replace('_','')}.groovy");					
								if(!file.exists())
								{						
								def criarapp = new FileWriter(new File("grails-app\\classes\\${params.nometabela.substring(0,1).toUpperCase()}${params.nometabela.substring(1,params.nometabela.size()).toLowerCase().replace('_','')}.groovy"));	
								    criarapp.write(objClasse); 
								    criarapp.close();
								}
						  
						 }
						
					}   
					
				}
		 
		 
		 
		}catch(Exception e){}
		
		
		 
	     if(gerararquivo.hasErrors()) {
            flash.message = "Gerararquivo ${gerararquivo.id} cadastrado"
            redirect(action:show,id:gerararquivo.id)
        }
        else {
            render(view:'create',model:[gerararquivo:gerararquivo,tabelas:tabelas])
        }
	}
}
