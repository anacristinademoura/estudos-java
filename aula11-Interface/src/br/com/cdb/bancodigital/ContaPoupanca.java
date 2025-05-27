package br.com.cdb.bancodigital;

public class ContaPoupanca implements ContaBancaria {

	private double saldo;

	@Override
	public double getSaldo() {
		return saldo;
	}

	@Override
	public double depositar(double valor) {
		return saldo += valor;
	}

	@Override
	public boolean sacar(double valor) {
		valor += 1; // Exemplo de condição específica para essa classe

		if (saldo < valor) {
			return false;
		} else {
			saldo -= valor;
			return true;
		}
	}

	@Override
	public void transferir(double valor, ContaBancaria destino) {
		if (sacar(valor)) {
			destino.depositar(valor);
		}
	}

}
