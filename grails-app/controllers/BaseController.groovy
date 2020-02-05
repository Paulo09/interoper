import javax.servlet.http.HttpServletResponse
import grails.converters.*
class BaseController {

	///////////////////////////////////////////////////////////////
    ////           API - RESTFULL - jRestFull-API 1.4          ////
	////           @Paulo Castro v4                            ////            
	///////////////////////////////////////////////////////////////
    
    def index = { redirect(action:list,params:params) }

    def listar = {
        def objJson = Base.list() ?: []
		def arrObjJson=[]
     	if(params.id)
        {
			if(Base.findById(params.id)){
			arrObjJson.add(Base.findById(params.id))
			render arrObjJson as JSON}
			if(!Base.findById(params.id)){
			def json='{"id":'+"${params.id}"+',"msg":"Base nao encontrado!"}'
			render json}
        }
	    else{render objJson as JSON}
    }
	
	  def deletar = {	
		if(Base.findById(request.JSON.id)){
		Base.get(request.JSON.id)?.delete()
		render "Base Id:${request.JSON.id} Deletado com sucesso!"}
		else{render "${className} Id:${request.JSON.id} nao encontrado!"}
	}

    def editar = {
        Base c = Base.get(request.JSON.id)
		c.properties = request.JSON	
		if(c.save()){render "Base Id:${c.id} - Editado com sucesso!!" 
		}else{render "Erro: Id: ${c.id} nao encontrado!"}
    }

    def salvar = {
         def base = new Base(request.JSON)
		if(base.save()){
			render "Base Id:${base.id} - Salvo com sucesso!" 
		}else{render "Erro: Base nao foi salvo!"}	    
    }
   
    def list = {
        if(!params.max) params.max = 10
        [ baseList: Base.list( params ) ]
    }

    def show = {
        def base = Base.get( params.id )

        if(!base) {
            flash.message = "Base não encontrado id ${params.id}"
            redirect(action:list)
        }
        else { return [ base : base ] }
    }

    def delete = {
        def base = Base.get( params.id )
        if(base) {
            base.delete()
            flash.message = "Base ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Base não encontrado id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def base = Base.get( params.id )

        if(!base) {
            flash.message = "Base não encontrado id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ base : base ]
        }
    }

    def update = {
        def base = Base.get( params.id )
        if(base) {
            base.properties = params
            if(!base.hasErrors() && base.save()) {
                flash.message = "Base ${params.id} updated"
                redirect(action:show,id:base.id)
            }
            else {
                render(view:'edit',model:[base:base])
            }
        }
        else {
            flash.message = "Base não encontrado id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def base = new Base()
        base.properties = params
		
		println "----------- Valor -------------"+params.nome+" Descrica:"+params.descricao
        return ['base':base]
    }

    def save = {
        def base = new Base(params)
        if(!base.hasErrors() && base.save()) {
            flash.message = "Base ${base.id} cadastrado"
            redirect(action:show,id:base.id)
        }
        else {
            render(view:'create',model:[base:base])
        }
    }
}
