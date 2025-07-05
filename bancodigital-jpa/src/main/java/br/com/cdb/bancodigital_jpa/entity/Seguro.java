package br.com.cdb.bancodigital_jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // ex: "vida", "roubo", "acidentes"

    private LocalDate dataInicio;
    private LocalDate dataFim;

    private double valor;

    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    public LocalDate getDataInicio() {return dataInicio;}
    public void setDataInicio(LocalDate dataInicio) {this.dataInicio = dataInicio;}

    public LocalDate getDataFim() {return dataFim;}
    public void setDataFim(LocalDate dataFim) {this.dataFim = dataFim;}

    public double getValor() {return valor;}
    public void setValor(double valor) {this.valor = valor;}

    public boolean isAtivo() {return ativo;}
    public void setAtivo(boolean ativo) {this.ativo = ativo;}

    public Cartao getCartao() {return cartao;}
    public void setCartao(Cartao cartao) {this.cartao = cartao;}
}
