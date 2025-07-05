package br.com.cdb.bancodigital_jpa.dto;

import java.time.LocalDate;

public class CartaoDetalhadoDTO {
	private Long id;
    private String numero;
    private String tipo;
    private boolean ativo;
    private LocalDate validade;
    private Double limite;
    private Double limiteDiario;
    private String titular;

	public CartaoDetalhadoDTO(Long id, String numero, String tipo, boolean ativo,
			LocalDate validade, Double limite, Double limiteDiario, String titular) {
	this.id = id;
	this.numero = numero;
	this.tipo = tipo;
	this.ativo = ativo;
	this.validade = validade;
	this.limite = limite;
	this.limiteDiario = limiteDiario;
	this.titular = titular;
}

    public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getNumero() {return numero;}
	public void setNumero(String numero) {this.numero = numero;}

	public String getTipo() {return tipo;}
	public void setTipo(String tipo) {this.tipo = tipo;}

	public boolean isAtivo() {return ativo;}
	public void setAtivo(boolean ativo) {this.ativo = ativo;}

	public LocalDate getValidade() {return validade;}
	public void setValidade(LocalDate validade) {this.validade = validade;}

	public Double getLimite() {return limite;}
	public void setLimite(Double limite) {this.limite = limite;}

	public Double getLimiteDiario() {return limiteDiario;}
	public void setLimiteDiario(Double limiteDiario) {this.limiteDiario = limiteDiario;}

	public String getTitular() {return titular;}
	public void setTitular(String titular) {this.titular = titular;}
}
