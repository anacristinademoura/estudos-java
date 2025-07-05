package br.com.cdb.bancodigital_jpa.dto;

public class ContaDetalhadaDTO {
	private String numero;
	private Double saldo;
	private String tipo;
	private String nomeCliente;
	private String cpfCliente;

	public ContaDetalhadaDTO(String numero, Double saldo, String tipo, 
			String nomeCliente, String cpfCliente) {
		this.numero = numero;
		this.saldo = saldo;
		this.tipo = tipo;
		this.nomeCliente = nomeCliente;
		this.cpfCliente = cpfCliente;
	}
	
	public ContaDetalhadaDTO() {}

	public String getNumero() {return numero;}
	public void setNumero(String numero) {this.numero = numero;}

	public Double getSaldo() {return saldo;}
	public void setSaldo(Double saldo) {this.saldo = saldo;}

	public String getTipo() {return tipo;}
	public void setTipo(String tipo) {this.tipo = tipo;}

	public String getNomeCliente() {return nomeCliente;}
	public void setNomeCliente(String nomeCliente) {this.nomeCliente = nomeCliente;}

	public String getCpfCliente() {return cpfCliente;}
	public void setCpfCliente(String cpfCliente) {this.cpfCliente = cpfCliente;}
}
