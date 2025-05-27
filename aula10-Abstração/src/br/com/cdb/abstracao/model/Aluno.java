package br.com.cdb.abstracao.model;

public class Aluno extends Pessoa {

	public int numeroMatricula;

	public Aluno(String nome, int numeroMatricula) {
		super(nome);
		this.numeroMatricula = numeroMatricula;
	}

	@Override
	public void apresentacao() {
		System.out.println("Olá, meu nome é " + nome + " e minha mátricula é " + numeroMatricula);
	}
}
