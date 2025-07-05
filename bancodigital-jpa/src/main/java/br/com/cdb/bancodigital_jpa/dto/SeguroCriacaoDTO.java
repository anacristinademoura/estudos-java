package br.com.cdb.bancodigital_jpa.dto;

import java.time.LocalDate;

public class SeguroCriacaoDTO {

    private String tipo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valor; //evita padrão 0.0 e permitir validações nulas se necessário.

    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    
    public LocalDate getDataInicio() {return dataInicio;}
    public void setDataInicio(LocalDate dataInicio) {this.dataInicio = dataInicio;}
    
    public LocalDate getDataFim() {return dataFim;}
    public void setDataFim(LocalDate dataFim) {this.dataFim = dataFim;}
    
    public double getValor() {return valor;}
    public void setValor(double valor) {this.valor = valor;}
}
