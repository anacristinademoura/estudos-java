package br.com.cdb.abstracao.model;

public class Faxineiro extends Funcionario {

	public String turno;

	public Faxineiro(String nome) {
		super(nome);
	}

	@Override
	public void apresentacao() {
		System.out.println("Olá, sou o faxineiro " + nome + " e sou do turno da " + turno);
	}
}