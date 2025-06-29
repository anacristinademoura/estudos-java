package br.com.cdb.bancodigital_jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String cpf;
	
	@OneToOne(mappedBy = "cliente")
	@JsonManagedReference //Conta é dono da resposta
	private Conta conta;
	/*
	 mappedBy indica que essa entidade não é a dona da relação.
	 A dona que contém a chave possui @JoinColumn
	 
	 Lado dependente, cascadeType propaga ações
	 para a Conta associada (salvar, deletar, atualizar) automaticamente.
	*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
}
