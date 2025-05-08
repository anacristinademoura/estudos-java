package br.com.cbd.bancodigital;

public class Conta {
	
	private int numero;
	private double saldo;
	
	public Conta(int numero) {
		this.numero = numero;
		this.saldo = 0;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public double getSaldo() {
		return saldo;
	}
	public void depositar(double valor) {
		
		if(valor > 0) {
			this.saldo += valor;
		}
	}
	
	
}
