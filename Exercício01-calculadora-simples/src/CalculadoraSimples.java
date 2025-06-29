import java.util.Scanner;

public class CalculadoraSimples {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		
		int valor1, valor2, resultado = 0;
		boolean operacaoValida = true;
		String entrada;
		
		System.out.println("-------Calculadora-------");
		System.out.println("Digite o primeiro número: ");
		valor1 = input.nextInt();
		System.out.println("Digite o segundo número: ");
		valor2 = input.nextInt();
		
		System.out.println("Escolha a operação Ex: +, -, *, / ");
		entrada = input.next();
		
		char operacao = entrada.charAt(0);
		
		switch(operacao) {
		case '+':
			resultado = valor1 + valor2;
			break;
		case '-':
			resultado = valor1 - valor2;
			break;
		case '*':
			resultado = valor1 * valor2;
			break;
		case '/':
			if(valor2 != 0) {
				resultado = valor1 / valor2;
			}else {
				System.out.println("Não é possivel fazer divisão por zero.");
				operacaoValida = false;
			}
			break;
		default:
			System.out.println("Operação não aceita.");
			operacaoValida = false;
		}
		
		if(operacaoValida) {
			System.out.println("O resultado é: " + resultado);
		}
		
		System.out.println("--------------------------");
		
		input.close();
	}

}
