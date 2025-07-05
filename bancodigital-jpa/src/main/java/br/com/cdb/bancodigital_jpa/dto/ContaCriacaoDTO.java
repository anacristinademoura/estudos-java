package br.com.cdb.bancodigital_jpa.dto;

import br.com.cdb.bancodigital_jpa.enums.TipoConta;

public class ContaCriacaoDTO {
	private TipoConta tipo;
	private Long idCliente;

	public TipoConta getTipo() {return tipo;}
	public void setTipo(TipoConta tipo) {this.tipo = tipo;}

	public Long getIdCliente() {return idCliente;}
	public void setIdCliente(Long idCliente) {this.idCliente = idCliente;}
}
