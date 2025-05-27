package br.com.cdb.abstracao.model;

public class Professor extends Funcionario {

	public int numeroDeAulas;

	public Professor(String nome) {
		super(nome);
	}

	public void novoSalario(double novoSalario) {
		this.salario = novoSalario;
	}

	public void novoSalario(double novoSalario, int nAulasAdicionais) {
		this.salario = novoSalario;
		this.numeroDeAulas += nAulasAdicionais;
	}

	@Override
	public void apresentacao() {
		System.out.println("Olá, sou o professor " + nome + " e já dei " + numeroDeAulas + " aulas");
	}
}
