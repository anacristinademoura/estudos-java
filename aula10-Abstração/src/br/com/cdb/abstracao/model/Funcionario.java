package br.com.cdb.abstracao.model;

public class Funcionario extends Pessoa {

	public double salario;

	public Funcionario(String nome) {
		super(nome);
	}

	@Override
	public void apresentacao() {}
}
