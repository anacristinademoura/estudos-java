package br.com.cdb.polimorfismo.model;

public class Professor extends Funcionario{
	
	public int numeroDeAulas;
	
	public Professor(String nome) {
		super(nome);
	}
	
	//SOBRECARGA DE MÉTODOS - Mesmo nome, parâmetros diferentes
		public void novoSalario(double novoSalario) {
			this.salario = novoSalario;
		}
		
		public void novoSalario(double novoSalario, int nAulasAdicionais) {
			this.salario = novoSalario;
			this.numeroDeAulas += nAulasAdicionais;
		}
	
	//SOBRESCRITA O MÉTODO APRESENTACAO()
		@Override
		public void apresentacao() {
			System.out.println("Olá, sou o professor " + nome + " e já dei " + numeroDeAulas + " aulas" );
		}
}
