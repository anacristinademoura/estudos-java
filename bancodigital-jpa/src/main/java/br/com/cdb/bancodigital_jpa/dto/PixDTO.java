package br.com.cdb.bancodigital_jpa.dto;

public class PixDTO {
	private Long idContaDestino;
	private Double valor;

    public Long getIdContaDestino() {return idContaDestino;}
    public void setIdContaDestino(Long idContaDestino) {this.idContaDestino = idContaDestino;}

    public Double getValor() {return valor;}
    public void setValor(Double valor) {this.valor = valor;}
}
