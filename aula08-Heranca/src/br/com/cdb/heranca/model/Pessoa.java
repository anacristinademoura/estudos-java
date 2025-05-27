package br.com.cdb.heranca.model;

public class Pessoa {
	
	public String nome;
	public long cpf;
	
	//Heranca de método construtor
	public Pessoa(String nome){
		this.nome = nome;
	}
	
	public void apresentacao() {
		System.out.println("Olá, meu nome é " + nome);
	}
}
