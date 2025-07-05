package br.com.cdb.bancodigital_jpa.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.cdb.bancodigital_jpa.enums.TipoCliente;
import br.com.cdb.bancodigital_jpa.enums.TipoConta;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreUpdate;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numero;
	private Double saldo = 0.0;
	
	 @Enumerated(EnumType.STRING)
	 private TipoConta tipoConta;

	@OneToOne // Dono da chave estrangeira
	@JsonBackReference // Evita o loop
	private Cliente cliente;
	
	@PreUpdate
	public void atualizarDataUltimaAtualizacao() {
	    LocalDate.now();
	}
	
	public void aplicarManutencaoMensal() {
        TipoCliente tipo = cliente.getTipoCliente();
        if (tipoConta == TipoConta.CORRENTE) {
            double taxa = switch (tipo) {
                case COMUM -> 12.0;
                case SUPER -> 8.0;
                case PREMIUM -> 0.0;
            };
            this.saldo -= taxa;
        }
    }

    public void aplicarRendimentoMensal() {
        TipoCliente tipo = cliente.getTipoCliente();
        if (tipoConta == TipoConta.POUPANCA) {
            double taxaAnual = switch (tipo) {
                case COMUM -> 0.005;
                case SUPER -> 0.007;
                case PREMIUM -> 0.009;
            };
            double taxaMensal = Math.pow(1 + taxaAnual, 1.0 / 12) - 1;
            this.saldo += this.saldo * taxaMensal;
        }
    }
	

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public String getNumero() {return numero;}
	public void setNumero(String numero) {this.numero = numero;}

	public Double getSaldo() {return saldo;}
	public void setSaldo(Double saldo) {this.saldo = saldo;}

	public Cliente getCliente() {return cliente;}
	public void setCliente(Cliente cliente) {this.cliente = cliente;}

	public TipoConta getTipoConta() {return tipoConta;}
	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
}
