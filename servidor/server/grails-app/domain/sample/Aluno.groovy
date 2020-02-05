package sample
import grails.rest.*

@Resource(uri='/api/aluno')
class Aluno{
  String nome

  static hasMany = [salas: Sala]
}
