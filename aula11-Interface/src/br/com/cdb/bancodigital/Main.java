package br.com.cdb.bancodigital;

public class Main {

	public static void main(String[] args) {
		
		ContaCorrente cc = new ContaCorrente();
		cc.depositar(1000.00);
		ContaPoupanca cp = new ContaPoupanca();
		cp.depositar(500.00);
		
		cc.depositar(500.00);
		cp.sacar(200.00);
		cc.transferir(300.00, cp);
		
		System.out.println("Saldo da conta corrente: " + cc.getSaldo());
		System.out.println("Saldo da conta poupança: " + cp.getSaldo());

	}

}
