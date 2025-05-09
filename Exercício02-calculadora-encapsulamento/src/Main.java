import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Calculadora calculo = new Calculadora();
		
		double n1;
		double n2;
		char op;
		
		System.out.println("Digite o primeiro número: ");
		n1 = input.nextDouble();
		
		System.out.println("Qual operação deseja realizar? ");
		op = input.next().charAt(0);
		
		System.out.println("Digite o segundo número: ");
		n2 = input.nextDouble();
		
		double resultado = calculo.calcular(n1, n2, op);
		
		System.out.println("O resultado é: " + resultado);
		
		input.close();

	}

}
