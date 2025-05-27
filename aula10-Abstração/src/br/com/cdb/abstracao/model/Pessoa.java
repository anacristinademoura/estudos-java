package br.com.cdb.abstracao.model;

public abstract class Pessoa {
	//Não posso mais instânciar essa classe, fulano não existe mais
	public String nome;
	public long cpf;

	public Pessoa(String nome) {
		this.nome = nome;
	}

	public abstract void apresentacao(); //Um método abstratado não pode ter definição
}
