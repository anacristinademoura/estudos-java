package br.com.cbd.bancodigital;

public class Main {

	public static void main(String[] args) {
		
		Conta contaCorrente = new Conta(123456);
		contaCorrente.depositar(1000);
		
		Conta contaPoupanca = new Conta(654321);
		contaPoupanca.depositar(500);
		
		System.out.println("Conta: " + contaCorrente.getNumero() + 
				" possui o saldo de R$" + contaCorrente.getSaldo());
		
		System.out.println("Conta: " + contaPoupanca.getNumero() + 
				" possui o saldo de R$" + contaPoupanca.getSaldo());
	}

}
