package br.com.cdb.polimorfismo.model;

public class Aluno extends Pessoa{
	
	public int numeroMatricula;
	
	public Aluno(String nome, int numeroMatricula) {
		super(nome);
		this.numeroMatricula = numeroMatricula;
	}
	
	//SOBRESCRITA - ANOTAÇÃO - Método da classe Pessoa
	@Override
	public void apresentacao() {
		System.out.println("Olá, meu nome é " + nome + " e minha mátricula é " + numeroMatricula);
	}
}
