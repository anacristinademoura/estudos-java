package br.com.cdb.heranca.model;

public class Professor extends Funcionario{
	
	public int numeroDeAulas;
	
	//Herda o método construtor de Funcioário
	public Professor(String nome) {
		super(nome);
	}
}
