package br.com.cdb.bancodigital_jpa.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.cdb.bancodigital_jpa.enums.TipoCartao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero;
	private String senha;
	
	@ManyToOne
	@JoinColumn(name = "conta_id")
	private Conta conta;
	
	@ManyToOne
    @JoinColumn(name = "cliente_id") 
	@JsonBackReference
	private Cliente cliente;
	
	@Enumerated(EnumType.STRING)
    private TipoCartao tipo;

    private boolean ativo;
    private double limite;
    private double limiteDiario;
    private double saldoFatura;
    private LocalDate validade;
    
    @Column
    //não se aplica a cartões de débito
    private Double valorFatura = 0.0;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public TipoCartao getTipo() { return tipo; }
    public void setTipo(TipoCartao tipo) { this.tipo = tipo; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public double getLimite() { return limite; }
    public void setLimite(double limite) { this.limite = limite; }

    public double getLimiteDiario() { return limiteDiario; }
    public void setLimiteDiario(double limiteDiario) { this.limiteDiario = limiteDiario; }

    public double getSaldoFatura() { return saldoFatura; }
    public void setSaldoFatura(double saldoFatura) { this.saldoFatura = saldoFatura; }

    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }
    
    public Double getValorFatura() {return valorFatura;}
    public void setValorFatura(Double valorFatura) {this.valorFatura = valorFatura;}
}
