package sample
import grails.rest.*

@Resource(uri='/api/sala')
class Sala{
  String nome
  
  static belongsTo = [aluno: Aluno]
}
