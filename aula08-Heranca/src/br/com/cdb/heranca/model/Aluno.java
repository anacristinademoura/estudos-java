package br.com.cdb.heranca.model;

public class Aluno extends Pessoa{
	
	public int numeroMatricula;
	
	//Método herdado da superclasse Pessoa a partir da palavra "super"
	public Aluno(String nome, int numeroMatricula) {
		super(nome);
		this.numeroMatricula = numeroMatricula;
	}
}
