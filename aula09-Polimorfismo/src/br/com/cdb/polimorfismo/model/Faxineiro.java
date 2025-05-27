package br.com.cdb.polimorfismo.model;

public class Faxineiro extends Funcionario{
	
	public String turno;
	
	public Faxineiro(String nome) {
		super(nome);
	}
	
	//SOBRESCRITA O MÉTODO APRESENTACAO()
	@Override
	public void apresentacao() {
		System.out.println("Olá, sou o faxineiro " + nome + " e sou do turno da " + turno);
	}
}
