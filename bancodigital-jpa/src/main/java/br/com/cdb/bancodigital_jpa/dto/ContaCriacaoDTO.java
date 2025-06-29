package br.com.cdb.bancodigital_jpa.dto;

public class ContaCriacaoDTO {
	private String tipo;
	private Long idCliente;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
}
