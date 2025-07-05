package br.com.cdb.bancodigital_jpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import java.util.ArrayList;

import br.com.cdb.bancodigital_jpa.enums.TipoCliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String cpf;
	
	 @Enumerated(EnumType.STRING)
	 private TipoCliente tipoCliente;

	 @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	 @JsonManagedReference
	 private List<Cartao> cartoes = new ArrayList<>();
	 
	 @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
	    private Conta conta;
	 
	/*
	 mappedBy indica que essa entidade não é a dona da relação.
	 A dona que contém a chave possui @JoinColumn
	 
	 Lado dependente, cascadeType propaga ações
	 para a Conta associada (salvar, deletar, atualizar) automaticamente.
	*/

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}

	public String getCpf() {return cpf;}
	public void setCpf(String cpf) {this.cpf = cpf;}

	public Conta getConta() {return conta;}
	public void setConta(Conta conta) {this.conta = conta;}
	
	public TipoCliente getTipoCliente() {return tipoCliente;}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	public List<Cartao> getCartoes() { return cartoes; }
    public void setCartoes(List<Cartao> cartoes) { this.cartoes = cartoes; }
}
