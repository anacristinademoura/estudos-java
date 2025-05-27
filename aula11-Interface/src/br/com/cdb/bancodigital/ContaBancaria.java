package br.com.cdb.bancodigital;

public interface ContaBancaria {
	
	public double getSaldo();
	public double depositar(double valor);
	public boolean sacar(double valor);
	public void transferir(double valor, ContaBancaria destino);
}
