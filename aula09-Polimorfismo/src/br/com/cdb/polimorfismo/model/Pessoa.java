package br.com.cdb.polimorfismo.model;

public class Pessoa {
	
	public String nome;
	public long cpf;
	
	public Pessoa(String nome){
		this.nome = nome;
	}
	
	public void apresentacao() {
		System.out.println("Olá, meu nome é " + nome);
	}
}
