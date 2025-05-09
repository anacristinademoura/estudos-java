public class Calculadora {
	
	//MÉTODOS
	private double somar(double n1, double n2) {
		return n1 + n2;
	}

	private double subtrair(double n1, double n2) {
		return n1 - n2;
	}

	private double multiplicar(double n1, double n2) {
		return n1 * n2;
	}

	private double dividir(double n1, double n2) {
		return n1 / n2;
	}

	public double calcular(double n1, double n2, char op) {
		switch (op) {
		case '+':
			return somar(n1, n2);
		case '-':
			return subtrair(n1, n2);
		case '*':
			return multiplicar(n1, n2);
		case '/':
			if(n2 != 0) {
				return dividir(n1, n2);
			}
			else {
				System.out.println("Não é possível fazer divisão por zero.");
			}
		default:
			System.out.println("Operação inválida.");
			return 0; //EVITA ERRO
		}
	}
}
