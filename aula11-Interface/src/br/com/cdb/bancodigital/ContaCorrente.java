package br.com.cdb.bancodigital;

public class ContaCorrente implements ContaBancaria {

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
		if (saldo < valor) {
			return false;
		} else {
			saldo -= valor;
			return true;
		}
	}

	@Override
	public void transferir(double valor, ContaBancaria destino) {
		if (sacar(valor)) { // mesma coisa que sacar(valor) == true
			destino.depositar(valor);
		}

	}

}
